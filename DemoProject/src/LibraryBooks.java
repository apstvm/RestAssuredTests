import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

public class LibraryBooks {
	String newBook = null;
	
	@Test(dataProvider = "booksData")
	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		System.out.println("Adding a book first");
		
		String addedBook = given().header("Content-Type", "application/json")
		.body(Payload.addBook(isbn, aisle))
		.post("/Library/Addbook.php")
		.then().log().all().statusCode(200).extract().response().asString();
		System.out.println("Added book " + isbn+aisle + "\n");
		
//		JsonPath js = new JsonPath(addedBook);
//		String newBook = js.getString("ID");
//		System.out.println(newBook);
	}

	@Test(dataProvider = "booksData")
	public void deleteBook(String isbn, String aisle) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		given().header("Content-Type", "application/json")
		.body(Payload.deleteBook(isbn, aisle))
		.post("/Library/DeleteBook.php")
		.then().log().all().statusCode(200);
		System.out.println("Deleted book " + isbn+aisle + "\n");
	}
	
	@DataProvider(name = "booksData")
	public Object[][] giveData() {
		return new Object[][] {{"djdjd", "fsj3"},{"sggd", "wdjka2"}, {"fskdd", "dfaf"}, {"etssd", "drfs"}};
	}
	
	
	
	
//	@Ignore
//	public void getBook(){
//		RestAssured.baseURI = "http://216.10.245.166";
//		String bookDetails = given().queryParam("ID", newBook)
//		.get("/Library/GetBook.php")
//		.then().statusCode(200).extract().response().asString();
//		
//		JsonPath js = new JsonPath(bookDetails);
//		
//	}

}
