## Area checker (web app) + MBeans (JMX) + Profile and Monitoring systems (JConsole, ViusalVM, IDEA profiler)
### Project configuration
* run `ant retrieve` in `./misp`

place ivy.jar into Application/IntelliJIDEA/lib/ant/lib/
* In `File | Project Structure` place libraries all your jars in
* In IDEA in `wildfly` run service edit configuration: select `run ant target ‘build’` + `war` (for deploy)
### VisualVM
#### To download
* <a href="https://visualvm.github.io/download.html">Download</a>
* <a href="https://plugins.jetbrains.com/plugin/7115-visualvm-launcher/">IDEA integration</a>
* Download plugin for MBeans `tools/plugins/MBEANS`

