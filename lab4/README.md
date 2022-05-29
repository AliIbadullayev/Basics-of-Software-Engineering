## Area checker (web app) + MBeans (JMX) + Profile and Monitoring systems (JConsole, ViusalVM, IDEA profiler)
### Project configuration
* <a href="https://ant.apache.org/bindownload.cgi">Download `ant`</a>
* <a href="https://ant.apache.org/ivy/download.cgi">Download `ivy`</a>
* place `ivy.jar` into `Application/IntelliJIDEA/lib/ant/lib/`
* run `ant retrieve` in `./misp`
* In `File | Project Structure` place libraries all your jars in
* In IDEA in `wildfly` run service edit configuration: select `run ant target ‘build’` + `war` (for deploy)
### VisualVM
#### To download
* <a href="https://visualvm.github.io/download.html">Download `VisualVM`</a>
* <a href="https://plugins.jetbrains.com/plugin/7115-visualvm-launcher/">Download `IDEA integration`</a>
* Download plugin for MBeans `Tools | Plugins | MBEANS`

