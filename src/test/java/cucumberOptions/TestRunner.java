package cucumberOptions;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

//mvn test to run all from command line
//mvn test -Dcucumber.filter.tags="@AddPlaces" specific tag
//https://github.com/damianszczepanik/maven-cucumber-reporting reporting
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features"
        , glue = {"StepDefinations"}
        , plugin = "json:target/jsonReports/cucumber-report.json"
        , publish = true
//        , tags = "@AddPlaces"
)
public class TestRunner {

}
