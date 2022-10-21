package Liveprojects;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.RequestResponsePact;
import java.util.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

@ExtendWith(PactConsumerTestExt.class)
public class ConsumerTest {
 //Headers object
	
	Map<String, String> headers = new HashMap <String, String> ();
	
	String resourcePath ="/api/users";
	
// Generated a contract
	
	@Pact(consumer ="UserConsumer", provider ="UserProvider")
	public RequestResponsePact createPact(PactDslWithProvider builder)
	{
		//Add the headers
		headers.put("Content-Type", "application/json");
	  
		// Create the Json body for request and response
		DslPart requestResponseBody = new PactDslJsonBody()
	                	.numberType("id" ,123)
	                    .stringType("firstName" ,"Vijay")
	                    .stringType("lastName" ,"Iyer")
                        .stringType("email" ,"Iyer@example.com");
		
		//Write the Fragement to pact
		
		return builder.given("A request to create a user")
		    .uponReceiving("A request to create a User")
		    .method("POST")
		    .headers(headers)
		    .path(resourcePath)
		    .body(requestResponseBody)
		    .willRespondWith()
		    .status(201)
		    .body(requestResponseBody)
		    .toPact();
		    
	}                  
	
	@Test
	@PactTestFor(providerName ="UserProvider", port ="8282")
	public void consumerTest() {
	  // BaseUri
		String baseURI ="http://localhost:8282"+resourcePath;
		
		Map<String,Object> reqBody =new HashMap<String, Object> ();
		reqBody.put("id" ,123);
		reqBody.put("firstName" ,"Vijay");
		reqBody.put("lastName" ,"Iyer");
		reqBody.put("email" ,"Iyer@example.com");
		
		//Generate Response
		given().headers(headers).body(reqBody).log().all().
        when().post(baseURI).
        then().statusCode(201).log().all();
		
	}
	
}
