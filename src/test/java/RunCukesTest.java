import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources"}, tags = {"~@Ignore", "~@WIP", "~@UITest", "~@Adhoc"})
public class RunCukesTest {
}