//
//import static io.restassured.RestAssured.*;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver; 
//
//import io.restassured.path.json.JsonPath;
//
//public class OAuthTestOld {
//	public static void main(String[] args) {
////		RestAssured.baseURI = "https://rahulshettyacademy.com/getCourse.php";
//		
//		System.setProperty("webdriver.chrome.driver", "/Users/arunps/Downloads/chromedriver");
//		WebDriver driver = new ChromeDriver();
//		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&"
//				+ "auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj"
//				+ ".apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
//		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("arunsaccount@gmail.com");
//		
//		
//		String accessTokenResponse = given().queryParam("code", 
//				"4%2F0wG4mfkgngccjIRThwSfwqeBg-skFzjoqulo3o4jR2A9vR4Tmy3rbUOZiSKjCJfbinMLU8d17OfCRrTfWUI0Swk")
//		.queryParam("client_id" , "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
//		.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
//		.queryParams("grant_type", "authorization_code")
//		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
//		.when().post("https://www.googleapis.com/oauth2/v4/token")
//		.then().log().all().extract().asString();
//		
//		System.out.println(accessTokenResponse);
//		
//		JsonPath json = new JsonPath(accessTokenResponse);
//		System.out.println(json.getString("access_token"));
//		
//
////		String response = given().queryParam("access_token",
////				"ya29.GlxgB56UzSPvk69weWofcw6Rk822oKmjVO9Oo0jzW0NprzPlwQ79Hw-2zM9k8r"
////				+ "-SztgijCo19viyluASLly_LWgVfvrVk77iwlme6Qh3HAuJQGLh4esgjgTkhu80wg")
////		.when().get("https://rahulshettyacademy.com/getCourse.php")
////		.then().log().all().extract().asString();
//		
//		
//		
//	}
//
//}
