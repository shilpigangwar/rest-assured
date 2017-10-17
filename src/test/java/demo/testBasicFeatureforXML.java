package demo;

import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class testBasicFeatureforXML {

    @Test
    public void testInlineSoftAssertion(){
        given()
                .get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10/")
                .then()
                .body("CUSTOMER.ID",equalTo("10"),"CUSTOMER.FIRSTNAME",equalTo("Sue"),"CUSTOMER.LASTNAME",equalTo("Fuller"))
                .log().all();
    }

    @Test
    public void testCompleteTextinOneGo(){
        given()
                .get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10/")
                .then()
                .body("CUSTOMER.text()",equalTo("10SueFuller135 Upland Pl.Dallas"))
                .log().all();
    }

    @Test
    public void testUsingXpath(){
        given()
                .get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10/")
                .then()
                .body(hasXPath("/CUSTOMER/CITY",containsString("Dallas")))
                .log().all();
    }
}
