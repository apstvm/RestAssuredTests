import io.restassured.path.json.JsonPath;

public class CommonFuncs{
	
	public JsonPath rawToJson(String path) {
		JsonPath js = new JsonPath(path);
		return js;
	}
	
	public String convertString(String path) {
		JsonPath js = new JsonPath(path);
		return js.getString(path);
		
	}
	


}
