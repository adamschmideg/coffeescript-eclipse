package csep.tests

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
		# remark
		a = 1
		''')
		ok('''
		if good
		  # do it
		  doIt()
		''')
	}
	
	@Test
	def void testStringInterpolation() {
		ok('"I am #{ name }"')
		error('"It is #{ ++1 }"')
	}
}