
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

  [1]: http://jevopisdeveloperblog.blogspot.com/2011/03/implement-tostring-with-xtexts.html
  [2]: http://www.eclipse.org/Xtext/documentation/2_1_0/100-serialization.php#serializationcontract 
  [3]: http://stackoverflow.com/questions/8154790/visualize-lalr-grammar
  [4]: http://goldparser.org/
  [5]: http://stackoverflow.com/questions/8263772/left-factoring-grammar-of-coffeescript-expressions
