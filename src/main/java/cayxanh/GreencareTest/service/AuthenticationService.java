package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.AuthenticationRequest;
import cayxanh.GreencareTest.dto.request.IntrospectRequest;
import cayxanh.GreencareTest.dto.response.AuthenticationResponse;
import cayxanh.GreencareTest.dto.response.IntrospectResponse;
import cayxanh.GreencareTest.exception.AppException;
import cayxanh.GreencareTest.exception.ErrorCode;
import cayxanh.GreencareTest.repo.UserRepo;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);
    UserRepo userRepo;
    @NonFinal
    protected static final String SIGNER_KEY="wjGmEI+WLcPFXBXcWJzYz+jXLBlQiW4ADlGcmVYRgCFpFWn7o6V7UlLns0Z5tTv9";
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepo.findByUsername(request.getUsername()).orElseThrow(()->new AppException(ErrorCode.USER_NONEXISTED));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated) {
            throw new AppException(ErrorCode.AUTHENTICATION);
        }
        var token = generateToken(request.getUsername());
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }
    private String generateToken(String username) {
        JWSHeader header=new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet=new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("GreencareTest")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.DAYS).toEpochMilli()
                ))
                .claim("userid","Custom")
                .build();
        Payload payload=new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject=new JWSObject(header,payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("cannot create token", e);
            throw new RuntimeException(e);
        }
    }
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token=request.getToken();
        JWSVerifier verifier=new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT singedJWT= SignedJWT.parse(token);
        Date expirationTime=singedJWT.getJWTClaimsSet().getExpirationTime();
        var verified= singedJWT.verify(verifier);
        return IntrospectResponse.builder()
                .valid(verified && expirationTime.after(new Date()))
                .build();
    }
}
