package ch.heigvd.broccoli.application.badge;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@CucumberContextConfiguration
public class BadgeStepDefinition {

    @Autowired
    private MockMvc mvc;

    ResultActions action;

    @When("^the client calls /badges$")
    public void the_client_issues_GET_badges() throws Throwable {
        action = mvc.perform(get("/badges"));
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        action.andExpect(status().is(statusCode));
    }

    @And("^the client receives an array of badges$")
    public void the_client_receives_server_version_body() throws Throwable {
        action.andExpect(content().string("[]"));
    }

}

