javac -cp lwjgl.jar;lwjgl_util.jar;slick-util.jar src/*.java
pause
java -cp src;lwjgl.jar;lwjgl_util.jar;slick-util.jar;res -Djava.library.path=natives Launcher
pause