#!/usr/bin/env bash
set -e
PROJECT_ROOT="$(cd "$(dirname "$0")/.." && pwd)"
cd "$PROJECT_ROOT"

rm -rf build dist
mkdir -p build/classes dist

find src/main/java -name "*.java" > build/sources.txt
javac -d build/classes @build/sources.txt

jar --create --file dist/library-management-system.jar     --main-class com.library.app.LibraryApplication     -C build/classes .

echo "Build successful: dist/library-management-system.jar"
