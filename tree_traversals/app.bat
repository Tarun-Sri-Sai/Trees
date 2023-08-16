@echo off

IF "%~1"=="" (
    echo Usage: app [run | build | clean]
    exit /b 1
)

IF /I "%~1"=="run" (
    java -cp bin Main.java
) ELSE IF /I "%~1"=="build" (
    javac lib/*.java src/*.java -d bin
) ELSE IF /I "%~1"=="clean" (
    del /q /s bin\*.class
) ELSE (
    echo Usage: app [run | build | clean]
    exit /b 1
)