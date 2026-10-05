@echo off
echo Compiling Java source files...
javac -cp "lib/*;." Book.java ExcelReader.java Library.java Main.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b %errorlevel%
)
echo Starting Library Management System...
java -cp "lib/*;." Main
pause
