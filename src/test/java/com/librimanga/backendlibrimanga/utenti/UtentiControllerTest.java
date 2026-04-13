package com.librimanga.backendlibrimanga.utenti;

import com.librimanga.backendlibrimanga.BaseTest;
import com.librimanga.backendlibrimanga.controller.UtentiController;
import com.librimanga.backendlibrimanga.dto.inputs.LoginReq;
import com.librimanga.backendlibrimanga.dto.inputs.UtentiReq;
import com.librimanga.backendlibrimanga.dto.outputs.UtentiDTO;
import com.librimanga.backendlibrimanga.response.Resp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.assertj.core.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtentiControllerTest extends BaseTest {
    @Autowired
    private UtentiController controller;

    @Test
    @Order(1)
    public void getUtente() {
        log.debug("test getUtente");
        ResponseEntity<?> resp = controller.findById(1);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        UtentiDTO ut = (UtentiDTO)resp.getBody();
        Assertions.assertThat(ut.getEmail()).isEqualTo("pinco@outlook.com");
    }

    @Test
    @Order(2)
    public void getUtenteError() {
        log.debug("test getUtenteError");
        ResponseEntity<?> resp = controller.findById(999);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        Assertions.assertThat(resp.getBody()).isEqualTo("utenti_ntfnd");
    }

    @Test
    @Order(3)
    public void createUtente() {
        log.debug("test createUtente");

        UtentiReq req = new UtentiReq();
        req.setNome("pinco");
        req.setCognome("ponco");
        req.setEmail("pinco@yahoo.com");
        req.setPassword("miku64");
        req.setRuolo("USER");
        req.setVia("Via pinco");
        req.setProvincia("Pollo");
        req.setTelefono("1234567890");

        ResponseEntity<Resp> resp = controller.create(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = resp.getBody();

        Assertions.assertThat(r.getMsg()).isEqualTo("rest_created");
    }

    @Test
    @Order(4)
    public void createUtenteError() {
        log.debug("test createUtenteError");

        UtentiReq req = new UtentiReq();
        req.setNome("pinco");
        req.setCognome("ponco");
        req.setEmail("pinco@yahoo.com");
        req.setPassword("miku64");
        req.setRuolo("USER");
        req.setVia("Via pinco");
        req.setProvincia("Pollo");
        req.setTelefono("1234567890");

        ResponseEntity<Resp> resp = controller.create(req);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        Resp r = resp.getBody();

        Assertions.assertThat(r.getMsg()).isEqualTo("user_exist");
    }

    @Test
    @Order(5)
    public void updateUtente() {
        log.debug("test updateUtente");

        UtentiReq req = new UtentiReq();
        req.setId(2);
        req.setEmail("pinco@ponco.net");
        req.setPassword("miku128");
        req.setRuolo("ADMIN");

        ResponseEntity<Resp> resp = controller.update(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = resp.getBody();

        Assertions.assertThat(r.getMsg()).isEqualTo("rest_updated");
    }

    @Test
    @Order(6)
    public void loginTest() {
        log.debug("loginTest");

        LoginReq req = new LoginReq();
        req.setEmail("pinco@ponco.net");
        req.setPassword("miku128");

        ResponseEntity<Object> resp = controller.login(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());

        UtentiDTO utente = (UtentiDTO) resp.getBody();

        Assertions.assertThat(utente.getEmail()).isEqualTo("pinco@ponco.net");
    }
}
