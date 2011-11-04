
This is a project to provide an Eclipse plugin for [CoffeeScript][coffeescript], using [Xtext][xtext].
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

