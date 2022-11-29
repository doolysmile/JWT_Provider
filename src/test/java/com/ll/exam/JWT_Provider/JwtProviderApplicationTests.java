package com.ll.exam.JWT_Provider;

import com.ll.exam.JWT_Provider.app.jwt.JwtProvider;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JwtProviderApplicationTests {

	@Autowired
	private JwtProvider jwtProvider;

	@Test
	void contextLoads() {
	}
	@Value("${custom.jwt.secretKey}")
	private String secretKeyPlain;

	@Test
	@DisplayName("seretKeyPlain이 정의 되어 있어야 한다")
	void t1() {
		assertThat(secretKeyPlain).isNotNull();
	}

	@Test
	@DisplayName("sercretKey 원문으로 hmac 암호화 알고리즘에 맞는 SecretKey 객체를 만들 수 있다.")
	void t2() {
		// Base64로 encoding
		String keyBase64Encoded = Base64.getEncoder().encodeToString(secretKeyPlain.getBytes());
		// hmac 암호화 알고리즘으로 SecretKey 객체 생성
		SecretKey secretKey = Keys.hmacShaKeyFor(keyBase64Encoded.getBytes());

		assertThat(secretKey).isNotNull();
	}

	@Test
	@DisplayName("JwtProvider 객체로 시크릿키 객체를 생성할 수 있다.")
	void t3() {
		SecretKey secretKey = jwtProvider.getSecretKey();

		assertThat(secretKey).isNotNull();
	}

	@Test
	@DisplayName("SecretKey 객체는 단 한번만 생성되어야 한다.")
	void t4() {
		SecretKey secretKey1 = jwtProvider.getSecretKey();
		SecretKey secretKey2 = jwtProvider.getSecretKey();

		assertThat(secretKey1 == secretKey2).isTrue();
	}
}
