## How to import Processing into a maven project

1. Download the Processing package to somewhere on your PC.

2. Open CMD in folder; <netbeans-folder>\java\maven\bin

3. Run command;  mvn install:install-file -Dfile=<processing-folder>\core\library\core.jar -DgroupId=processing.java -DartifactId=core -Dversion=3.5.4 -Dpackaging=jar

4. Check the project's pom.xml and make sure that there's a working dependancy for 
  <groupId>processing.java</groupId>
  <artifactId>core</artifactId>
  <version>3.5.4</version>
  Clean n build.
