rm -rf bin/*

javac -d bin/ -cp src src/Driver.java

java -cp bin/ src/Driver.java
