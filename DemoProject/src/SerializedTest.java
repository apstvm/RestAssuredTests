import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;


public class SerializedTest {
	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		MapsLocations p = new MapsLocations();
		p.setAccuracy(50);
		p.setAddress("Any address I want to give");
		p.setLanguage("en_US");
		p.setName("ANother house");
		p.setPhone_number("763248784");
		p.setWebsite("www.kjhkjew.com");
		List<String> list2 = new ArrayList<String>();
		list2.add("value1");
		list2.add("value2");
		p.setTypes(list2);
		
		Location location = new Location();
		location.setLat(12.23243);
		location.setLongitude(-101.224345);
		p.setLocation(location);
				
		given().queryParam("key", "qaclick123")
		.body(p)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).log().all();
		
	}
	

}
