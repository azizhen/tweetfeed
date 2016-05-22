package co.za.hendricks.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 *
 * @author  Aziz Hendricks
 * @version 1.0
 * @since   2016-5-21
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        format = { "pretty", "html:target/cucumber" },
        features = "classpath:cucumber/tweetfeed1.feature"
)
public class CucumberTest {

}
