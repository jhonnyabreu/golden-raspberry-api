package com.textoit.resource;

import com.google.gson.Gson;
import com.textoit.entity.dto.AwardsOutput;
import com.textoit.entity.dto.MovieDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class AwardsResourceTest {

    @Test
    public void testHelloEndpoint() {
        given().when()
                .get("/api/awards")
                .then()
                .statusCode(200)
                .body(is(payload()));
    }

    private String payload() {
        AwardsOutput output = new AwardsOutput();

        MovieDTO min = new MovieDTO();
        min.setProducer("Joel Silver");
        min.setInterval(1);
        min.setFollowingWin(1991);
        min.setPreviousWin(1990);
        output.setMin(List.of(min));

        MovieDTO max = new MovieDTO();
        max.setProducer("Matthew Vaughn");
        max.setInterval(13);
        max.setFollowingWin(2015);
        max.setPreviousWin(2002);
        output.setMax(List.of(max));

        return new Gson().toJson(output);
    }

}