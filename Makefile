JAVAC = javac
SRCDIR = src
LIBDIR = lib
BINDIR = bin

.SUFFIXES: .java .class

# Compile all .java files in the src directory
$(BINDIR)/%.class: $(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR) $<

# Compile all .java files in the lib directory
$(BINDIR)/lib/%.class: $(LIBDIR)/%.java
	$(JAVAC) -d $(BINDIR) $<

# Compile all .java files
all: $(patsubst $(SRCDIR)/%.java,$(BINDIR)/%.class,$(wildcard $(SRCDIR)/*.java)) \
     $(patsubst $(LIBDIR)/%.java,$(BINDIR)/lib/%.class,$(wildcard $(LIBDIR)/*.java))

# Run the project
run:
	java -cp $(BINDIR) src\Main.java

# Clean up generated files
clean:
	del /F /Q $(BINDIR)\$(SRCDIR) $(BINDIR)\$(LIBDIR)

# Define the default goal to be all
.DEFAULT_GOAL := all
