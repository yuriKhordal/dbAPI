# dbAPI
An abstract library for reducing code when working with databases

## Compiling
First, create directories `build/` and `bin/` in the base `dbAPI/` directory <br/>
Second, run the following commands:
```
javac src/*.java -d build -classpath build -sourcepath src -parameters
jar -cvf bin/dbAPI.jar -C build .
```
The jar file will be in `bin/dbAPI.jar` <br/>
\* Linux users can just use `make all` <br/>

## Javadoc
The javadoc documentation should be inside `doc/` directory and in `doc.zip` file <br>
To re-generate the documentation run `make doc` <br/>
\* The `doc.zip` file might not be generated, if that's the case you can just put everything inside `doc/` folder in an archive yourself
