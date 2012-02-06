
* Display parse tree for debugging purposes
	* Trying [Implement toString with Xtext's Serializer][1].
	  The parse tree I get is flat without indentation when not doing the `toString` replacement magic,
	    just overriding `AdditionImpl.toString()`.
	  So displaying the parse tree of `1+2` just gives `1+2`.
	  (I had to use the built-in lexer, it doesn't seem to work with my external beaver-based lexer.)
* Display parse tree with structure
	* I'll have to implement manually an AST displayer.
	  The serializer approach doesn't work, because [it's supposed to output a parseable string][2]
	* Done.  See `csep.parser.Helper.stringify`.

* Overriding `nextToken()` in Lexer is not enough.
  The parser seems to use only the first token when building the AST.
	* There are some differences between the token returned by the internal lexer and the token by the aptana lexer.
	  The differences include: whitespace handling; EOF; type/id of operators.
	* The difference of whitespace and EOF handling seem to cause no problem.
* Why is the type of operators different in BeaverToken?
	* A literal '+' within a rule is not the same as using PLUS within the same rule.
	  (Note: the aptana lexer returns a MATH token both for '*' and for '/')

* How to vizualize the LALR grammar of coffeescript?
	* I asked about [visualizing LALR grammar] on Stackoverflow
	* I found [GOLD parser][4] which has an IDE, but runs only on Windows.
  	  But I may check it out.

* Learning ANTLR
  * Tried to use the generated python target, but it seemed difficult on
   Ubuntu: there is no python binding for antlr3, the java version
   doesn't match with the downloadable python bundles.
   So I abandon this track, and go back to painful old java.
 * The difficulty seems to be left-factoring expressions.
   I asked for [help with left-factoring expressions][5] at Stackoverflow.
 * I got an answer to my question which doesn't fix the problem but
   helped me understand it.
   The issue boils down to this: write a simple expression language with
   function calls.
   An expression can be an operation or a function call.
   The operand in an operation can be any expression, including a
   function call.
 * The lesson learned is this: if I have `expression: operation | whatever`,
   and `whatever` can be reached via `operation`, then omit it from the
   right-hand side of `expression`.
 * I'm not sure how close I can stay to the original LR grammar.

* [Convert Antlr grammar to Xtext][6]
 * Terminals should be prefixed with `terminal`
 * The first rule should be the root, it cannot be a terminal
 * Fix the error `Cannot create datatype X` by including
   `import "http://www.eclipse.org/emf/2002/Ecore" as ecore` right after
   the first line
 * Fix the error `Generated package X may not be empty` by including a
  feature in the root rule.
  If you have `Root: Foo`, replace it with `Root: bar=Foo`.
 * Rules are case-insensitive, you cannot have a terminal ID and a rule
  Id in the same file.
 * Optionally you may want to capitalize the rule names to follow the
  java convention.

* Running headless with make
  * I'm going to abandod this because eclipse packages are versioned with a build date too which is difficult to handle in a Makefile.
  * Possible (and time-consuming) solutions are
    * use some dependency management tool, such as ivy
    * do some bash scripting magic to find foo-[version]-v[build-date].jar and copy it as foo-[version].jar

* Terminals in a separate file.
  Well, [I've struggled with it][7].

* Partial parsing instead of failure: `1 = 2` should fail, but it the first token gets parsed, the rest discarded without any error message.
  * Done, using `org.eclipse.xtext.junit.AbstractXtextTests.getModelAndExpect()`

* How is THEN handled?
  Fortunately, it seems to be handled by the lexer, making it a block

* Add postfix conditional.
  The problem is "num = 2 if even" is parsed as an assignment "num = 2", then the next IF token is considered an error.
  It may be handled the same way as the existence operator.

* Study XBase to see how they handle expressions.
  This is how to get to the XBase grammar:
    * Create a new XText project
    * Append `with org.eclipse.xtext.xbase.Xbase` to the grammar definition line
    * Navigate to `Xbase` and press F3

 My first impression is they follow this strategy
 * Sort every rule related to expressions by its precedence
 * Create a chain of rules from lowest to highest precedence
 * The highest precendenc rule refers to `PrimaryExpression`
 * `PrimaryExpression` has all the expressions which consume at least one token from the stream

 I don't know why unary expression is included in the chain, and not in the PrimaryExpression.

* Some grammar features worth studying
  * Cross-reference denoted as `[Foo]`, for example `'Class: class' name=ID 'extends' superClass=[Class];`
    The referrred `[Class]` will not create a new class name, but expects an existing name
  * Syntactic predicate denoted as `=>`.

* Debugging Xtext grammar with Antlrworks
  * The grammar generated doesn't compile, it's looking for a class
   `DebugAbstractInternalAntlrParser` which cannot be found anywhere
  * Xtext [claims to have new stuff for debugging][8].
    New stuff includes a railroad diagram (`Views->Xtext->Xtext Syntax Graph`).
    It also refers to a new generator fragment, `org.eclipse.xtext.generator.parser.antlr.DebugAntlrGeneratorFragment`
    which I don't know yet how to use.
  * It was just a single line of code, and `csep/src-gen/csep/parser/antlr/internal/DebugInternalCoffeeScript.g`
    gets generated which can be opened and debugged with Antlrworks
    seamlessly

* Warning: "Decision can match input such as RULE\_IF using multiple alternatives: 1, 2"
  * Before the previous commit it almost disappeared, then it came back.
  * The answer to this [Stackoverflow question][9] says, you can set
   `option=greedy` in Antlr, but this is not an option here, because the
   Antlr grammar is generated.
  * This issue has no impact on parsing itself, it affects only the way
  how AST will be constructed.
  So I'm going to deal with this question later, in the AST construction phase.
  * A dirty quick-fix in the generated DebugInternalCoffeeScript is to make
  this change: in `rulePostfixIf` append a caret to
  `(RULE_POSTIF|RULE_IF)`, so it becomes `(RULE_POSTIF|RULE_IF)^`.
  I don't know how to achieve this in the Xtext grammar.
  I don't know why this fixes the Antlr grammar, either.

* Handle dynamic variables.
  It's more difficult than it first seemed, see [my Stackoverflow question about handling dynamic variables][10]

* There is a difference between the generated debug grammar and the
 generated proper grammar, even to the extent that the debug grammar
 works, the proper one doesn't compile.
 See [my question at Stackoverflow][11] for more.
 * Just installed an [Antlr Eclipse plugin][12] to compare the grammars
 * I found the reason:
  
    Assignment:
      {Assignment} (=>(left...));

  The `{Assignment}` part will be converted to an action in the internal
  grammar, and to an empty group before the syntactic predicate in the
  stripped off grammary, and to nothing in the debug grammar.
  The empty group seems to invalidate the syntactic predicate.
  I removed the `{Assignment}`, so an uglier AST will be produced.

* Another trick I tried for assignment is to treat property assignment
 differently, so `a.b = 3` is thought of as `a (.b =) 3`.
 The actual Xtext is

    Assignment returns Expression: 
     {Assignment} (=>(left=(Id | Array) operator=(EQUAL | COMPOUND_ASSIGN)) right=Expression) |
     LogicOp (=>({PropertyAssignment.left=current} prop=PropertyAccess operator=EQUAL) right=Expression)?;

 Unfortunately, it gives the same non-LL(*) decision error.

* Linking.
  Introduced `IdRef` and a dummy scoping mechanism.
  It shuts up linking problems and enables most coffeescript constructs.
  The only drawback for the moment seems to be that variables cannot be reassigned.
  * It causes some tricky hidden problems (StringIndexOutOfBoundsException), so I'm going another way
  See http://www.eclipse.org/forums/index.php?t=msg&th=172032&start=0&
  and http://zarnekow.blogspot.com/2010/06/customizing-error-messages-in-xtext-10.html

* Testing with automatically generated sentences fed to `coffee`
  See my question: http://stackoverflow.com/questions/8478320/generate-syntactically-correct-sentences-from-an-antlr-grammar

* Syntax highlight of keywords.
  It should be automatic, but it matters how terminals are defined in
  the grammar, consider this:

    ImportStatement: 'import' ID;

    ExportStatement: EXPORT ID;
    terminal EXPORT: 'export';

  In the first case, `import` will be a Keyword object; in the second
  case, it will be a `Terminal` object.

  There is a way to assign a highlighting category to each token, see
  http://www.eclipse.org/forums/index.php?t=msg&goto=479001&
  This is what I just started.
  `Regex` can be configured in the preferences now, but it doesn't get
  highlighted in the source code.

* Tricky highlight issues
 - how to handle 'then' which doesn't get a token, but is replaced with
   a block?
 - how to handle string interpolation?  `"before #{ x } after"` is
   tokenized as `[( (] [STRING "before "] [+ +] [IDENTIFIER x] [+ +] [STRING " after"] [) )]`

* Variable scoping.  The strategy is
 - The left-hand side of assignment contains `Id`.
   It causes a `Duplicate ForValue` error in case of reassignment.
   Solution: simply hide this error.
 - The right-hand side of assignment contains `IdRef`.
   It causes a `Couldn't resolve reference to xxx` error when using an
   imported or built-in object.
   Solution: change this error to a warning.
   Optionally, add built-in objects to global scope.
 - Change compound left-hand sides to contain only `Id`s.
   It means introducing new rules: AssignableArray and AssignableObject.
   It wouldn't accept syntactically correct, but erroneous
   expressions, such as `[a+1] = 3`

* Syntax highlight of comments
 - Xtext uses `TokenScanner` when doing syntax highlighting which seems
   to ignore the lexer and to use the xtext grammar directly.
   I asked about it: http://www.eclipse.org/forums/index.php/m/772060/#msg_772060
   I may check out semantic highlighting instead.
 - It was easier to change the terminals in the xtext grammar and stay
   with the current lexical highlighter.

* Display tokenization errors correctly.
 - An exception may be thrown either by the scanner, or by the rewriter.
   In the first case, we have a stream of valid tokens up to the point
   where the exception was thrown.
   In the second case, the rewriter iterates over the tokens, so we need
   to throw an exception containing the index of the problematic token.
   Eclipse doesn't treat INVALID_TOKEN_TYPE in a special way, just
   passes it on to the parser.
   So it would be correct if the Lexer added a Diagnostic error directly to the
   XtextResource.
 - The errors in XtextResource are cleared in the parsing phase, so
   adding a Diagnostic error by the lexer would be lost.

* Issues
 - One-character long string not recognized in editor -- Done
 - Double quotation mark within singly quoted string -- Done
 - Add standard objects and functions (Array, String, Math, console, etc) to global scope 
 - Add function parameters to local scope, they should be Id-s rather
   than IdRef-s
 - Explore StringIndexOutOfBoundsException problem when resolving an
   xtext link -- Done
 - `break` keyword -- Done
 - Comments are ignored by lexer, so error locations are shifted by the
   length of preceding comment -- Done
 - Weird reconciler exception when opening `nodes.coffee` -- Done
 - Use `.coffee` as file extension -- Done
 - Accept empty file

* The file has to be modified to activate xtext grammar check

* Problems with tokens got from Aptana scanner
 - It uses short numbers internally which easily overflows, resulting
   token positions restarted after 4095 -- oops, not true, it simply
   uses offsets
 - The rewriter calculates wrong positions for inserted tokens,
   sometimes resulting in a token with startIndex > stopIndex
 - Comments get ignored, since it can't use a hidden channel (probably
   unsupported by the Beaver API)
 - It doesn't seem to keep track of line number and positions within
   line

* The case of the ugly StringIndexOutOfBoundsException:
  When an issue is raised, its content is calculated from the whole
  text indexed by the token where occurred.
  But the whole text is calculated by taking the input char stream and
  reading it from the first token.
  If the first token was hidden, the beginning of the text is chopped,
  and we'll get a shorter full text.
  So a substring with the proper indexes will fail.

* Most annoying issue:
  The editor works inconsistently.
  - Sometimes `No viable alternative at EOF` for an empty or almost
    empty file
  - When opening a file, it's not checked, only after it's been modified
  - Saving a file removes warnings
  - The warnings are not shown in the `Problems` window

* Hovering sometimes throws an NPE, maybe when I hover over a missing crossref

* Scoping, second round.
  Reading this series of posts: http://blogs.itemis.de/stundzig/archives/773
  I just realized that functions have dynamic scoping, but loops seem to
  have lexical scoping.

  - Debugging into `AbstractDeclarativeScopeProvider` and
    `ImportedNamespaceAwareLocalScopeProvider` makes me think that
    scopes are queries based on how AST nodes are nested.
    I suspect that my `IdRef` references are hidden in a side-branch in
    the AST, so their referees cannot be found by simply querying
    upwards.
    My next step could be just to make this work properly

     a = 0
     b = a + 1

  - Built-in namespaces/objects.
  I have to make import (aka. require) work in the first place.
  Then I can think about default global imports.
  Well, it seems to much work to do it properly, resolve exports in the
  imported file, etc.
  It would add only a limited autocompletion support, anyway, since we
  can't infer the return type of a function call.

* Read original coffeescript source files.
  There seems to be some errors with scanner which cannot handle all of
  them properly.

* Probably interdependent issues
  - Files don't get validated automatically (http://www.eclipse.org/forums/index.php/m/774437/#msg\_774437)
  - Errors are not displayed in the Problems tab
    - Well, maybe a workaround, but you can manually validate the file
      selecting Validate from the context menu.
  - Saving or modifying a file may change whether or not problems are
    shown in the editor

* An ugly bug that took me hours to hunt down: when there's an error in
 the file, any change to the file will generate a number of errors
 without any message.
 This is due to the bug that somewhere deep down trailing zero bytes are
 appended to the source text to be parsed.
 My hunch is it's related to replacing the text from the previous parse
 result using a replace region.
 I couldn't find the exact source of the problem, but could make a
 simple workaround.

* Auto-completion
  - The tricky thing is: when we try to give a completion for `Math.`,
  the context is an IdRef, but it can't find any referee named `Math`.
  So we have no name for the lookup.

* String interpolation mystery.
  The right-hand side of `first` and `second` in the following snippet should be parsed the same,
  because the same tokens are generated.

    name = "Joe"
    first = ("I am " + name)
    second = "I am #{name}"

  But in the second case, a warning is given that the reference cannot be found.
  There is also an offset problem here, the warning message is `Couldn't resolve reference to Id '#{na'`

* Annoying editor bug.
  This is how I could reproduce a problem most of the times (it may
  depend on typing speed).
  Enter the following snippet into an empty file

    a=1
    class C
       b:3

  (The property `b` after `class C` will be auto-indented).
  Now, go back and insert a space before `1` in the first line.
  If it doesn't cause a syntax error, delete the space you just
  inserted.
  You are supposed to get a `No viable alternative at input '}'` error.
  In the meanwhile, the hosting Eclipse instance logs errors every now
  and then:
  `PartialParsingHelper  - Invalid replace region`.
  - One cause is a bad coincidence: the scanner adds OUTDENT and
    TERMINATOR tokens with a wrong offset.
    The antlr stream doesn't check the upper bound.
    The result is: when the underlying parser gets the whole stream as
    string, it reads a substring after the end of the stream, resulting
    in trailing zero bytes.
  - A related problem is that the scanner added and removed some
    whitespaces to make its job easier, but it didn't fix the offsets
    accordingly.

* Respond to bug reports.

* Resolution warning.  The minimal snippet to reproduce it is

     a = 1
     #remark
     b = a

  It gives no warning when opening with a new Eclipse instance or when
  checking in a test case.
  If you add a trailing space (or if there is one, remove it), you'll
  get the warning.
  It seems to trying to resolve `a` as a reference, but with a wrong offset.
  It may be related to Xtext calculating a ReplaceRegion when parsing.

  - Parse element with wrong offsets are produced in `XtextResource.update` after calling `parser.reparse()`
  - Workaround: don't use partialParser, because it seems to be responsible for the wrong offsets.
    This may result in some performance penalty for larger files.

* Asked about issues at Eclipse forum
  - [Debug grammar generation gives error but seems to work](http://www.eclipse.org/forums/index.php/m/791928/)
  - [Partial parsing can't resolve some references](http://www.eclipse.org/forums/index.php/m/791944/) 

  [1]: http://jevopisdeveloperblog.blogspot.com/2011/03/implement-tostring-with-xtexts.html
  [2]: http://www.eclipse.org/Xtext/documentation/2_1_0/100-serialization.php#serializationcontract 
  [3]: http://stackoverflow.com/questions/8154790/visualize-lalr-grammar
  [4]: http://goldparser.org/
  [5]: http://stackoverflow.com/questions/8263772/left-factoring-grammar-of-coffeescript-expressions
  [6]: http://stackoverflow.com/questions/8279790/convert-simple-antlr-grammar-to-xtext
  [7]: http://stackoverflow.com/questions/8302333/xtext-grammar-in-two-files
  [8]: http://eclipse.org/Xtext/documentation/indigo/new_and_noteworthy.php
  [9]: http://stackoverflow.com/questions/7954142/antlr-decision-can-match-input-using-multiple-alternatives
  [10]: http://stackoverflow.com/questions/8387818/handle-dynamic-variable-with-xtext-grammar
  [11]: http://stackoverflow.com/questions/8438755/stripping-actions-from-antlr-grammar-changes-its-parsing-algorithm
  [12]: http://antlrv3ide.sourceforge.net/
