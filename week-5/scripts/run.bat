@echo off
setlocal
cd /d "%~dp0.."

if not exist dist\library-management-system.jar (
  echo Application JAR not found. Run scripts\build.bat first.
  exit /b 1
)

java -jar dist\library-management-system.jar
endlocal
