JC=javac
JFLAGS=-g

RM=del /S /Q

SRC_DIR=src
LIB_DIR=lib
BIN_DIR=bin
PROJECT_DIR=trees

SOURCE_FILES=$(wildcard $(PROJECT_DIR)/$(SRC_DIR)/*.java $(PROJECT_DIR)/$(LIB_DIR)/*.java)
MAIN_CLASS=Main

CLASS_FILES=$(BIN_DIR)/$(PROJECT_DIR)/$(MAIN_CLASS).class

$(CLASS_FILES): $(SOURCE_FILES)
	$(JC) $(JFLAGS) $^ -d $(PROJECT_DIR)/$(BIN_DIR)

run: $(CLASS_FILES)
	java -cp $(PROJECT_DIR)/$(BIN_DIR) $(PROJECT_DIR)/$(SRC_DIR)/$(MAIN_CLASS).java

clean:
	$(RM) *.class