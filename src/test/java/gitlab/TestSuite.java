package gitlab;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty", "zephyr.listeners.ZephyrListener"},
        features = {"src/test/resources/features/edge/TC006_Schema_Validation.feature"}
)

public class TestSuite {
}
