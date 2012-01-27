package csep.tests.basic

import csep.tests.ParserTestBase
import org.junit.Test

class ControlFlowTest extends ParserTestBase {
	
	@Test
	def void testBasicConditionals() {
    ok('''
      if a
        1
      ''')
    ok('''
      if a
        1
      else if b
        2
      ''')
    ok('''
      if a
        1
      else if b
        2
      else
        3
      ''')

    ok('''
      unless a
        1
      ''')
    ok('''
      unless a
        1
      else unless b
        2
      ''')
    ok('''
      unless a
        1
      else unless b
        2
      else
        3
      ''')
    ok('''
      if a
        1
      else unless b
        2
    ''')
    error('if a b')
    error('if a: b')
    error('''
      if a
      b
      ''')
  }

  @Test
  def void testReservedWords() {
    ok('if false then undefined else null')
  }

  @Test
  def void testSingleLineConditional() {
    ok('if something then 1 else 2')
    ok('unless nothing then 1 else 2')
  }

  @Test
  def void testNestedConditional() {
    ok('''
      if a
        unless b
          if c then d else e
       ''')
  }

  @Test
  def void testEmptyConditionalBody() {
    ok('''
      if a
      else if b
      else
      ''')
  }

  @Test
  def void testNestedSingleLineConditional() {
    ok('if nothing then oops else b = if 0 then oops else nonce')
    ok('if nothing then oops else (if 0 then oops else nonce)')
    ok('if something then id(if nothing then oops else nonce)')
  }

  @Test
  def void testPostfixConditional() {
    ok('num = 42 if asked')
    ok('num = 0 unless more')
    ok('2 if you and me')
    ok('you + me if together')
  }

  @Test
  def void testForComprehension() {
  	ok('2*i for i in numbers')
  	ok('2*i for i in [0..5]')
  	ok('2*i for i in numbers when i<4')
  	ok('2*i for i in [0..5] when i<4')
  	ok('"${key} is ${value}" for key, value of foo: 1, bar: 2')
  }
  
  @Test
  def void testForLoop() {
  	ok('''
  	  sum = 0
  	  for i in numbers
  	    sum += i
  	''')
  	ok('''
  	  positiveSum = 0
  	  for i in numbers when i>0
  	    positiveSum += i
  	''')
  	ok('''
  	  doubles = for i in numbers
  	    2*i
  	''')
  }
  
  @Test
  def void testWhile() {
  	ok('''
  	  while i>0
  	  	i -= 1
  	''')
  	ok('buy() while supply > demand')
  }
  
  @Test
  def void testTryCatch() {
  	ok('''
      content = try
        readFile()
      catch IOException
        null
  	''')
  	ok('''
  	  try
  	  	missionImpossible()
  	  catch error
  	    print error
  	  finally
  	  	cleanUp()
  	''')
  }
  
  @Test
  def void testSwitch() {
  	ok('''
  	  switch day
  	    when "Mon" then go work
  	    when "Fri", "Sat"
  	      if day is bingoDay
  	        go bingo
  	    else go work
  	''')
  }
  
  @Test
  def void testBreakContinue() {
  	ok('''
  	  while true
  	  	if somethingHappened()
  	  	  break
  	  	else if goOn()
  	  	  continue
  	  	justWait()
  	''')
  }
  
  @Test
  def void testReturnIf() {
  	ok('return if yes')  	
  	ok('''
  		if yes
  			return 1
  	''')
  	ok('''
  		if yes
  			return
  	''')
  	ok('return 1 if yes')  	
  }
}
