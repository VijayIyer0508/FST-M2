package Liveprojects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junit5.HttpTestTarget;


@Provider("UserProvider")
@PactFolder("targert/pacts")
public class ProviderTest {
  @BeforeEach
 
  public void setUp(PactVerificationContext context) {
	 HttpTestTarget target = new HttpTestTarget("localhost",8585);
      context.setTarget(target); 	  
  }
  
  @TestTemplate
  @ExtendWith(PactVerificationInvocationContextProvider.class)
  public void ProviderTest(PactVerificationContext context) {
	  context.verifyInteraction();
  }
  @State("A request to create a user")
  public void state1() {}
}
