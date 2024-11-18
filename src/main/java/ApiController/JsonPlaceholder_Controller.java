package ApiController;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static ApiController.BaseController.baseRequestUrl;
import static io.restassured.RestAssured.given;

public class JsonPlaceholder_Controller {
    static RequestSpecification basePosts = baseRequestUrl("https://jsonplaceholder.typicode.com/");

    public static Response ListAllPosts(String Endpoint) {
        return given()
                .spec(basePosts)
                .when().get(Endpoint);
    }
    public static Response GetSinglePostByID(String Endpoint, String ID){
        return   given()
                .spec(basePosts)
                .when().get(Endpoint+ID);
    }
}
