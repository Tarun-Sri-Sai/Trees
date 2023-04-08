JC=javac
JFLAGS=-g

RM=del /S /Q

SRC_DIR=src
LIB_DIR=lib
BIN_DIR=bin
PROJECT=trees

SOURCE_FILES=$(wildcard $(SRC_DIR)/*.java $(LIB_DIR)/*.java)

CLASS_FILES=$(BIN_DIR)/$(PROJECT)/$(SOURCE_FILES:.java=.class)

MAIN_CLASS=Main.java

all: $(CLASS_FILES)

$(CLASS_FILES): $(SOURCE_FILES)
	$(JC) $(JFLAGS) $^ -d $(BIN_DIR)

run:
	java -cp $(BIN_DIR) $(SRC_DIR)/$(MAIN_CLASS)

clean:
	$(RM) *.class