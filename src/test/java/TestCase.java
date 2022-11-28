import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class TestCase {
    @BeforeClass
    public void setup() {
        baseURI = "https://api.punkapi.com/";
        basePath = "/v2/";
    }
    @Test(priority = 1)
    public void url1() {
        String numberOfData = "20";
        Response res = given().
                queryParam("page", "2").
                queryParam("per_page",numberOfData).
                when().
                get("/beers/");

        JsonPath extractor = res.jsonPath();
        String size = extractor.getString("size()");

        System.out.println("Amount of data returned: "+size);

        Assert.assertEquals(size,numberOfData);
    }
    @Test(priority = 2)
    public void url2() {
        String numberOfData = "5";
        String res = given().
                queryParam("page", "2").
                queryParam("per_page",numberOfData).
                when().
                get("/beers/").
                then().
                extract().path("size()").toString();

        System.out.println("Amount of data returned: "+res);
        Assert.assertEquals(res,numberOfData);
    }
    @Test(priority = 3)
    public void url3() {
        String numberOfData = "1";
        Response res = given().
                queryParam("page", "2").
                queryParam("per_page",numberOfData).
                when().
                get("/beers/");

        JsonPath js = new JsonPath(res.asString());

        String size = js.getString("size()");
        //        String name = js.getString("name");
        System.out.println("Amount of data returned: "+size);
//        System.out.println(name);
        Assert.assertEquals(size,numberOfData);
    }
}