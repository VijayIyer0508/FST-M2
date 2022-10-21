package Liveprojects;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.BeforeClass;

import java.util.*;


public class ProjectApiTestTest {
	
	String  sshKey = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCRWEZlnekCx4oGunhwTrfxaj0TJirGEZxXOUdEKsRYyj6u5EcGjYmYsrt8fzHF7hUK3nbMOfVC+Wi5gqVO7Y1rU1SBVwoROP/lbYMQXdvcdCZCD+7FRlt4gCUJigL99qkQsGrrHxh9s7AjDkYpe2YU0t9ApT72cm7/kvnlvxwS1cFczFU5cJJBHh8U2+sc+qxMb3QpAWuhWoE50dP0B9Wv7WWbTDAVXg+uLny/wnVuR49fRpXgXlqsfm14u9hT947KvWU7tAbNZmKoKxKuEiK9+qsLLQVGZSBokq3EDTsPd5jOvX1Xgz3WF7Yu1ACcBPUXyjsSLwCK2MlO0cvZo1Nv";
  	int sshid;
    RequestSpecification rs;
  
  @BeforeClass
  public void setUp() {
	  	      rs= new RequestSpecBuilder()
	  			.setBaseUri("https://api.github.com")
	  			.addHeader("Content-type","application/json")
	  			.addHeader("Authorization","token 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")
	  			.build();
	  	     }	
  
  @Test (priority=1)
  public void posMethodTest() {
	  
	  Map <String,String> reqBody = new HashMap<String, String>();
	  reqBody.put("title", "testKey");
	  reqBody.put("key", sshKey);
	  
		        Response response = 
		            given().spec(rs) 
		            .body(reqBody) // Add request body
		            .when().post("/user/keys");
	System.out.println(response.getBody().prettyPrint());
	sshid =response.then().extract().path("id");
	response.then().statusCode(201).body("key",equalTo(sshKey));
  }
	  		  
  @Test (priority=2)
  public void getMethodTest() {
	  
		        Response response = 
		            given().spec(rs).pathParam("keyId",sshid) 
		            .when().get("/user/keys/{keyId}");
	System.out.println(response.getBody().prettyPrint());
	sshid =response.then().extract().path("id");
	response.then().statusCode(200).body("key",equalTo(sshKey)); 
      }
  
  @Test (priority=3)
  public void deleteMethodTest() {
  
	  Response response = 
	            given().spec(rs).pathParam("keyId",sshid) 
	            .when().delete("/user/keys/{keyId}");
System.out.println(response.getBody().prettyPrint());
sshid =response.then().extract().path("id");
response.then().statusCode(204);
      }
  }

