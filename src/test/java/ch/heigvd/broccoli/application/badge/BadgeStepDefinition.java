package ch.heigvd.broccoli.application.badge;

import ch.heigvd.broccoli.SpringIntegrationTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BadgeStepDefinition extends SpringIntegrationTest {

    @Autowired
    private MockMvc mvc;

    MvcResult result;
    ResultActions action;
    String pathBadges = "/badges";
    String pathApplication = "/applications?name=";
    String appName = "test";
    String apiKey = "";


    /* Badges */
    //GET
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

    //POST
    @When("^the client posts /badges$")
    public void theClientPostsBadges() throws Exception {
        action = mvc.perform(MockMvcRequestBuilders.post(pathBadges)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-API-KEY", apiKey)
                .content("{\"description\":\"You can get this badge after 50 comments\",\"icon\":\"/images/icon.png\",\"id\": 6,\"name\":\"My amazing badge\"}"));
    }

    @When("^the client posts /badges (\\d+) times$")
    public void theClientPostsBadgesMultipleTimes(int nbRequests) throws Exception {

        for(int i = 0; i < nbRequests; ++i) {
            StringBuilder sb = new StringBuilder("{\"description\":\"You can get this badge after ")
                    .append(5 * (i + 1))
                    .append(" comments\",\"icon\":\"/images/icon.png\",\"id\":")
                    .append(i + 1)
                    .append(",\"name\":\"My amazing badge ")
                    .append(i + 1)
                    .append("\"}");

            action = mvc.perform(MockMvcRequestBuilders.post(pathBadges)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("X-API-KEY", apiKey)
                    .content(sb.toString()))
                    .andExpect(status().is(201));
        }
    }

    //PUT
    @When("the client put {string} with API-KEY")
    public void theClientPutWithAPIKEY(String path) throws Exception {
        action = mvc.perform(MockMvcRequestBuilders.put(path)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-API-KEY", apiKey)
                .content("{\"description\":\"This badge has been modified\",\"icon\":\"/images/icon.png\",\"id\": 6,\"name\":\"My amazing badge\"}"));
    }

    @When("the client put {string} with wrong API-KEY")
    public void theClientPutWithWrongAPIKEY(String path) throws Exception {
        action = mvc.perform(MockMvcRequestBuilders.put(path)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-API-KEY", apiKey)
                .content("{\"description\":\"This badge has been modified\",\"icon\":\"/images/icon.png\",\"id\": 6,\"name\":\"My amazing badge\"}"));
    }

    //DELETE
    @When("the client delete {string} with API-KEY")
    public void theClientDeleteWithAPIKEY(String path) throws Exception {
        action = mvc.perform(MockMvcRequestBuilders.delete(path)
                .header("X-API-KEY", apiKey));
    }

    @When("the client delete {string} with wrong API-KEY")
    public void theClientDeleteWithWrongAPIKEY(String path) throws Exception {
        action = mvc.perform(MockMvcRequestBuilders.delete(path)
                .header("X-API-KEY", "WrongApiKey"));
    }


    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        action.andExpect(status().is(statusCode));
    }

    @Then("^the client receives a badge$")
    public void theClientReceivesAnArrayOfBadges() throws Throwable {
        action.andExpect(content().string("{\"id\":6,\"name\":\"My amazing badge\",\"description\":\"You can get this badge after 50 comments\",\"icon\":\"/images/icon.png\"}"));
    }

    @And("^the client receives an empty array of badges$")
    public void the_client_receives_server_version_body() throws Throwable {
        action.andExpect(content().string("[]"));
    }

    @And("^the client receives an array of (\\d+) badges$")
    public void theClientReceivesAnEmptyArrayOfBadges(int nbBadges) throws Throwable {
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < nbBadges; ++i) {
            sb.append("{\"id\":")
                    .append(i + 1)
                    .append(",\"name\":\"My amazing badge ")
                    .append(i + 1)
                    .append("\",\"description\":\"You can get this badge after ")
                    .append(5 * (i + 1))
                    .append(" comments\",\"icon\":\"/images/icon.png\"}");

                    if(i < nbBadges - 1) {
                        sb.append(",");
                    }
        }
        sb.append("]");
        action.andExpect(content().string(sb.toString()));
    }

    @Then("^the client has updated a badge$")
    public void theClientHasUpdatedABadge() throws Exception {
        action.andExpect(content().string("{\"id\":6,\"name\":\"My amazing badge\",\"description\":\"This badge has been modified\",\"icon\":\"/images/icon.png\"}"));
    }


    /* Application */

    @Given("an application named {string}")
    public void anApplicationNamed(String name) throws Exception {
        action = mvc.perform(post(pathApplication + name));
    }

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

}

