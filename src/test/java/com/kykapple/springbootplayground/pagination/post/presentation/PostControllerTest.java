package com.kykapple.springbootplayground.pagination.post.presentation;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void testForDebug() {
        // given

        given()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("writer", "kyk")
                .param("contents", "text")
                .param("tags", "\"[\"backend\", \"bug fix\", \"enhancement\"]")

        // when
        .when()
                .post("/api/posts")

        // then
        .then()
                .log().all()
                .statusCode(HttpStatus.OK.value());
    }

}