package org.example.authservice.service;

import com.auth0.jwt.algorithms.Algorithm;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.example.authservice.DAO.CandidateImpl;
import org.example.authservice.config.AuthConfig;
import org.example.authservice.exceptions.UserNotFoundException;
import org.example.authservice.models.Candidate;
import org.example.authservice.models.RefreshToken;
import org.example.authservice.service.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;


@Component
public class Service {

    @Autowired
    private CandidateImpl candidateImpl;

    private final Utils utils = new Utils();
    private final long ACCESS_TOKEN_LIFE_TIME = 3000;  // 5 minute 180
    private final long REFRESH_TOKEN_LIFE_TIME = 600;   // 3 month 3600 * 24 * 30
    private final HashMap<String, String> TOKENS = new HashMap<>();
    private final Algorithm algorithm ;

    public Service(AuthConfig authConfig) {
        algorithm = Algorithm.HMAC256(authConfig.getKey());
    }

    public DecodedJWT verifyAccessToken(String token) {
        return utils.verifyToken(algorithm, token, ACCESS_TOKEN_LIFE_TIME);
    }

    public Candidate checkUser(Candidate candidate) {
        Optional<Candidate> optCandidate = candidateImpl.checkUser(candidate);

        if (optCandidate.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        return optCandidate.get();
    }

    public Candidate getUserByRefreshToken(String token) {
        DecodedJWT decodedJWT = utils.verifyToken(algorithm, token, REFRESH_TOKEN_LIFE_TIME);

        String id = decodedJWT.getClaim("id").toString();
        int userId = Integer.parseInt(id);

        Optional<Candidate> user = candidateImpl.getUser(userId);
        Optional<RefreshToken> refreshToken = candidateImpl.getRefreshToken(new RefreshToken(userId, token));

        if (refreshToken.isEmpty()) {
            throw new JWTVerificationException("Invalid refresh token");
        }
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return user.get();
    }

    public HashMap<String, String> createTokens(Candidate user) {
        String accessToken = utils.createJwt(algorithm, user, ACCESS_TOKEN_LIFE_TIME);
        String refreshToken = utils.createJwt(algorithm, user, REFRESH_TOKEN_LIFE_TIME);

        candidateImpl.createRefreshToken(new RefreshToken(user.getId(), refreshToken));

        TOKENS.put("accessToken", accessToken);
        TOKENS.put("refreshToken", refreshToken);
        return TOKENS;
    }


}
