package csep.tests.basic

import csep.tests.ParserTestBase
import org.junit.Test

class LiteralTest extends ParserTestBase {
	
	@Test
	def void testEmbedJS() {
		ok('`var i`')
	}
	
	@Test
	def void testRegex() {
		ok('/.*/')
		ok('''
		  ///
		    .*
		  ///
		''')
	}
	
	@Test
	def void testHerecomment() {
		ok('''
		  ###
		    1/
		  ###
		''')
	}
	
	@Test
	def void testComment() {
		ok('''
		# before
		a = 0
		''')
		ok('''
		a = 0
		# between
		a = 1
		''')
		ok('''
		a = 0
		# after
		''')
		ok('''
		if good
		  # do it
		  doIt()
		''')
		ok('''
		fun = (x) ->
		  # do something
		  x + 1
		''')
		okNoWarning('''
			a = 1
			#remark
			bb = a
		''')	
	}
	
	@Test
	def void testStringInterpolation() {
		ok('me = "I am #{firstName} #{lastName}"')
		error('"It is #{ ++1 }" exactly')
		okNoWarning('''
			name = "Joe"
			me = "I am #{name}"
		''')
	}
}