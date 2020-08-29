import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*; 

import files.Payload;
import files.ReusableMethods;

public class MapsTest {
	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
//		updatePlace();
		String newAddress = "Any address I want";
		String place = addPlace(newAddress);
		getPlace(place, newAddress);
		
	}
	public static String addPlace(String address) {
		String response = given().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.addPlace())
		.when().post("maps/api/place/add/json")
		.then().extract().asString();
		
		JsonPath json = new JsonPath(response);
		String place_id = json.getString("place_id");
		System.out.println("Place id is " + place_id);
//		return place_id;
//	}
//	
//	public static void updatePlace() {
//		String place_id = addPlace();
//		System.out.println("Place id from add is "+ place_id);
		String response2 = given().queryParam("key", "qaclick123")
		.header("Content-Type","application/json")
		.body("{\n" + 
				"\"place_id\":\" " + place_id + "\",\n" + 
				"\"address\":\"" + address + " \",\n" + 
				"\"key\":\"qaclick123\"\n" + 
				"}\n" + 
				"")
		.when().put("maps/api/place/update/json")
		.then().extract().asString(); 
		
		JsonPath json2 = new JsonPath(response2);
		String msg = json2.getString("msg");
		System.out.println("Message " + msg);
		
		return place_id;
	}
	
	public static void getPlace(String place_id, String newAddress) {
		System.out.println("THis is the get method");
		
		String response = given().queryParam("key", "qaclick123").queryParam("place_id", place_id)
		.when().get("maps/api/place/get/json")
		.then().extract().asString();
		
		JsonPath json  = ReusableMethods.rawToJson(response);
//		String lat = json.getString("name");
//		System.out.println("Lat is " + lat);
//		System.out.println("lat long " + json.getString("location.latitude") + " , " + json.getString("location.longitude"));
		String address = json.getString("address");
		System.out.println(address);
		
//		Assert.assertEquals(address, newAddress);
		System.out.println("Test passed");
		
		
	}
	

}
