import org.testng.Assert;

import files.Payload;


import io.restassured.path.json.JsonPath;

public class ComplexJson {
	
	public static void main(String args[]) {		
		int sum = 0;
		JsonPath js = new JsonPath(Payload.CoursePrice());
	
		//1. Print No of courses returned by API
		int courseLength = js.getInt("courses.size()");
		System.out.println("Length of courses array is " + courseLength);
		
//		2. Print Purchase Amount
		System.out.println("Purchase amount is " + js.getInt("dashboard.purchaseAmount"));

		// 3. Print Title of the first course
		System.out.println("Title of first course is " + js.getString("courses[0].title"));
//		

		// Print All course titles and their respective Prices
		for(int i=0; i<courseLength; i++) {
			System.out.println("Title of course " + (i+1) + " is " + js.get("courses[" + i + "].title").toString());
//			System.out.println("Price of course " + (i+1) + " is " + js.get("courses[" + i + "].price").toString());
			sum = sum + js.getInt("courses[" + i + "].price")*js.getInt("courses[" + i + "].copies");
		}
		Assert.assertEquals(sum, js.getInt("dashboard.purchaseAmount"));
		System.out.println("sum is purchase amount");
		//		 Print no of copies sold by RPA Course
//		String courseName = "RPA";
//		boolean isFound = false;
//		for(int i=0; i<length; i++) {
//			if(js.get("courses[" + i + "].title").toString().equalsIgnoreCase(courseName)) {
//				isFound = true;
//				System.out.println("Number of copies of " + js.get("courses[" + i + "].title")
//				.toString() + " is " + js.get("courses[" + i + "].copies").toString());
//				break;
//			} 
//		}
//		if(!isFound) System.out.println("Title not found");
		String courseName = "RPA";
		
		for(int j=0; j<courseLength; j++) {
			if(js.getString("courses[" + j + "].title").equalsIgnoreCase(courseName))
				System.out.println("Number of copies sold by " + courseName + " is " + js.getString("courses[" + j + "].copies"));
				
		}

	}

}
