package ch.heigvd.broccoli;

import ch.heigvd.broccoli.application.event.EventDTO;
import ch.heigvd.broccoli.application.pointscale.PointScaleDTO;
import ch.heigvd.broccoli.application.rule.RuleDTO;
import ch.heigvd.broccoli.domain.award.AwardBadge;
import ch.heigvd.broccoli.domain.award.AwardPoint;
import ch.heigvd.broccoli.domain.rule.specification.RuleIf;
import ch.heigvd.broccoli.domain.rule.specification.RuleThen;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.hibernate.boot.jaxb.hbm.spi.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {"spring.datasource.url=jdbc:h2:mem:db"})
@AutoConfigureMockMvc
@CucumberContextConfiguration
public class StepDefinitions {

    @Autowired
    private MockMvc mvc;

    MvcResult result;
    ResultActions action;
    String pathBadges = "/badges";
    String pathApplication = "/applications?name=";
    String pathRules = "/rules";
    String pathPointScales = "/pointscales";
    String pathEvents = "/events";
    String appName = "test";
    String apiKey = "";
    String lastPayload = "";
    UUID userId = UUID.randomUUID();
    ObjectMapper mapper = new ObjectMapper();


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
    @When("the client put {string}")
    public void theClientPut(String path) throws Exception {
        action = mvc.perform(MockMvcRequestBuilders.put(path+appName));
    }

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
    @When("the client delete {string}")
    public void theClientDelete(String path) throws Exception {
        action = mvc.perform(MockMvcRequestBuilders.delete(path+appName));
    }

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
    public void theClientReceivesABadge() throws Throwable {
        action.andExpect(content().string("{\"id\":6,\"name\":\"My amazing badge\",\"description\":\"You can get this badge after 50 comments\",\"icon\":\"/images/icon.png\"}"));
    }

    @And("^the client receives an empty array$")
    public void TheClientReceivesAnEmptyArray() throws Throwable {
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
    public void TheClientPostsAnApplication() throws Throwable{
        action = mvc.perform(post(pathApplication + appName));
    }

    // Rules
    @When("^the client posts a badge-scale rule$")
    public void TheClientPostsABadgeScaleRule() throws Throwable{
        lastPayload = mapper.writeValueAsString(RuleDTO.builder()
                .id(1L)
                .ruleIf(RuleIf.builder()
                        .type("string")
                        .properties(new HashMap<>(){{
                            put("additionalProp1", "string");
                            put("additionalProp2", "string");
                        }})
                        .build())
                .ruleThen(RuleThen.builder()
                        .awardPoints(AwardPoint.builder()
                                .amount(0)
                                .pointScale(1L)
                                .build())
                        .awardBadge(AwardBadge.builder()
                                .badgeId(1L)
                                .build())
                        .build())
                .build());

        action = mvc.perform(MockMvcRequestBuilders.post(pathRules)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-API-KEY", apiKey)
                .content(lastPayload));
    }

    @When("^the client posts a badge rule$")
    public void TheClientPostsABadgeRule() throws Throwable{
        lastPayload = mapper.writeValueAsString(RuleDTO.builder()
                .id(2L)
                .ruleIf(RuleIf.builder()
                        .type("string")
                        .properties(new HashMap<>(){{
                            put("additionalProp1", "string");
                            put("additionalProp2", "string");
                        }})
                        .build())
                .ruleThen(RuleThen.builder()
                        .awardBadge(AwardBadge.builder()
                                .badgeId(1L)
                                .build())
                        .build())
                .build());

        action = mvc.perform(MockMvcRequestBuilders.post(pathRules)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-API-KEY", apiKey)
                .content(lastPayload));
    }

    @When("^the client posts a scale rule$")
    public void TheClientPostsAScaleRule() throws Throwable {
        lastPayload = mapper.writeValueAsString(RuleDTO.builder()
                .id(3L)
                .ruleIf(RuleIf.builder()
                        .type("string")
                        .properties(new HashMap<>(){{
                            put("additionalProp1", "string");
                            put("additionalProp2", "string");
                        }})
                        .build())
                .ruleThen(RuleThen.builder()
                        .awardPoints(AwardPoint.builder()
                                .amount(0)
                                .pointScale(1L)
                                .build())
                        .build())
                .build());

        action = mvc.perform(MockMvcRequestBuilders.post(pathRules)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-API-KEY", apiKey)
                .content(lastPayload));
    }

    @Then("^the client receives the correct payload$")
    public void theClientReceivestheCorrectPayload() throws Throwable {
        action.andExpect(content().string(lastPayload));
    }

    /*PointScale*/
    @Then("the client posts a point scale called {string}")
    public void theClientPostsAPointScaleCalled(String name) throws Throwable{
        lastPayload = mapper.writeValueAsString(PointScaleDTO.builder()
                .id(1L)
                .name(name)
                .build());
        action = mvc.perform(MockMvcRequestBuilders.post(pathPointScales)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-API-KEY", apiKey)
                .content(lastPayload));
    }


    @When("the client posts an event")
    public void theClientPostsAnEvent() throws Throwable{

        lastPayload = mapper.writeValueAsString(EventDTO.builder()
                .properties(new HashMap<>(){{
                    put("additionalProp1", "string");
                    put("additionalProp2", "string");
                }})
                .type("string")
                .userId(userId)
                .build());
        action = mvc.perform(MockMvcRequestBuilders.post(pathEvents)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-API-KEY", apiKey)
                .content(lastPayload));

    }
}

