package com.epsi.mediatheque;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.Key;
import java.security.KeyStore;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.yml")
@ActiveProfiles("test")
@Ignore
@Slf4j
@Data
public class AbstractMvcTest {

	@Value("${spring.security.authentication.jwt.expiration:21600}")
	private long delayExpirationToken;

	protected MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();
	private static Set<Class> inited = new HashSet<>();

	private static Key privateKey;
	public static String validToken;
	public static String expiredToken;
	public static String malformatedToken;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		mockMvc = webAppContextSetup(webApplicationContext).apply(springSecurity()).build();

	}

	@BeforeClass
	public static void init() throws Exception {
		privateKey = getRSAPrivateKey();
	}

	protected String json(Object o) throws IOException {
		return mapper.writeValueAsString(o);
	}


	private static Key getRSAPrivateKey() throws Exception {
		ClassPathResource classPathResource = new ClassPathResource("jwt.jks");

		KeyStore keystore = KeyStore.getInstance("jks");
		keystore.load(classPathResource.getInputStream(), "password".toCharArray());
		String alias = "jwt";
		return keystore.getKey(alias, "password".toCharArray());

	}

	protected String generateValidToken() {

		return Jwts.builder()
				.setClaims(generateClaims())
				.setSubject("john.doe@worldline.com")
				.setIssuer("cvp.daf.com")
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.RS256, privateKey)
				.compact();
	}

	protected String generateExpiredToken() {
		//generate an expired date.
		Date expiredDate = Date.from(Instant.now().minusSeconds(delayExpirationToken * 2));
		return Jwts.builder()
				.setClaims(generateClaims())
				.setSubject("john.doe@worldline.com")
				.setIssuer("cvp.daf.com")
				.setIssuedAt(expiredDate)
				.signWith(SignatureAlgorithm.RS256, privateKey)
				.compact();
	}

	protected String generateMalformedToken() {
		//generate a token without 'issued_at' date
		return Jwts.builder()
				.setClaims(generateClaims())
				.setSubject("john.doe@worldline.com")
				.setIssuer("cvp.daf.com")
				.signWith(SignatureAlgorithm.RS256, privateKey)
				.compact();
	}

	private Map<String, Object> generateClaims() {
		Map<String, Object> claims = new HashMap<>();
		claims.put("username", "John Doe");
		claims.put("timezone", "Europe/Paris");
		claims.put("locale", "fr_FR");
		return claims;
	}

	public static Map<String, String> splitQuery(URL url) throws UnsupportedEncodingException {
		Map<String, String> query_pairs = new LinkedHashMap<String, String>();
		String query = url.getQuery();
		String[] pairs = query.split("&");
		for (String pair : pairs) {
			int idx = pair.indexOf("=");
			query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
		}
		return query_pairs;
	}
}
