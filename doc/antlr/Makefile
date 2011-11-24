# Prerequisite: sudo apt-get install antlr3

BUILD_CP=/usr/share/java/antlr3.jar
RUN_CP=/usr/share/java/antlr3-runtime.jar

test: out/Test.class out/CsParser.class
	java -cp ${RUN_CP}:out Test <devel.testcase

out/Test.java: Test.java
	cp Test.java out/Test.java

out/CsParser.java: Cs.g
	antlr3 -o out Cs.g
	
out/Test.class: out/Test.java out/CsParser.class
	javac -cp ${BUILD_CP}:out out/Test.java -d out

out/CsParser.class: out/CsParser.java
	javac -cp ${BUILD_CP} out/Cs*.java -d out
