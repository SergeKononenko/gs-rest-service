// quickstart from https://spring.io/guides/gs/rest-service/

package hello;

public class CreateJwtToken {

	// String subject, int duration, String name, String audience
	private static final String template = "Helloooo, %s!";
    private final long id = 0;
    
    private final String subject;

	private final int duration; 
    private final String name; 
    private final String audience;
    
    private String token; 
   
    public CreateJwtToken(String subject, int duration, String name, String audience){
        
    	this.subject = subject;
    	this.duration = duration;
    	this.name = name;
    	this.audience = audience;
    	
    	try {
    		this.token = JwtMaker.makeJwt(subject, duration, name, audience);
    	}
    	catch (Exception ex) {
    		this.token = "error!";
    	}

    }

    public long getId() {
        return id;
    }

    public String getSubject() {
		return subject;
	}

	public int getDuration() {
		return duration;
	}

	public String getName() {
		return name;
	}

	public String getAudience() {
		return audience;
	}
    
	public String getToken() {
		return token;
	}
}
