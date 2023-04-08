JC=javac
JFLAGS=-g

RM=del /S /Q

SRC_DIR=src
LIB_DIR=lib
BIN_DIR=bin
PROJECT_DIR=trees

SOURCE_FILES=$(wildcard $(SRC_DIR)/*.java $(LIB_DIR)/*.java)
MAIN_CLASS=Main

CLASS_FILES=$(BIN_DIR)/$(PROJECT_DIR)/$(MAIN_CLASS).class

$(CLASS_FILES): $(SOURCE_FILES)
	$(JC) $(JFLAGS) $^ -d $(BIN_DIR)

run: $(CLASS_FILES)
	java -cp $(BIN_DIR) $(SRC_DIR)/$(MAIN_CLASS).java

clean:
	$(RM) *.class