ANTLR=$(SHAREDLIBDIR)/antlr-4.7.1-complete.jar

all:dir
	javac -cp "$(ANTLR)" src/*.java src/tree/*.java src/task2/*.java -d out

run:
	java -Xmx512m -Xms512m -cp out:$(ANTLR) Main

dir:
	mkdir out

pack:
	zip -r ml3.zip Makefile src/tree src/Parser.g4 src/*.java src/task2

clean:
	rm -rf out
	rm -rf ml3.zip


