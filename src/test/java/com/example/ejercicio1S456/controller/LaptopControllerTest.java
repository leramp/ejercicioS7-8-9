package com.example.ejercicio1S456.controller;

import com.example.ejercicio1S456.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //aca no ejecutamos métodos, lanzamos peticiones http y recibir respuestas responseentity
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;
    @Autowired //aca le decimos a spring que nos ineyecte un builder
    //eso lo vamos a utilizar para generar el objeto testRestTemplate
    //el cual nos permite lanzar las peticiones http vinculadas a
    //esa web con ese puerto
    private RestTemplateBuilder restTemplateBuilder;
//esto objeto es el que a partir de unos metodos
    //nos va a permitir utilizar los metodos http
    //en vez de hacer las llamadas desde postman
    //las hacemos desde el propia java en spring boot

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {//esto incializa el restTemplateBuilder
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response =
        testRestTemplate.getForEntity("/api/laoptops", Laptop[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        List<Laptop> laptops = Arrays.asList((response.getBody()));
        System.out.println(laptops.size());
    }

    @Test
    void findOneById() {

        ResponseEntity<Laptop> response =
                testRestTemplate.getForEntity("/api/laptops/1", Laptop.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());


    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json= """
                {
                    "modelo": "nuevo",
                    "precio": 555.0,
                    "tactil": false
                }
                """;
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("api/laptops", HttpMethod.POST, request, Laptop.class);
        Laptop result = response.getBody();
        assertEquals(1L, result.getId());
        assertEquals("Ordenador creado desde Spring Test", result.getModelo());
    }
}