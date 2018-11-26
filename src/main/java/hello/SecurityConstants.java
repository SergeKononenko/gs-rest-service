package hello;

public class SecurityConstants {
	
	public static final String ENCRYPT_SECRET = "ThisIsTheMostSecretestAsLongAsNobodyTellsAnyoneEver"; // Don't tell!
	
	public static final String SECRET = "SecretKeyToGenJWTs"; //TODO read from file; this is the fallback
	
    public static final long EXPIRATION_TIME = 864_000_000; // TODO - replace with Date gen that adds lifespan to current date
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    
    public static final String SIGN_UP_URL = "/hello";  // TODO

}
// from jwt.io
// eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJ0aGluZyI6InZhbHVlIn0.p_ytK1FH1vbSZyI_KpuOQmlIs_FV1JIKKDPjqgjqnPo

/***********************************************
{
 “sub”: “1234567890",
 “name”: “John Doe”,
 “iat”: 1516239022,
 “exp”: 1600000000,
 “iss”: “auth0",
 “aud”: “external”
} 

        jwt.getClaim("sub").asString(),          jwt.getSubject()
        jwt.getClaim("name").asString(),         (custom)
        jwt.getClaim("iat").asLong(),            jwt.getIssuedAt();
        jwt.getClaim("exp").asLong(),			 jwt.getExpiresAt();	    Date
        jwt.getClaim("iss").asString(),			 jwt.getIssuer();
        jwt.getClaim("aud").asString();          jwt.getAudience();         List<String>




***********************************************/