package ch.heigvd.broccoli.application.badge;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.remoting.httpinvoker.HttpInvokerRequestExecutor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@CucumberContextConfiguration
public class BadgeStepDefinition {

    @Autowired
    private MockMvc mvc;
    MvcResult result;
    ResultActions action;
    String pathApplication = "/applications?name=";
    String appName = "test";
    String apiKey = "";



    @When("the client get {string}")
    public void theClientGet(String path) throws Exception {
            action = mvc.perform(get(path+appName));
    }

    @When("the client get {string} with API-KEY")
    public void theClientGetWithAPIKEY(String path) throws Exception {
        action = mvc.perform(MockMvcRequestBuilders.get(path).header("X-API-KEY", apiKey));
    }

    @When("the client get {string} with wrong API-KEY")
    public void theClientGetWithWrongAPIKEY(String path) throws Exception {
        action = mvc.perform(MockMvcRequestBuilders.get(path).header("X-API-KEY", "WrongApiKey"));
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        action.andExpect(status().is(statusCode));
    }

    @And("^the client receives an empty array of badges$")
    public void the_client_receives_server_version_body() throws Throwable {
        action.andExpect(content().string("[]"));
    }


    @Given("an application named {string}")
    public void anApplicationNamed(String name) throws Exception {
        action = mvc.perform(post(pathApplication + name));
    }


    /* Application */
    @And("^the client receives an API-KEY$")
    public void the_client_receives_API_KEY() throws UnsupportedEncodingException, ParseException {
        result = action.andReturn();
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(result.getResponse().getContentAsString());
        apiKey = json.getAsString("apiKey");
        assertEquals(appName, json.getAsString("name"));
    }

    @When("^the client posts /applications$")
    public void the_client_POST_applications() throws Throwable{
        action = mvc.perform(post(pathApplication + appName));
    }

    //TODO check si c'est possible d'atteindre une instance de l'API/serveur
    @Given("There is an application server")
    public void thereIsAnApplicationServer() {

    }


    @And("the client posts \\/badges")
    public void theClientPostsBadges() throws Exception {
        action = mvc.perform(post(pathApplication + appName));
    }
}

