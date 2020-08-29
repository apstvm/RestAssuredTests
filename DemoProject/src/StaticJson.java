import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class StaticJson {
	
	@Test
	public void addBook() {
		String jsonPath = "/Users/arunps/Downloads/AddJson.json";
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		System.out.println("Adding a book first");
		String body;
		try {
			body = generateStringFromPath(jsonPath);

		
			String addedBook = given().header("Content-Type", "application/json")
			.body(body)
			.post("/Library/Addbook.php")
			.then().log().all().statusCode(200).extract().response().asString();
			
			JsonPath js = new JsonPath(addedBook);
			String newBook = js.getString("ID");
			System.out.println("Added book " + newBook);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String generateStringFromPath(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	
	@DataProvider(name = "testData")
	public Object[] testFunction() {
		return new Object[] {"sfds","weffsd", "Ssdfsdf"};
	}
	
	public String readFile(String location) throws IOException {
		String path = "/Users/arunps/Downloads/AddBookDetails.json";
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	
	


}

//"/Users/arunps/Downloads/AddJson.json"