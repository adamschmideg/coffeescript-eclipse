
This is a project to provide an Eclipse plugin for [CoffeeScript][coffeescript], using [Xtext][xtext].
Development uses Xtext 2.1.
It just started, so you may be interested in the roadmap, including where we are at the moment.

## Starting point
There are quite a few [editors with CoffeeScript support][coffeescript_editors],
 but most of them have just a little more than syntax highlighting.
Only three of them do actual parsing:

 - [CoffeeBrew][coffeebrew] for IntelliJ IDEA which has added some [official support of coffeescript][idea_rubymine] based on CoffeeBrew.
 - [Netbeans plugin][netbeans_plugin] which seems quite mature
 - [Aptana Studio3 plugin][aptana_plugin]

Besides, there is CoffeeScript's own compilation method as a good source of ideas.
Let's see what we can learn from them or how they could be used.

CoffeeBrew is based on IntelliJ's own packages, using a Java-based lexer, and processes a [flex file][coffeebrew_flex].
It's not clear yet if whether and how it can handle significant whispaces of coffeescript,
 but at first it looks a promising starting point.
The only downside is, it's development halted in favor of the official coffeescript support.
I couldn't find how parsing is done here.

Regarding the Netbeans plugin, it seems to have implemented both lexing, and parsing in [pure Java code][netbeans_lexer_parser],
 making use of Netbeans' own parsing framework.
It would be quite difficult to use it, either directly (importing it), or indirectly understanding the algorithm.

The Aptana Studio3 plugin is based on the [Beaver parser generator][beaver] with a Java [scanner class][aptana_lexer] which is either generated,
 or crafted by hand.
It is accompanied by a [rewriter class][aptana_rewriter] which takes care of indentation and other niceties.
Since they extend the Beaver API, they may be not directly usable, but more on that later.

CoffeeScript itself sports a [hand-written lexer in coffeescript][coffeescript_lexer]
 and a hand-written rewriter to handle indentation and some shortcut features of the language.
Its parser is a [Jison, a javascript implementation of Bison][jison], processing an [annotated grammar file][coffeescript_grammar].

  [aptana_plugin]: https://github.com/aptana/studio3/tree/development/plugins/com.aptana.editor.coffee
  [aptana_lexer]: https://github.com/aptana/studio3/blob/development/plugins/com.aptana.editor.coffee/src/com/aptana/editor/coffee/parsing/lexer/CoffeeScanner.java
  [aptana_rewriter]: https://github.com/aptana/studio3/blob/development/plugins/com.aptana.editor.coffee/src/com/aptana/editor/coffee/parsing/lexer/CoffeeRewriter.java 
  [beaver]: http://beaver.sourceforge.net/
  [coffeebrew]: https://github.com/netzpirat/coffee-brew
  [coffeebrew_flex]: https://github.com/netzpirat/coffee-brew/blob/master/src/org/coffeebrew/lang/lexer/coffee-script.flex
  [coffeescript]: http://www.coffeescript.org
  [coffeescript_editors]: https://github.com/jashkenas/coffee-script/wiki/Text-editor-plugins
  [coffeescript_grammar]: http://jashkenas.github.com/coffee-script/documentation/docs/grammar.html
  [coffeescript_lexer]: https://github.com/jashkenas/coffee-script/blob/master/src/lexer.coffee
  [idea_rubymine]: http://confluence.jetbrains.net/display/RUBYDEV/RubyMine+3.2+EAP+%28build+107.235%29+Release+Notes
  [jison]: http://zaach.github.com/jison/
  [netbeans_plugin]: https://github.com/dstepanov/coffeescript-netbeans
  [netbeans_lexer_parser]: https://github.com/dstepanov/coffeescript-netbeans/tree/master/src/coffeescript/nb
  [xtext]: http://www.xtext.org

## First step: a custom lexer
The main issue with coffeescript is that it uses significant whitespaces.
It's not obvious how to handle it with Xtext, but there are a few attempts at resolving it.
See [Pythonisque indent/dedent block structure for a Xtext DSL][indent_xtext]; a 
 [Stackoverflow question about Xtext with significant/semantic whitespace][so_xtext_indent]
 and the [todotext implementation][todotext] referred to in an answer.

Conclusion and status: the Aptana scanner got integrated into Xtext successfully.

  [indent_xtext]: http://eclipsesnippets.blogspot.com/2009/08/pythonisque-indentdedent-block.html
  [so_xtext_indent]: http://stackoverflow.com/questions/7167834/xtext-grammar-for-language-with-significant-semantic-whitespace
  [todotext]: http://code.google.com/a/eclipselabs.org/p/todotext/

## Second step: implement an LL grammar for the parser
Other implementations use LALR parsers, but Xtext is based on Antlr which is an LL parser.
Transforming a LALR grammar to an LL one is not a mechanic task,
 even a simple expression language feels challenging at first, see [Parsing Expressions with Xtext][expr_xtext].
There are some basic rules to follow [Converting a Grammar from LALR to LL][lalr_to_ll].

On the other hand, some good test cases are needed, too.
[CoffeeScript tests][coffee_tests] are geared toward execution and not only parsing, and they quite coarse grained for our purposes now.
The [parser tests of Aptana][aptana_tests] may be a good starting point with some drawbacks:

 - The Aptana parser produces a different AST than the one generated by Xtext
 - The test cases suffer from Java's lack of multiline strings.

Conclusion and status: it parses almost anything.
At least all examples in the coffeescript documentation folder.
There are two cases it cannot handle, though, post-increment and
assignment to a deeply nested property.

    # Not working
    drinkBeer(glasses++)  

    # Workaround
    drinkBeer(glasses)
    glasses += 1

    # Not working
    my.secret.money = 1000

    # Workaround
    tmp = my.secret
    tmp.money = 1000

I guess they don't hurt that much...

  [expr_xtext]: http://dev.eclipse.org/viewcvs/viewvc.cgi/org.eclipse.tmf/org.eclipse.xtext/plugins/org.eclipse.xtext.doc/help/expressions.html?root=Modeling_Project&view=co
  [lalr_to_ll]: http://javadude.com/articles/lalrtoll.html
  [coffee_tests]: https://github.com/jashkenas/coffee-script/tree/master/test
  [aptana_tests]: https://github.com/aptana/studio3/blob/development/tests/com.aptana.editor.coffee.tests/src/com/aptana/editor/coffee/parsing/CoffeeParserTest.java

## Third step: linking, scoping and meaningful messages
Well, we'll see.
