# nga project

This is a Java command-line application named _nga_. The project uses Gradle and the
Shadow plugin to create an executable fat JAR.

## Setting up in Intellij

Prerequisites: JDK 21 and Gradle 9.2 or later. Update IntelliJ to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 21** (not other versions) as explained [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/nga/Nga.java` file, right-click it, and choose `Run Nga.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
    _ __   __ _  __ _
   | '_ \ / _` |/ _` |
   | | | | (_| | (_| |
   |_| |_|\__, |\__,_|
          |___/
    ```

## Creating and running the fat JAR

From the project root, make sure Java 21 is selected and run:

```bash
export JAVA_HOME="$(/usr/libexec/java_home -v 21)"
export PATH="$JAVA_HOME/bin:$PATH"
gradle shadowJar
```

The `shadowJar` task compiles the application and bundles the application classes
and runtime dependencies into one executable JAR. The output is:

```text
build/libs/nga-all.jar
```

You can confirm the file exists with:

```bash
ls -l build/libs/nga-all.jar
```

Run it from the project root with:

```bash
java -jar build/libs/nga-all.jar
```

The bulk-delete commands are `clear` and `delete all`. Both remove every task
and save the empty list to `data/nga.txt`.

The JAR contains the `nga.Nga` main class in its manifest, so no additional
classpath or main-class argument is needed. The target computer still needs a
compatible Java runtime; the JAR does not include the Java runtime itself.

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.
