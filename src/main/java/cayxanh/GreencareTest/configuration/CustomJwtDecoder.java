package cayxanh.GreencareTest.configuration;

import cayxanh.GreencareTest.dto.request.IntrospectRequest;
import cayxanh.GreencareTest.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Objects;
import java.util.logging.Logger;

@Component
public class CustomJwtDecoder implements JwtDecoder {
    private static final String SIGNER_KEY = "wjGmEI+WLcPFXBXcWJzYz+jXLBlQiW4ADlGcmVYRgCFpFWn7o6V7UlLns0Z5tTv9";

    @Autowired
    private AuthenticationService authenticationService;

    private NimbusJwtDecoder nimbusJwtDecoder = null;

    private static final Logger logger = Logger.getLogger(CustomJwtDecoder.class.getName());

    public void setNimbusJwtDecoder(NimbusJwtDecoder nimbusJwtDecoder) {
        this.nimbusJwtDecoder = nimbusJwtDecoder;
    }

    @Override
    public Jwt decode(String token) throws JwtException {
        if (token == null || token.trim().isEmpty()) {
            throw new JwtException("Token is null or empty");
        }

        try {
            var response = authenticationService.introspect(
                    IntrospectRequest.builder().token(token).build());

            if (!response.isValid()) throw new JwtException("Token invalid");
        } catch (JOSEException | ParseException e) {
            throw new JwtException(e.getMessage());
        }

        if (Objects.isNull(nimbusJwtDecoder)) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(SIGNER_KEY.getBytes(), "HS512");
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();
        }

        try {
            return nimbusJwtDecoder.decode(token);
        } catch (JwtException e) {
            logger.severe("Failed to decode JWT: " + e.getMessage());
            throw e;
        }
    }

}
