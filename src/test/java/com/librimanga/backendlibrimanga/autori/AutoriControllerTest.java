package com.librimanga.backendlibrimanga.autori;

import com.librimanga.backendlibrimanga.BaseTest;
import com.librimanga.backendlibrimanga.controller.AutoriController;
import com.librimanga.backendlibrimanga.dto.inputs.AutoriReq;
import com.librimanga.backendlibrimanga.dto.outputs.AutoriDTO;
import com.librimanga.backendlibrimanga.response.Resp;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AutoriControllerTest extends BaseTest {
    @Autowired
    private AutoriController controller;

    @Test
    @Order(1)
    public void createAutore() {
        AutoriReq req = new AutoriReq();
        req.setNome("nome");
        req.setCognome("cognome");

        ResponseEntity<Resp> resp = controller.create(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = resp.getBody();

        Assertions.assertThat(r.getMsg()).isEqualTo("rest_created");
    }

    @Test
    @Order(2)
    public void getAutore() {
        ResponseEntity<?> resp = controller.findById(1);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        AutoriDTO autore = (AutoriDTO) resp.getBody();
        Assertions.assertThat(autore.getNome()).isEqualTo("nome");
        Assertions.assertThat(autore.getCognome()).isEqualTo("cognome");
    }

    @Test
    @Order(3)
    public void getAutoreError() {
        ResponseEntity<?> resp = controller.findById(99);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        Assertions.assertThat(resp.getBody()).isEqualTo("autori_ntfnd");
    }
}
