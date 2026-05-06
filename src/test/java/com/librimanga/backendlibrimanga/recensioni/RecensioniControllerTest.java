package com.librimanga.backendlibrimanga.recensioni;

import com.librimanga.backendlibrimanga.BaseTest;
import com.librimanga.backendlibrimanga.controller.RecensioniController;
import com.librimanga.backendlibrimanga.dto.inputs.RecensioniReq;
import com.librimanga.backendlibrimanga.dto.outputs.RecensioniDTO;
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
public class RecensioniControllerTest extends BaseTest {
    @Autowired
    private RecensioniController controller;

    @Test
    @Order(1)
    public void createRecensione() {
        RecensioniReq req = new RecensioniReq();
        req.setStelle(5);
        req.setContenuto("contenuto");
        req.setIdLibro(1);
        req.setIdUtente(2);

        ResponseEntity<Resp> resp = controller.create(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = resp.getBody();

        Assertions.assertThat(r.getMsg()).isEqualTo("rest_created");
    }

    @Test
    @Order(2)
    public void createRecensioneError() {
        RecensioniReq req = new RecensioniReq();
        req.setStelle(6);
        req.setContenuto("contenuto");
        req.setIdLibro(1);
        req.setIdUtente(2);

        ResponseEntity<Resp> resp = controller.create(req);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        Assertions.assertThat(resp.getBody().getMsg()).isEqualTo("stelle_invalide");
    }

    @Test
    @Order(3)
    public void getRecensione() {
        ResponseEntity<Object> resp = controller.findById(1);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        RecensioniDTO rec = (RecensioniDTO) resp.getBody();
        Assertions.assertThat(rec.getStelle()).isEqualTo(5);
        Assertions.assertThat(rec.getContenuto()).isEqualTo("contenuto");
    }

    @Test
    @Order(4)
    public void getRecensioneError() {
        ResponseEntity<?> resp = controller.findById(99);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        Assertions.assertThat(resp.getBody()).isEqualTo("recensioni_ntfnd");
    }
}
