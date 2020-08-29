import org.testng.annotations.Test;
import files.*;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class JiraTests {
	
	@Test
	public void jiraLogin() {
		RestAssured.baseURI = "http://localhost:8080/";
		System.out.println("Test this");
		SessionFilter session = new SessionFilter();
		given().header("Content-Type", "application/json")
				.body("{ \n" + 
						"	\"username\": \"arunsaccount\", \n" + 
						"	\"password\": \"6756\" \n" + 
						"}")
				.filter(session)
				.when().post("http://localhost:8080/rest/auth/1/session")
				.then().statusCode(200);
	
		System.out.println("Authenticated");
		System.out.println("Session ID is " + session.getSessionId());
		
		String response = given().header("Content-Type", "application/json")
				.body("{\n" + 
						"    \"fields\": {\n" + 
						"        \"project\": {\n" + 
						"            \"key\": \"RAA\"\n" + 
						"        },\n" + 
						"        \"summary\": \"ninth bug in new project\",\n" + 
						"        \"issuetype\": {\n" + 
						"            \"name\": \"Bug\"\n" + 
						"        },\n" + 
						"        \"reporter\": {\n" + 
						"            \"name\": \"arunsaccount\"\n" + 
						"        }\n" + 
						"	}\n" + 
						"}")
				.filter(session)
				.when().post("/rest/api/2/issue")
				.then().extract().response().asString();
		JsonPath json = ReusableMethods.rawToJson(response);
		System.out.println("Bug created. Bug ID is " + json.getString("id") + " and Bug is " + json.getString("key"));
		String key = json.getString("key");
		System.out.println("Adding attachment now");
		
		given().header("X-Atlassian-Token", "no-check")
		.pathParam("key", key)
		.header("Content-Type","multipart/form-data")
		.multiPart("file", new File("/Users/arunps/Downloads/test.txt"))
		.filter(session)
		.when().post("/rest/api/2/issue/{key}/attachments")
		.then().assertThat().statusCode(200);
		System.out.println("File attached to " + key);
		
		System.out.println("Getting issue " + key);
		
		given().filter(session).pathParam("key", key)
		.queryParam("fields", "description")
//		.queryParam("properties" , arg1)
		.when().get("/rest/api/2/issue/{key}")
		.then().assertThat().statusCode(200).log().all();
				
	}
	

}
