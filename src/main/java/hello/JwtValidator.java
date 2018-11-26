package hello;




import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;



public class JwtValidator {
	
	public static boolean isValid(String token) {		
		
		// peel off the prefix
		if(token.startsWith("Bearer "))
    		token = token.substring(7);    	
		
		try {
			// SHA-256
		    Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.SECRET);
		    JWTVerifier verifier = JWT.require(algorithm)
//		        .withIssuer("auth0")						// sample inline claim to validate
		        .build(); 									// Reusable verifier instance
		    
		    DecodedJWT jwt = verifier.verify(token);
		    
		    logClaims(jwt);
		    
		    return true;
		} catch (JWTVerificationException exception) {
		    //Invalid signature/claims
			return false;
		}
		
	}
	
	private static void logClaims(DecodedJWT jwt) {
		
		Map<String, Claim> claims = jwt.getClaims();
		
		StringBuffer out = new StringBuffer();
		
		for (Entry<String, Claim> pair : claims.entrySet()){
	        //iterate over the pairs
			String value = pair.getValue().asString();
			if(pair.getKey() == "exp" || pair.getKey() == "iat")
				value = pair.getValue().asDate().toString();
			
	        out.append("\n"+pair.getKey()+" "+value);			
	    }
		
		Logger logger = LoggerFactory.getLogger(JwtValidator.class);
		logger.info(out.toString());
	}
		
}
