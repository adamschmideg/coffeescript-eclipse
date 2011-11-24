ANTLR3=antlr-3.2.jar

test: init out/Test.class out/CsParser.class Cs.gunit
	java -cp devel/${ANTLR3}:out org.antlr.gunit.Interp Cs.gunit

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

out/CsParser.java: Cs.g
	java -jar devel/${ANTLR3} -o out Cs.g
	
out/Test.class: out/Test.java out/CsParser.class
	javac -cp devel/${ANTLR3}:out out/Test.java -d out

out/CsParser.class: out/CsParser.java
	javac -cp devel/${ANTLR3} out/Cs*.java -d out
