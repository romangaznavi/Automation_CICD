package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/java/cucumber", 
		glue = "romangaznavi.stepDefinitions",
		tags = "@Regression", 
		monochrome = true, 
		plugin = {"html:target/cucumber.html" 
				
		})

public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
//New comment