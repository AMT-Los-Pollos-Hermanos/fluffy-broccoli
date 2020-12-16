package ch.heigvd.broccoli.application.rules;

import ch.heigvd.broccoli.SpringIntegrationTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RuleStepDefinition extends SpringIntegrationTest {

    @When("the client get all rules")
    public void theClientGetAllRules() {
    }

    @Then("the client receives a {int}")
    public void theClientReceivesA(int arg0) {
    }

}
