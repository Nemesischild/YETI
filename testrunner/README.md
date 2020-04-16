# YETI RUNNER
YETI is an Enhanced JUnit 4 Test Runner is part of the YETI suite (originally Rysource) of solutions designed to simplify and
enhance the testing and quality assurance of software development. It gives the test developer the
tools to better align with AGILE practices amongst adding more metadata and verbosity to their
existing framework without sacrificing the standardised approach in which Junit 4 is deployed.

<h2>How it works?</h2>
YETI - (EJ4TR) extends the existing Suite class to provide the same setup and configuration process of a
traditional JUnit 4 implementation. When enabled with the @RunWith annotation, the end
developer can configure and retrieve callbacks on events by using the provided annotations or
interfaces as described in the JavaDoc or Getting Started section of the official documentation.

<h3>Report Styles Support</h3>
- Excel Style
- JUnit XML (Currently in Development)
- EXTENT REPORT HTML

<h3>Callbacks</h3>
<i>Implemented using the EnhancedTestInterface</i>
- onTestFailure (Fired upon a test failing an assertion)
- onTestFinished (Fired upon a test finishing)
- onTestIgnored (Fired when a test is ignored using the @Ignore annotation)
- onTestPassed (Fired when a test finishes without hitting any false assertions)
- onTestStarted (Fired when a test starts executing)

<h3>Getting Started, Images and Documentation</h3>
<a href="https://github.com/SKLn-Rad/Enhanced-JUnit-4-Test-Runner/blob/master/Enhanced%20JUnit4%20Test%20Runner.pdf">Please follow the official startup documentation included in this repository.</a>


<h3>Issues?</h3>
Please post a valid log and use the GitHub issues tracker

<h3>Copyright</h3>
YETI RUNNER is part of the larger YETI solution and therefore the property of inqVine Ltd 
however their is an open source version available here"https://github.com/SKLn-Rad/Enhanced-JUnit-4-Test-Runner/blob/master/" that is covered under wtfpl license.
<br />
<a href="http://www.wtfpl.net/"><img
       src="http://www.wtfpl.net/wp-content/uploads/2012/12/wtfpl-badge-4.png"
       width="80" height="15" alt="WTFPL" /></a>
