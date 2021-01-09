ANTLR=$(SHAREDLIBDIR)/antlr-4.7.1-complete.jar

all:dir
	java -Xmx4096m -cp "$(ANTLR)" org.antlr.v4.Tool src/Parser.g4
	javac -cp "$(ANTLR)" src/*.java src/tree/*.java -d out

run:
	java -cp out:$(ANTLR) Main

dir:
	mkdir out

pack:
	zip -r ml1.zip Makefile src/tree src/Parser.g4 src/Main.java

clean:
	rm -rf out
	rm -rf ml1.zip


