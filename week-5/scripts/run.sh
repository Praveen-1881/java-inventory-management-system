#!/usr/bin/env bash
set -e
PROJECT_ROOT="$(cd "$(dirname "$0")/.." && pwd)"
cd "$PROJECT_ROOT"

if [ ! -f dist/library-management-system.jar ]; then
  echo "Application JAR not found. Run ./scripts/build.sh first."
  exit 1
fi

java -jar dist/library-management-system.jar
