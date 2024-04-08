package co.edu.uniquindio.uniLocal.utils;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component


    public class JWTUtils {
        public String generarToken(String email, Map<String, Object> claims){
            Instant now = Instant.now();

            return Jwts.builder()
                    .claims(claims)
                    .subject(email)
                    .issuedAt(Date.from(now))
                    .expiration(Date.from(now.plus(1L, ChronoUnit.HOURS)))
                    .signWith( getKey() )
                    .compact();

        }

        public Jws<Claims> parseJwt(String jwtString) throws ExpiredJwtException,
                UnsupportedJwtException, MalformedJwtException, IllegalArgumentException {
            JwtParser jwtParser = Jwts.parser().verifyWith( getKey() ).build();
            return jwtParser.parseSignedClaims(jwtString);
        }

        private SecretKey getKey(){
            String claveSecreta = "secretsecretsecretsecretsecretsecretsecretsecret";
            byte[] secretKeyBytes = claveSecreta.getBytes();
            return Keys.hmacShaKeyFor(secretKeyBytes);
        }

    public Map<String,String> desencriptarToken(String token) throws Exception{
        String jwtToken = token.substring(7); // Suponiendo que el token comienza con "Bearer "
        System.out.println(jwtToken);

        try {
            // Decodificar el token
            SignedJWT signedJWT = SignedJWT.parse(jwtToken);

            // Verificar la firma utilizando la clave secreta
            JWSVerifier verifier = new MACVerifier("secretsecretsecretsecretsecretsecretsecretsecret");
            if (!signedJWT.verify(verifier)) {
                throw new RuntimeException("Firma de token inválida");
            }

            // Extraer el payload
            JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();

            // Insertar en un Map<String, String>
            Map<String, String> jwtMap = new HashMap<>();
            for (Map.Entry<String, Object> entry : claimsSet.getClaims().entrySet()) {
                jwtMap.put(entry.getKey(), entry.getValue().toString());
            }

            return jwtMap;

        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
}



