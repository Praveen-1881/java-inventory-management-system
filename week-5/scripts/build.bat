@echo off
setlocal
cd /d "%~dp0.."

if exist build rmdir /s /q build
if exist dist rmdir /s /q dist
mkdir build\classes
mkdir dist

dir /s /b src\main\java\*.java > build\sources.txt
javac -d build\classes @build\sources.txt

jar --create --file dist\library-management-system.jar --main-class com.library.app.LibraryApplication -C build\classes .

echo Build successful: dist\library-management-system.jar
endlocal
