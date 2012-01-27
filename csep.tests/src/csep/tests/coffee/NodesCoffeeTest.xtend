package csep.tests.coffee

import csep.tests.ParserTestBase
import org.junit.Test

class NodesCoffeeTest extends ParserTestBase {

  /**
   * Even the original coffee has problems parsing it
   */
  @Test def void testClosure() {
    shouldBeOk('''#### Closure

# A faux-node used to wrap an expressions body in a closure.
METHOD_DEF = ///
  ^
    (?:
      (#{IDENTIFIER_STR})
      \.prototype
      (?:
        \.(#{IDENTIFIER_STR})
      | \[("(?:[^\\"\r\n]|\\.)*"|'(?:[^\\'\r\n]|\\.)*')\]
      | \[(0x[\da-fA-F]+ | \d*\.?\d+ (?:[eE][+-]?\d+)?)\]
      )
    )
  |
    (#{IDENTIFIER_STR})
  $
///
    ''')
  }

  /**
   * @see {MissingFeaturesTest.testKeywordAsFeatureName}
   */
  @Test def void testOp() {
    ok('''#### Op

# Simple Arithmetic and logical operations. Performs some conversion from
# CoffeeScript operations into their JavaScript equivalents.
exports.Op = class Op extends Base
  constructor: (op, first, second, flip ) ->
    return new In first, second if op is 'in'
    if op is 'do'
      call = new Call first, first.params or []
      ## workaround for: call.do = yes
      call['do'] = yes
      return call
    if op is 'new'
      return first.newInstance() if first instanceof Call and not first.do and not first.isNew
      first = new Parens first   if first instanceof Code and first.bound or first.do
    @operator = CONVERSIONS[op] or op
    @first    = first
    @second   = second
    @flip     = !!flip
    return this

  # The map of conversions from CoffeeScript to JavaScript symbols.
  CONVERSIONS =
    '==': '==='
    '!=': '!=='
    'of': 'in'

  # The map of invertible operators.
  INVERSIONS =
    '!==': '==='
    '===': '!=='

  children: ['first', 'second']

  isSimpleNumber: NO

  isUnary: ->
    not @second

  isComplex: ->
    not (@isUnary() and (@operator in ['+', '-'])) or @first.isComplex()

  # Am I capable of
  # [Python-style comparison chaining](http://docs.python.org/reference/expressions.html#notin)?
  isChainable: ->
    @operator in ['<', '>', '>=', '<=', '===', '!==']

  invert: ->
    if @isChainable() and @first.isChainable()
      allInvertable = yes
      curr = this
      while curr and curr.operator
        allInvertable and= (curr.operator of INVERSIONS)
        curr = curr.first
      return new Parens(this).invert() unless allInvertable
      curr = this
      while curr and curr.operator
        curr.invert = !curr.invert
        curr.operator = INVERSIONS[curr.operator]
        curr = curr.first
      this
    else if op = INVERSIONS[@operator]
      @operator = op
      if @first.unwrap() instanceof Op
        @first.invert()
      this
    else if @second
      new Parens(this).invert()
    else if @operator is '!' and (fst = @first.unwrap()) instanceof Op and
                                  fst.operator in ['!', 'in', 'instanceof']
      fst
    else
      new Op '!', this

  unfoldSoak: (o) ->
    @operator in ['++', '--', 'delete'] and unfoldSoak o, this, 'first'

  compileNode: (o) ->    
    isChain = @isChainable() and @first.isChainable()
    # In chains, there's no need to wrap bare obj literals in parens, 
    # as the chained expression is wrapped.
    @first.front = @front unless isChain
    return @compileUnary     o if @isUnary()
    return @compileChain     o if isChain
    return @compileExistence o if @operator is '?'
    code = @first.compile(o, LEVEL_OP) + ' ' + @operator + ' ' +
           @second.compile(o, LEVEL_OP)
    if o.level <= LEVEL_OP then code else "(#{code})"

  # Mimic Python's chained comparisons when multiple comparison operators are
  # used sequentially. For example:
  #
  #     bin/coffee -e 'console.log 50 < 65 > 10'
  #     true
  compileChain: (o) ->
    [@first.second, shared] = @first.second.cache o
    fst = @first.compile o, LEVEL_OP
    code = "#{fst} #{if @invert then '&&' else '||'} #{ shared.compile o } #{@operator} #{ @second.compile o, LEVEL_OP }"
    "(#{code})"

  compileExistence: (o) ->
    if @first.isComplex()
      ref = new Literal o.scope.freeVariable 'ref'
      fst = new Parens new Assign ref, @first
    else
      fst = @first
      ref = fst
    new If(new Existence(fst), ref, type: 'if').addElse(@second).compile o

  # Compile a unary **Op**.
  compileUnary: (o) ->
    parts = [op = @operator]
    plusMinus = op in ['+', '-']
    parts.push ' ' if op in ['new', 'typeof', 'delete'] or
                      plusMinus and @first instanceof Op and @first.operator is op
    if (plusMinus && @first instanceof Op) or (op is 'new' and @first.isStatement o)
      @first = new Parens @first 
    parts.push @first.compile o, LEVEL_OP
    parts.reverse() if @flip
    parts.join ''

  toString: (idt) ->
    super idt, @constructor.name + ' ' + @operator

''')}

  @Test def void testArr() {
    ok('''#### Arr

# An array literal.
exports.Arr = class Arr extends Base
  constructor: (objs) ->
    @objects = objs or []

  children: ['objects']

  filterImplicitObjects: Call::filterImplicitObjects

  compileNode: (o) ->
    return '[]' unless @objects.length
    o.indent += TAB
    objs = @filterImplicitObjects @objects
    return code if code = Splat.compileSplattedArray o, objs
    code = (obj.compile o, LEVEL_LIST for obj in objs).join ', '
    if code.indexOf('\n') >= 0
      "[\n#{o.indent}#{code}\n#{@tab}]"
    else
      "[#{code}]"

  assigns: (name) ->
    for obj in @objects when obj.assigns name then return yes
    no

''')}
	
  /**
   * @see {MissingFeaturesTest.testCompoundAssignable}
   */
  @Test def void testObj() {
    ok('''#### Obj

# An object literal, nothing fancy.
exports.Obj = class Obj extends Base
  constructor: (props, @generated = false) ->
    @objects = @properties = props or []

  children: ['properties']

  compileNode: (o) ->
    props = @properties
    return (if @front then '({})' else '{}') unless props.length
    if @generated
      for node in props when node instanceof Value
        throw new Error 'cannot have an implicit value in an implicit object'
    idt         = o.indent += TAB
    lastNoncom  = @lastNonComment @properties
    props = for prop, i in props
      join = if i is props.length - 1
        ''
      else if prop is lastNoncom or prop instanceof Comment
        '\n'
      else
        ',\n'
      indent = if prop instanceof Comment then '' else idt
      if prop instanceof Value and prop.this
        prop = new Assign prop.properties[0].name, prop, 'object'
      if prop not instanceof Comment
        if prop not instanceof Assign
          prop = new Assign prop, prop, 'object'
        # Workaround for: (prop.variable.base or prop.variable).asKey = yes
        vari = prop.variable.base or prop.variable
        vari.asKey = yes
      indent + prop.compile(o, LEVEL_TOP) + join
    props = props.join ''
    obj   = "{#{ props and '\n' + props + '\n' + @tab }}"
    if @front then "(#{obj})" else obj

  assigns: (name) ->
    for prop in @properties when prop.assigns name then return yes
    no

''')}

  @Test def void testClass() {
    ok('''#### Class

# The CoffeeScript class definition.
# Initialize a **Class** with its name, an optional superclass, and a
# list of prototype property assignments.
exports.Class = class Class extends Base
  constructor: (@variable, @parent, @body = new Block) ->
    @boundFuncs = []
    @body.classBody = yes

  children: ['variable', 'parent', 'body']

  # Figure out the appropriate name for the constructor function of this class.
  determineName: ->
    return null unless @variable
    decl = if tail = last @variable.properties
      tail instanceof Access and tail.name.value
    else
      @variable.base.value
    decl and= IDENTIFIER.test(decl) and decl

  # For all `this`-references and bound functions in the class definition,
  # `this` is the Class being constructed.
  setContext: (name) ->
    @body.traverseChildren false, (node) ->
      return false if node.classBody
      if node instanceof Literal and node.value is 'this'
        node.value    = name
      else if node instanceof Code
        node.klass    = name
        node.context  = name if node.bound

  # Ensure that all functions bound to the instance are proxied in the
  # constructor.
  addBoundFunctions: (o) ->
    if @boundFuncs.length
      for bvar in @boundFuncs
        lhs = (new Value (new Literal "this"), [new Access bvar]).compile o
        @ctor.body.unshift new Literal "#{lhs} = #{utility 'bind'}(#{lhs}, this)"

  # Merge the properties from a top-level object as prototypal properties
  # on the class.
  addProperties: (node, name, o) ->
    props = node.base.properties[0..]
    exprs = while assign = props.shift()
      if assign instanceof Assign
        base = assign.variable.base
        delete assign.context
        func = assign.value
        if base.value is 'constructor'
          if @ctor
            throw new Error 'cannot define more than one constructor in a class'
          if func.bound
            throw new Error 'cannot define a constructor as a bound function'
          if func instanceof Code
            assign = @ctor = func
          else
            @externalCtor = o.scope.freeVariable 'class'
            assign = new Assign new Literal(@externalCtor), func
        else
          if assign.variable.this
            func.static = yes
          else
            assign.variable = new Value(new Literal(name), [(new Access new Literal 'prototype'), new Access base ])
            if func instanceof Code and func.bound
              @boundFuncs.push base
              func.bound = no
      assign
    compact exprs

  # Walk the body of the class, looking for prototype properties to be converted.
  walkBody: (name, o) ->
    @traverseChildren false, (child) =>
      return false if child instanceof Class
      if child instanceof Block
        for node, i in exps = child.expressions
          if node instanceof Value and node.isObject(true)
            exps[i] = @addProperties node, name, o
        child.expressions = exps = flatten exps

  # Make sure that a constructor is defined for the class, and properly
  # configured.
  ensureConstructor: (name) ->
    if not @ctor
      @ctor = new Code
      @ctor.body.push new Literal "#{name}.__super__.constructor.apply(this, arguments)" if @parent
      @ctor.body.push new Literal "#{@externalCtor}.apply(this, arguments)" if @externalCtor
      @body.expressions.unshift @ctor
    @ctor.ctor     = @ctor.name = name
    @ctor.klass    = null
    @ctor.noReturn = yes

  # Instead of generating the JavaScript string directly, we build up the
  # equivalent syntax tree and compile that, in pieces. You can see the
  # constructor, property assignments, and inheritance getting built out below.
  compileNode: (o) ->
    decl  = @determineName()
    name  = decl or @name or '_Class'
    name = "_#{name}" if name.reserved
    lname = new Literal name

    @setContext name
    @walkBody name, o
    @ensureConstructor name
    @body.spaced = yes
    @body.expressions.unshift new Extends lname, @parent if @parent
    @body.expressions.unshift @ctor unless @ctor instanceof Code
    @body.expressions.push lname
    @addBoundFunctions o

    klass = new Parens Closure.wrap(@body), true
    klass = new Assign @variable, klass if @variable
    klass.compile o

''')}

}
