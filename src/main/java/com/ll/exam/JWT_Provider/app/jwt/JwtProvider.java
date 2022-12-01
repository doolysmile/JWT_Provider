package com.ll.exam.JWT_Provider.app.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;


@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final SecretKey jwtSecretKey;

    private SecretKey getSecretKey() {
        return jwtSecretKey;
    }
}
