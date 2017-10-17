package demo;

import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class testBasicFeaturesTest {
    //check status code
    @Test
    public void testStatusCode(){
        given()
                .get("http://jsonplaceholder.typicode.com/posts/3")
                .then()
                .statusCode(200).log().all();
    }

    @Test
    public void testLogging(){
        given()
                .get("http://services.groupkt.com/country/get/iso2code/us")
                .then()
                .statusCode(200).log().all();
    }

    @Test
    public void testEqualToFunction(){
        given()
                .get("http://services.groupkt.com/country/get/iso2code/us")
                .then()
                .body("RestResponse.result.name", equalTo("United States of America"));
    }

    @Test
    public void testHasItemFunction(){
        given()
                .get("http://services.groupkt.com/country/get/all")
                .then()
                .body("RestResponse.result.name" ,hasItems("Afghanistan","Argentina","Australia"));

    }

    @Test
    public void testParametersAndHeaders(){
        given()
                .param("key1","value1")
                .header("headA","valueA")
                .when()
                .get("http://services.groupkt.com/country/get/iso2code/gb")
                .then()
                .log().all();
    }
}
