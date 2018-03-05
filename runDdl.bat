echo off
@rem to delete class files from target folder
cd target/org/myemail/db
del /s /q TestDdl.class
del /s /q Ddl.class
cd../../../..

rem create class files in target folder
set targetPath=f:/project/corejava7_8/test/EmailServer1/target
cd src/java
javac  -d %targetPath% org/myemail/db/Ddl.java
javac  -d %targetPath% org/myemail/db/TestDdl.java
cd../..

rem run controller
cd target
java -cp ../lib/h2-1.3.176.jar;../resources/;. org/myemail/db/TestDdl
cd..