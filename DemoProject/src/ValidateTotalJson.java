import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ValidateTotalJson {
	
	@Test
	public static void validateSum() {
		int total = 0;
		System.out.println("Checking if sum is correct");
		
//		String response = given().
		
		JsonPath json = new JsonPath(Payload.CoursePrice());
//		System.out.println("Total is " + json.getInt("dashboard.purchaseAmount"));
		
		int size = json.getInt("courses.size()");
		
		for (int i=0; i<size; i++) {
			System.out.println(i);
			total = total + json.getInt("courses["+i+"].price")*json.getInt("courses["+i+"].copies");
		}
		System.out.println(total);
		Assert.assertEquals(total, json.getInt("dashboard.purchaseAmount"));
		System.out.println("Success");
		
		
		
	}
	

}
