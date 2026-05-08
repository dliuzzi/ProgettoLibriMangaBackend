package com.librimanga.backendlibrimanga.carrelli;

import com.librimanga.backendlibrimanga.BaseTest;
import com.librimanga.backendlibrimanga.controller.CarrelloController;
import com.librimanga.backendlibrimanga.dto.inputs.DettagliCarrelliReq;
import com.librimanga.backendlibrimanga.dto.outputs.DettagliCarrelliDTO;
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

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarrelliControllerTest extends BaseTest {
    @Autowired
    private CarrelloController controller;

    @Test
    @Order(1)
    public void create() {
        DettagliCarrelliReq req = new DettagliCarrelliReq();
        req.setIdUtente(2);
        req.setIdLibro(1);
        req.setQuantita(2);

        ResponseEntity<Resp> resp = controller.create(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = resp.getBody();

        Assertions.assertThat(r.getMsg()).isEqualTo("rest_created");
    }

    @Test
    @Order(2)
    public void update() {
        DettagliCarrelliReq req = new DettagliCarrelliReq();
        req.setId(1);
        req.setQuantita(3);

        ResponseEntity<Resp> resp = controller.update(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = resp.getBody();

        Assertions.assertThat(r.getMsg()).isEqualTo("rest_updated");
    }

    @Test
    @Order(3)
    public void getById() {
        ResponseEntity<?> resp = controller.findById(1);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        DettagliCarrelliDTO c = (DettagliCarrelliDTO) resp.getBody();
        Assertions.assertThat(c.getLibro().getId()).isEqualTo(1);
        Assertions.assertThat(c.getQuantita()).isEqualTo(3);
    }

    @Test
    @Order(4)
    public void getByUtente() {
        ResponseEntity<?> resp = controller.findByUtente(2);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        List<DettagliCarrelliDTO> lC = (List<DettagliCarrelliDTO>) resp.getBody();
        Assertions.assertThat(lC.get(0).getLibro().getId()).isEqualTo(1);
        Assertions.assertThat(lC.get(0).getQuantita()).isEqualTo(3);
    }

    @Test
    @Order(5)
    public void acquista() {
        ResponseEntity<Resp> resp = controller.acquista(2);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = resp.getBody();
        Assertions.assertThat(r.getMsg()).isEqualTo("rest_created");
    }
}
