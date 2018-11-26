
package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="Worldddd") String name) {
    	
    	return WebRequest.makeFakeRequest("http://10.1.5.34:8080", "/greeting");
        	
    }
    
    @RequestMapping("/protected_greeting")
    public Object validateGreeting(
    		@RequestParam(value="name", defaultValue="World") String name, 
    		@RequestHeader(value=SecurityConstants.HEADER_STRING, defaultValue="none") String auth
    		) {
    	
    	if(isValid(auth)) {
    		return new Greeting(counter.incrementAndGet(), name);
    		
    	} else {
    		
    		return new Deny();
    	}
    }
    
    @RequestMapping("/create_token")
    public CreateJwtToken cjt (
    		@RequestParam(value="subject") String subject,
    		@RequestParam(value="duration") int duration,
    		@RequestParam(value="name") String name,
    		@RequestParam(value="audience") String audience
    		
    		) {  	
    	
    	return new CreateJwtToken(subject, duration, name, audience);
    }
    
    public boolean isValid(String token) {    	
    	
    	return JwtValidator.isValid(token);
    }
    
   
}
