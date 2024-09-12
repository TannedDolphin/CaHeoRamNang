package cayxanh.GreencareTest.service;

import cayxanh.GreencareTest.dto.request.AuthenticationRequest;
import cayxanh.GreencareTest.dto.request.IntrospectRequest;
import cayxanh.GreencareTest.dto.request.LogoutRequest;
import cayxanh.GreencareTest.dto.response.AuthenticationResponse;
import cayxanh.GreencareTest.dto.response.IntrospectResponse;
import cayxanh.GreencareTest.entity.InvalidatedToken;
import cayxanh.GreencareTest.entity.User;
import cayxanh.GreencareTest.exception.AppException;
import cayxanh.GreencareTest.exception.ErrorCode;
import cayxanh.GreencareTest.repo.InvalidatedTokenRepo;
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
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);
    UserRepo userRepo;
    InvalidatedTokenRepo invalidatedTokenRepo;
    @NonFinal
    protected static final String SIGNER_KEY="wjGmEI+WLcPFXBXcWJzYz+jXLBlQiW4ADlGcmVYRgCFpFWn7o6V7UlLns0Z5tTv9";
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepo.findByUsername(request.getUsername()).orElseThrow(()->new AppException(ErrorCode.USER_NOT_EXISTED));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated) {
            throw new AppException(ErrorCode.AUTHENTICATION);
        }
        var token = generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        var signToken=verifyToken(request.getToken());
        String jit =signToken.getJWTClaimsSet().getJWTID();
        Date expiryTime=signToken.getJWTClaimsSet().getExpirationTime();
        InvalidatedToken invalidatedToken= InvalidatedToken.builder()
                .tokenid(jit).expiryTime(expiryTime).build();
        invalidatedTokenRepo.save(invalidatedToken);
    }

    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier=new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT singedJWT= SignedJWT.parse(token);
        Date expirationTime=singedJWT.getJWTClaimsSet().getExpirationTime();
        var verified= singedJWT.verify(verifier);
        if(!(verified && expirationTime.after(new Date())))
            throw new AppException(ErrorCode.AUTHENTICATION);
        if(invalidatedTokenRepo.existsById(singedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(ErrorCode.AUTHENTICATION);
        return singedJWT;
    }

    private String generateToken(User user) {
        JWSHeader header=new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet=new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("GreencareTest")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.DAYS).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope",buildScope(user))
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
        boolean isValid=true;
        try {
            verifyToken(token);
        }catch (AppException e) {
            isValid=false;
        }return IntrospectResponse.builder()
                .valid(isValid)
                .build();
    }
    private String buildScope(User user) {
        StringJoiner stringJoiner= new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(user.getRoles()))
            user.getRoles().forEach(stringJoiner::add);
        return stringJoiner.toString();
    }

}
