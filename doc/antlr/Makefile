ANTLR3=antlr-3.2.jar

test: init out/Test.class out/CoffeeScriptParser.class CoffeeScript.gunit
	java -cp devel/${ANTLR3}:out org.antlr.gunit.Interp CoffeeScript.gunit

init: devel out devel/${ANTLR3}

devel:
	mkdir devel

out:
	mkdir out

${ANTLR3}:
	wget http://www.antlr.org/download//${ANTLR3}
	mv ${ANTLR3} devel

out/Test.java: Test.java
	cp Test.java out/Test.java

out/CoffeeScriptParser.java: out/CoffeeScript.g
	java -jar devel/${ANTLR3} out/CoffeeScript.g
	
out/CoffeeScript.g: prelude.g tokens.g rules.g
	cat prelude.g > out/CoffeeScript.g
	cat tokens.g >> out/CoffeeScript.g
	cat rules.g >> out/CoffeeScript.g

out/Test.class: out/Test.java out/CoffeeScriptParser.class
	javac -cp devel/${ANTLR3}:out out/Test.java -d out

out/CoffeeScriptParser.class: out/CoffeeScriptParser.java
	javac -cp devel/${ANTLR3} out/*.java -d out
