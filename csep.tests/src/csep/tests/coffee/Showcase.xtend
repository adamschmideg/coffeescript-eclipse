package csep.tests.coffee

import csep.tests.ParserTestBase
import org.junit.Test
/**
 * Files from {@link https://github.com/jashkenas/coffee-script/tree/master/documentation/coffee}
 * converted to test cases
 * @author adam
 *
 */
public class Showcase extends ParserTestBase {

    @Test
    def void test_scope() {
      ok('''
        outer = 1
        changeNumbers = ->
          inner = -1
          outer = 10
        inner = changeNumbers()
      ''')
    }
    

    @Test
    def void test_interpolation() {
      ok('''
        author = "Wittgenstein"
        quote  = "A picture is a fact. -- #{ author }"

        sentence = "#{ 22 / 7 } is a decent approximation of π"
      ''')
    }
    

    @Test
    def void test_expressions_try() {
      ok('''
        alert(
          try
            nonexistent / undefined
          catch error
            "And the error is ... #{error}"
        )
      ''')
    }
    

    @Test
    def void test_embedded() {
      ok('''
        hi = `function() {
          return [document.title, "Hello JavaScript"].join(": ");
        }`
      ''')
    }
    

    @Test
    def void test_object_extraction() {
      ok('''
        futurists =
          sculptor: "Umberto Boccioni"
          painter:  "Vladimir Burliuk"
          poet:
            name:   "F.T. Marinetti"
            address: [
              "Via Roma 42R"
              "Bellagio, Italy 22021"
            ]
        
        {poet: {name, address: [street, city]}} = futurists
      ''')
    }
    

    @Test
    def void test_prototypes() {
      ok('''
        String::dasherize = ->
          this.replace /_/g, "-"
      ''')
    }
    

    @Test
    def void test_range_comprehensions() {
      ok('''
        countdown = (num for num in [10..1])
      ''')
    }
    

    @Test
    def void test_array_comprehensions() {
      ok('''
        # Eat lunch.
        eat food for food in ['toast', 'cheese', 'wine']
        
        # Fine dining
        courses = ['salad', 'entree', 'dessert']
        menu index + 1, dish for dish, index in courses
        
        # Health conscious meal
        foods = ['broccoli', 'spinach', 'chocolate']
        eat food for food in foods when food isnt 'chocolate'
      ''')
    }
    

    @Test
    def void test_cake_tasks() {
      ok('''
        fs = require 'fs'
        
        option '-o', '--output [DIR]', 'directory for compiled code'
        
        task 'build:parser', 'rebuild the Jison parser', (options) ->
          require 'jison'
          code = require('./lib/grammar').parser.generate()
          dir  = options.output or 'lib'
          fs.writeFile "#{dir}/parser.js", code
      ''')
    }
    

    @Test
    def void test_heredocs() {
    	ok(
        "html = " +
        "  '''" + 
        "    <strong>" +
        "      cup of coffeescript" +
        "    </strong>" +
        "  '''")
    }

    @Test
    def void test_patterns_and_splats() {
      ok('''
        tag = "<impossible>"
        
        [open, contents..., close] = tag.split("")
      ''')
    }
    

    @Test
    def void test_objects_and_arrays() {
      ok('''
        song = ["do", "re", "mi", "fa", "so"]
        
        singers = {Jagger: "Rock", Elvis: "Roll"}
        
        bitlist = [
          1, 0, 1
          0, 0, 1
          1, 1, 0
        ]
        
        kids =
          brother:
            name: "Max"
            age:  11
          sister:
            name: "Ida"
            age:  9
      ''')
    }
    

    @Test
    def void test_try() {
      ok('''
        try
          allHellBreaksLoose()
          catsAndDogsLivingTogether()
        catch error
          print error
        finally
          cleanUp()
      ''')
    }
    

    @Test
    def void test_expressions() {
      ok('''
        grade = (student) ->
          if student.excellentWork
            "A+"
          else if student.okayStuff
            if student.triedHard then "B" else "B-"
          else
            "C"
        
        eldest = if 24 > 21 then "Liz" else "Ike"
      ''')
    }
    

    @Test
    def void test_default_args() {
      ok('''
        fill = (container, liquid = "coffee") ->
          "Filling the #{container} with #{liquid}..."
      ''')
    }
    

    @Test
    def void test_multiple_return_values() {
      ok('''
        weatherReport = (location) ->
          # Make an Ajax request to fetch the weather...
          [location, 72, "Mostly Sunny"]
        
        [city, temp, forecast] = weatherReport "Berkeley, CA"
      ''')
    }
    

    @Test
    def void test_fat_arrow() {
      ok('''
        Account = (customer, cart) ->
          @customer = customer
          @cart = cart
        
          $('.shopping_cart').bind 'click', (event) =>
            @customer.purchase @cart
      ''')
    }
    

    @Test
    def void test_aliases() {
      ok('''
        launch() if ignition is on
        
        volume = 10 if band isnt SpinalTap
        
        letTheWildRumpusBegin() unless answer is no
        
        if car.speed < limit then accelerate()
        
        winner = yes if pick in [47, 92, 13]
        
        print inspect "My name is #{@name}"
      ''')
    }
    

    @Test
    def void test_expressions_comprehension() {
      ok('''
        # The first ten global properties.
        
        globals = (name for name of window)[0...10]
      ''')
    }
    

    @Test
    def void test_switch() {
      ok('''
        switch day
          when "Mon" then go work
          when "Tue" then go relax
          when "Thu" then go iceFishing
          when "Fri", "Sat"
            if day is bingoDay
              go bingo
              go dancing
          when "Sun" then go church
          else go work
      ''')
    }
    

    @Test
    def void test_soaks() {
      ok('''
        zip = lottery.drawWinner?().address?.zipcode
      ''')
    }
    

    @Test
    def void test_expressions_assignment() {
      ok('''
        six = (one = 1) + (two = 2) + (three = 3)
      ''')
    }
    

    @Test
    def void test_object_comprehensions() {
      ok('''
        yearsOld = max: 10, ida: 9, tim: 11
        
        ages = for child, age of yearsOld
          "#{child} is #{age}"
      ''')
    }
    

    @Test
    def void test_slices() {
      ok('''
        numbers = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        
        copy    = numbers[0...numbers.length]
        
        middle  = copy[3..6]
      ''')
    }
    

    @Test
    def void test_overview() {
      ok('''
        # Assignment:
        number   = 42
        opposite = true
        
        # Conditions:
        number = -42 if opposite
        
        # Functions:
        square = (x) -> x * x
        
        # Arrays:
        list = [1, 2, 3, 4, 5]
        
        # Objects:
        math =
          root:   Math.sqrt
          square: square
          cube:   (x) -> x * square x
        
        # Splats:
        race = (winner, runners...) ->
          print winner, runners
        
        # Existence:
        alert "I knew it!" if elvis?
        
        # Array comprehensions:
        cubes = (math.cube num for num in list)
      ''')
    }
    

    @Test
    def void test_block_comment() {
      ok('''
        ###
        CoffeeScript Compiler v1.1.3
        Released under the MIT License
        ###
      ''')
    }
    

    @Test
    def void test_do() {
      ok('''
        for filename in list
          do (filename) ->
            fs.readFile filename, (err, contents) ->
              compile filename, contents.toString()
      ''')
    }
    

    @Test
    def void test_conditionals() {
      ok('''
        mood = greatlyImproved if singing
        
        if happy and knowsIt
          clapsHands()
          chaChaCha()
        else
          showIt()
        
        date = if friday then sue else jill
        
        options or= defaults
      ''')
    }
    

    @Test
    def void test_functions() {
      ok('''
        square = (x) -> x * x
        cube   = (x) -> square(x) * x
      ''')
    }
    

    @Test
    def void test_strings() {
      ok('''
        mobyDick = "Call me Ishmael. Some years ago --
         never mind how long precisely -- having little
         or no money in my purse, and nothing particular
         to interest me on shore, I thought I would sail
         about a little and see the watery part of the
         world..."
      ''')
    }
    

    @Test
    def void test_splats() {
      ok('''
        gold = silver = rest = "unknown"
        
        awardMedals = (first, second, others...) ->
          gold   = first
          silver = second
          rest   = others
        
        contenders = [
          "Michael Phelps"
          "Liu Xiang"
          "Yao Ming"
          "Allyson Felix"
          "Shawn Johnson"
          "Roman Sebrle"
          "Guo Jingjing"
          "Tyson Gay"
          "Asafa Powell"
          "Usain Bolt"
        ]
        
        awardMedals contenders...
        
        alert "Gold: " + gold
        alert "Silver: " + silver
        alert "The Field: " + rest
      ''')
    }
    

    @Test
    def void test_classes() {
      ok('''
       class Animal
         constructor: (@name) ->
        
         move: (meters) ->
           alert @name + " moved #{meters}m."

       class Snake extends Animal
          move: ->
            alert "Slithering..."
            super 5
        
       class Horse extends Animal
          move: ->
            alert "Galloping..."
            super 45

       sam = new Snake "Sammy the Python"
       tom = new Horse "Tommy the Palomino"
        
       sam.move()
       tom.move()
      ''')
    }
    

    @Test
    def void test_splices() {
      ok('''
        numbers = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        
        numbers[3..6] = [-3, -4, -5, -6]
      ''')
    }
    

    @Test
    def void test_objects_reserved() {
      ok('''
        $('.account').attr class: 'active'
        
        log object.class
      ''')
    }
    

    @Test
    def void test_comparisons() {
      ok('''
        cholesterol = 127
        
        healthy = 200 > cholesterol > 60
      ''')
    }
    

    @Test
    def void test_while() {
      ok('''
        # Econ 101
        if this.studyingEconomics
          buy()  while supply > demand
          sell() until supply > demand
        
        # Nursery Rhyme
        num = 6
        lyrics = while num -= 1
          "#{num} little monkeys, jumping on the bed.
            One fell out and bumped his head."
      ''')
    }
    

    @Test
    def void test_heregexes() {
      ok('''
        OPERATOR = /// ^ (
          ?: [-=]>             # function
           | [-+*/%<>&|^!?=]=  # compound assign / compare
           | >>>=?             # zero-fill right shift
           | ([-+:])\1         # doubles
           | ([&|<>])\2=?      # logic / shift
           | \?\.              # soak access
           | \.{2,3}           # range or splat
        ) ///
      ''')
    }
    

    @Test
    def void test_parallel_assignment() {
      ok('''
        theBait   = 1000
        theSwitch = 0
        
        [theBait, theSwitch] = [theSwitch, theBait]
      ''')
    }
    

    @Test
    def void test_existence() {
      ok('''
        solipsism = true if mind? and not world?
        
        speed ?= 75
        
        footprints = yeti ? "bear"
      ''')
    }
    
}
