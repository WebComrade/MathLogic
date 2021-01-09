ANTLR=$(SHAREDLIBDIR)/antlr-4.7.1-complete.jar

all:dir
	java -Xmx4096m -cp "$(ANTLR)" org.antlr.v4.Tool src/Parser.g4
	javac -cp "$(ANTLR)" src/*.java src/tree/*.java src/task2/*.java -d out

run:
	java -cp out:$(ANTLR) Main

dir:
	mkdir out

pack:
	zip -r ml3.zip Makefile src/tree src/Parser.g4 src/Main.java src/task2

clean:
	rm -rf out
	rm -rf ml3.zip


