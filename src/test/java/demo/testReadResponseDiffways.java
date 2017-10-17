package demo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class testReadResponseDiffways {

    @Test
    public void testExtractDetailUsingPath(){
        String href =
                when().
                        get("http://jsonplaceholder.typicode.com/photos/1")
                .then()
                .contentType(ContentType.JSON)
                .body("albumId",equalTo(1))
                .extract()
                .path("url");
        System.out.println(href);
        when().get(href).then().statusCode(200);
    }

    @Test
    public void testExtractPathInOneLine(){
        String href1 = get("http://jsonplaceholder.typicode.com/photos/1").path("thumbnailUrl");
        System.out.println("Fetched URL"+ href1);
        when().get(href1).then().statusCode(200);

        String href2 = get("http://jsonplaceholder.typicode.com/photos/10").andReturn().jsonPath().getString("thumbnailUrl");
        System.out.println("Fetched Url"+ href2);
        when().get(href2).then().statusCode(200);
    }

    @Test
    public void testExtractDetailsUsingResponse(){
        Response response=
                when().get("http://jsonplaceholder.typicode.com/photos/10")
                .then().extract().response();
        System.out.println("Content type "+response.getContentType());
        System.out.println("Href "+response.path("url"));
        System.out.println("Status Code "+response.getStatusCode());
    }
}
