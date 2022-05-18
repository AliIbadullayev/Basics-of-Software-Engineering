### Ant + Ivy configuration
First we need to know how we build and run simple Java project
#### 1. To compile the TestHello.java
javac -sourcepath src -cp lib/junit-4.13.2.jar:lib/hamcrest-all-1.3.jar src/test/TestHello.java -d build/classes
<img src="img/classes.png" data-canonical-src="img/classes.png" width="300" height="300" />

#### 2. To run TestHello
```bash
java -cp build/classes:lib/junit-4.13.2.jar:lib/hamcrest-all-1.3.jar org.junit.runner.JUnitCore test.TestHello
```
**After run**
```bash
> java -cp build/classes:lib/junit-4.13.2.jar:lib/hamcrest-all-1.3.jar org.junit.runner.JUnitCore test.TestHello  
JUnit version 4.13.2
.
Time: 0,006

OK (1 test)
```

To download ant 
https://ant.apache.org/srcdownload.cgi
To download ivy
https://ant.apache.org/ivy/download.cgi
To configure ant and ivy
```
nano ~/.bash_profile (or nano ~/.zprofile for mac)
```
``` bash
source ~/.profile
# Setting PATH for Python 3.10
# The original version is saved in .zprofile.pysave
ANT_HOME=/Users/alibaba/Downloads/apache-ant-1.10.12
IVY_HOME=/Users/alibaba/Downloads/apache-ivy-2.5.0
PATH="$IVY_HOME/ivy-2.5.0.jar:$ANT_HOME/bin:/Library/Frameworks/Python.framework/Versions/3.10/bin:${PATH}"
export PATH
```

Then as example run project in apache-ivy-2.5.0/example/hello-ivy running command `ant`
Then all must be done successfull
