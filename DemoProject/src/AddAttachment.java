import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class AddAttachment {

	public static void main(String[] args) {
//		addAttachment();
		int commentID = addComment();
		getIssue(commentID);
		
		
		// TODO Auto-generated method stub

	}
	
	public static int addComment() {
		
		RestAssured.baseURI = "http://localhost:8080";		
		SessionFilter session = new SessionFilter();
		given().header("Content-Type","application/json")
		.body("{ \n" + 
				"	\"username\": \"arunsaccount\", \n" + 
				"	\"password\": \"6756\" \n" + 
				"}")
		.filter(session)
		.post("/rest/auth/1/session")
		.then().statusCode(200).extract().response().asString();
		
		System.out.println("Adding comment");
		String commentResponse = given().header("Content-Type", "application/json")
		.pathParam("key", "10101")
		.body("{\n" + 
				"    \"body\": \"A final comment on this bug from API\"\n" + 
				"}")
		.filter(session)
		.post("/rest/api/2/issue/{key}/comment")
		.then().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js = new JsonPath(commentResponse);
		int commentID = js.getInt("id");
		return commentID;
		
	}
	
	public static void addAttachment() {
		
		RestAssured.baseURI = "http://localhost:8080";		
		SessionFilter session = new SessionFilter();
		given().header("Content-Type","application/json")
		.body("{ \n" + 
				"	\"username\": \"arunsaccount\", \n" + 
				"	\"password\": \"6756\" \n" + 
				"}")
		.filter(session)
		.post("/rest/auth/1/session")
		.then().statusCode(200).extract().response().asString();
		
		given().pathParam("key", "10101")
		.filter(session)
		.header("X-Atlassian-Token","no-check")
		.header("Content-Type","multipart/form-data")
		.multiPart("file", new File("/Users/arunps/Downloads/JIRA/JIRA Server/data/attachments/test.PNG"))
		.post("/rest/api/2/issue/{key}/attachments").then().assertThat().statusCode(200);
	}
	
	public static void getIssue(int commentID) {
		RestAssured.baseURI = "http://localhost:8080";		
		SessionFilter session = new SessionFilter();
		given().header("Content-Type","application/json")
		.body("{ \n" + 
				"	\"username\": \"arunsaccount\", \n" + 
				"	\"password\": \"6756\" \n" + 
				"}")
		.filter(session)
		.post("/rest/auth/1/session")
		.then().statusCode(200).extract().response().asString();
		
		String response = given()
		.queryParam("fields","comment")
//		.queryParam("expand", "false")
//		.queryParam("properties", arg1)
		.pathParam("key", "10101")
		.filter(session)
		.get("/rest/api/2/issue/{key}")
		.then().log().all().extract().response().asString();
//		.assertThat().statusCode(200);
		
		JsonPath js = new JsonPath(response);
		int size = js.getInt("fields.comment.comments.size()");
		
		for(int i=0; i<size; i++) {
			if(js.getInt("fields.comment.comments["+i+"].id") == commentID) {
				System.out.println("Match");
				System.out.println("Comment with ID " + js.getInt("fields.comment.comments["+i+"].id") + " is " + js.getString("fields.comment.comments["+i+"].body"));
//				System.out.println(js.getInt());
			}
		}
//		System.out.println("Response is " + response);
		
	}

}
