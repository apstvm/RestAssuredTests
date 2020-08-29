import org.testng.Assert;
import org.testng.annotations.Test;
import files.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	public void sumOfPrices() {
		JsonPath js = new JsonPath(Payload.CoursePrice());
		int length = js.getInt("courses.size()");
//		System.out.println("Length of courses array is " + length);
		
		//6. Verify if Sum of all Course prices matches with Purchase Amount
		int totalAmount=0;
		for(int i=0; i<length; i++) {
			totalAmount = totalAmount + (js.getInt("courses[" + i + "].price") * js.getInt("courses[" + i + "].copies"));
		}
		System.out.println("Total purchase amount is " + totalAmount);
		Assert.assertEquals(totalAmount, js.getInt("dashboard.purchaseAmount")); 		
	}

}
