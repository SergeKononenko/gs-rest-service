package hello;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtMaker {
	
	/***********
	 * 
	 * 
	 * @param subject
	 * @param duration - lifespan of token in hours
	 * @param name
	 * @param audience
	 * @return - complete JWT token
	 * @throws Exception - params, create fail
	 */

	public static String makeJwt(String subject, int duration, String name, String audience) throws Exception {
		
		// necessary here? SpringBoot seems to do it already
		if( subject.isEmpty() || duration < 1 || name.isEmpty() || audience.isEmpty() ) {
			log("Invalid params", "error");
			throw new Exception("Invalid params!");
		}
		
		try {
			// SHA-256
		    Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.SECRET);
		    
		    Calendar now = Calendar.getInstance();
		    Calendar expires = Calendar.getInstance();
		    expires.add(Calendar.HOUR_OF_DAY, duration);             // 1 hour
		    
		    // as per Showzeb's spec
		    String token = JWT.create()
		    		.withSubject(subject)
		    		.withClaim("name", name)
		    		.withIssuedAt(now.getTime())
		    		.withExpiresAt(expires.getTime())
		    		.withIssuer("teamlabs")
		    		.withAudience(audience)  // is this scope? will this actually be an array?
		    		.sign(algorithm);
		    
		    return token;
		} catch (JWTCreationException ex){
		    // Invalid Signing configuration / Couldn't convert Claims.
			log(ex.getMessage(), "error");
			throw ex;
		}		
	}
	
	private static void log(String message, String level) {		
		
		Logger logger = LoggerFactory.getLogger(JwtValidator.class);
		if(level.toLowerCase() == "error")
			logger.error(message);
		else
			logger.info(message);
	}

}

/***************************
 * 
 * {
 “sub”: “1234567890",
 “name”: “John Doe”,
 “iat”: 1516239022,
 “exp”: 1600000000,
 “iss”: “auth0",
 “aud”: “external”
} 

***************************/
