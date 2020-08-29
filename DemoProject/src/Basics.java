import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Payload;


public class Basics {
	public static void main(String args[]) {
		//given = input, when = submit api, then = validate
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		
		String house_number = given()
				.queryParam("key", "qaclick123")
				.header("Content-Type", "application/json")
		.body(Payload.addPlace()).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
		.log().all().extract().body().asString();
		System.out.println(house_number);
	
		JsonPath js = new JsonPath(house_number);
		String place = js.getString("place_id");
		System.out.println(place);

		String newAddress = "1200 Beacon";
		given()
		.queryParam("key","qaclick123")
		.queryParam("place_id", place)
		.header("Content-Type", "application/json")
		.body("{\n" + 
				"\"place_id\":\"" + place + "\",\n" + 
				"\"address\":\"" + newAddress+ "\",\n" + 
				"\"key\":\"qaclick123\"\n" + 
				"}")
		.put("/maps/api/place/update/json")
		.then().statusCode(200).body("msg",equalTo("Address successfully updated")).log().all();
//		System.out.println("Place updated");
		
		
		
		String address = given()
		.queryParam("key", "qaclick123")
		.queryParam("place_id", place)
		.get("/maps/api/place/get/json")
		.then().extract().response().asString();
		
		JsonPath new_js = new JsonPath(address);
		String exactAddress = new_js.getString("address");
		System.out.println(exactAddress);
		Assert.assertEquals(exactAddress, newAddress);
		System.out.println("All tests passed");
		
	}

}