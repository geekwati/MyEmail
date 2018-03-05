echo off
@rem to delete class files from target folder
cd target
del /s /q *.class
cd..

rem create class files in target folder
set targetPath=f:/project/corejava7_8/test/EmailServer1/target
cd src/java
javac  -d  %targetPath% org/myemail/bl/*.java
javac  -d %targetPath% org/myemail/controller/*.java
javac  -d %targetPath% org/myemail/db/*.java
javac  -d %targetPath% org/myemail/domain/*.java
javac  -d %targetPath% org/myemail/service/*.java
cd../..
 
 rem run controller
cd target
java -cp ../lib/h2-1.3.176.jar;../resources/;. org/myemail/controller/Controller
cd..