# Deployment Guide

## 1. Environment

Install Java 17 or newer and verify:

```bash
java -version
javac -version
```

For Maven-based development, also verify:

```bash
mvn -version
```

## 2. Build

Using Maven:

```bash
mvn clean test
mvn package
```

This compiles the application, executes the configured JUnit tests and produces an executable JAR in `target/`.

For a minimal deployment simulation without Maven:

```bash
./scripts/build.sh
```

The script compiles all production Java files and packages them as:

`dist/library-management-system.jar`

## 3. Run

```bash
java -jar dist/library-management-system.jar
```

The Windows equivalent is:

```bat
scripts\run.bat
```

## 4. Deployment Validation

After packaging, validate:

1. The JAR exists.
2. The JAR starts with `java -jar`.
3. The menu is displayed.
4. A book can be created.
5. Books can be listed and searched.
6. A book can be updated.
7. A book can be deleted.
8. Invalid input is rejected with a readable message.

## 5. Rollback

Because the application is packaged as a self-contained JAR, rollback can be performed by replacing the current JAR with a previously validated version.

## 6. Operations Notes

The current version stores data in memory. Restarting the application clears the inventory. A future production version should introduce persistent storage such as PostgreSQL or MySQL and configuration through environment variables.

## 7. Maintenance

Future maintainers should preserve the separation between presentation and service logic. New features should preferably be added through the service layer and covered with tests before release.
