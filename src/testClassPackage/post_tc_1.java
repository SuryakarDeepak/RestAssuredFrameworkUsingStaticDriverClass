package testClassPackage;

import org.testng.Assert;

import  commonFunctionPackage.API_Common_Function;
import io.restassured.path.json.JsonPath;
import requestRepositoryPackage.post_req_repository1;

public class post_tc_1 {
    
	public static void execute() {
		for(int i=0 ; i<5 ;i++)
		{
			int res_status_code=API_Common_Function.response_statuscode(post_req_repository1.base_URI(),post_req_repository1.post_resource(),post_req_repository1.post_req_tc1());
			if(res_status_code == 200)
			{
			 String res_Body=API_Common_Function.response_Body(post_req_repository1.base_URI(),post_req_repository1.post_resource(),post_req_repository1.post_req_tc1());
			 post_tc_1.validator(res_Body, res_status_code);
			 break;
			}
			else
			{
				System.out.println("correct status code is not found hence retrying the API");
			}
				
			}
		}
			
        public static void validator(String res_Body,int res_status_code) {
		//parse	the response body
		JsonPath jsp=new JsonPath(res_Body);
		String res_name=jsp.getString("name");
		String res_job=jsp.getString("job");
		String res_id=jsp.getString("id");
		String res_createdAt=jsp.getString("createdAt");
		System.out.println(res_name);
		System.out.println(res_job);
		System.out.println(res_id);
		System.out.println(res_createdAt);

		
	//validate response body 
    Assert.assertEquals(res_status_code,201);
	Assert.assertEquals(res_name,"morpheus");
	Assert.assertEquals(res_job,"leader");
	Assert.assertNotNull(res_id);
	Assert.assertNotNull(res_createdAt);
		
	}
}
