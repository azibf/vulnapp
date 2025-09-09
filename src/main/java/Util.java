public class Util {
}
public class JwtTokenUtil {
    private Algorithm algorithmHS;
    private JWTVerifier verifier;
    @Value("${jwt.api.issuer}")
    private String loginApiIssuer;
    @Value("${jwt.api.jwtExpiration}")
    private int jwtExpiration;

    public String generateAccessToken(final Long userId, final String userName, final List<String> roles) {
        return JWT.create().withIssuer(loginApiIssuer).withSubject(userName).withIssuedAt(getIssuedAt())
                .withClaim("id", userId).withClaim("roles", roles).withExpiresAt(getExpireAt()).sign(getAlgorithmHS());
    }
    private Algorithm getAlgorithmHS() {
        if (algorithmHS == null) {
            try {
                File resource = new ClassPathResource("jwt-secret").getFile();
                String text = new String(Files.readAllBytes(resource.toPath()), Charset.defaultCharset());
                algorithmHS = Algorithm.HMAC512(text);
            } catch (IOException e) {
                // Invalid signature/claims
                log.error("Error getting expiration date: " + e.getMessage(), e);
            }
        }
        return algorithmHS;
    }
    public DecodedJWT getDecodedJWT(final String token) {
        DecodedJWT jwt = null;
        try {
            jwt = getVerifier().verify(token);
        } catch (JWTVerificationException e) {
            // Invalid signature/claims
            log.error("Error validating token JWT: " + e.getMessage(), e);
        }
        return jwt;
    }
    public UserDetails getUserDetails(final String token) {
        DecodedJWT decoded = getDecodedJWT(token);
        return JWTUserDetailsImpl.build(decoded.getClaim("id").asLong(), decoded.getSubject(),
                decoded.getClaim("roles").asList(String.class));
    }
    private JWTVerifier getVerifier() {
        if (verifier == null) {
            verifier = JWT.require(getAlgorithmHS()).withIssuer(loginApiIssuer).build();
        }
        return verifier;
    }
    public boolean validate(final String token) {
        return getDecodedJWT(token) != null;
    }
    //more utilities
}