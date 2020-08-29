import org.testng.annotations.Ignore;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import static io.restassured.RestAssured.*;
import java.io.File;


public class OldJiraTests {
	
	public String authenticateJira() {
		RestAssured.baseURI = "http://localhost:8080";
		String SessionID = given().header("Content-Type","application/json")
				.body("{ \n" + 
						"	\"username\": \"arunsaccount\", \n" + 
						"	\"password\": \"6756\" \n" + 
						"}")
				.post("/rest/auth/1/session")
				.then().statusCode(200).extract().response().asString();
		return SessionID;
	}
	
	@Ignore
	public void AddIssue() {
		RestAssured.baseURI = "http://localhost:8080";
//		String sessionDetails = authenticateJira();
//		JsonPath js = new JsonPath(sessionDetails);
//		String ID = js.getString("session.value");
//		System.out.println(ID);
//		String jsession = "JSESSIONID=".concat(ID);
		
		SessionFilter session = new SessionFilter();
		given().header("Content-Type","application/json")
		.body("{ \n" + 
				"	\"username\": \"arunsaccount\", \n" + 
				"	\"password\": \"6756\" \n" + 
				"}")
		.filter(session)
		.post("/rest/auth/1/session")
		.then().statusCode(200).extract().response().asString();
		
		
		String newIssueDetails = given().header("Content-Type","application/json")
//				.header("Cookie","JSESSIONID=".concat(ID))
				.header("Content-Type", "application/json")
				.body("{\n" + 
						"    \"fields\": {\n" + 
						"        \"project\": {\n" + 
						"            \"key\": \"TES\"\n" + 
						"        },\n" + 
						"        \"summary\": \"Bug from API code 11:58pm\",\n" + 
						"        \"issuetype\": {\n" + 
						"            \"name\": \"Bug\"\n" + 
						"        },\n" + 
						"        \"reporter\": {\n" + 
						"            \"name\": \"arunsaccount\"\n" + 
						"        }\n" + 
						"	}\n" + 
						"}")
				.filter(session)
				.post("/rest/api/2/issue")
				.then().statusCode(201).extract().response().asString();
		System.out.println(newIssueDetails);		
	}
	
//	@Test
	public void addComment() {
		
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
		given().header("Content-Type", "application/json")
		.pathParam("key", "10105")
		.body("{\n" + 
				"    \"body\": \"Adding a comment from API\"\n" + 
				"}")
		.filter(session)
		.post("/rest/api/2/issue/{key}/comment")
		.then().assertThat().statusCode(201);
		
	}
	
//	@Test
	public void addAttachment() {
		
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
		
		given().pathParam("key", "10105")
		.filter(session)
		.header("X-Atlassian-Token","no-check")
		.multiPart("file", new File("/Users/arunps/Downloads/JIRA/JIRA Server/data/attachments/test.PNG"))
		.post("/rest/api/2/issue/{key}/attachments");
	}

}
