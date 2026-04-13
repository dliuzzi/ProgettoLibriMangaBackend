package com.librimanga.backendlibrimanga.ordini;

import com.librimanga.backendlibrimanga.BaseTest;
import com.librimanga.backendlibrimanga.controller.OrdiniController;
import com.librimanga.backendlibrimanga.dto.inputs.DettagliOrdiniReq;
import com.librimanga.backendlibrimanga.dto.inputs.OrdiniReq;
import com.librimanga.backendlibrimanga.dto.outputs.OrdiniDTO;
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

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrdiniControllerTest extends BaseTest {
    @Autowired
    private OrdiniController controller;

    @Test
    @Order(1)
    public void createOrdine() {
        OrdiniReq req = new OrdiniReq();
        req.setIdUtente(1);

        DettagliOrdiniReq dReq = new DettagliOrdiniReq();
        dReq.setIdLibro(1);
        dReq.setQuantita(10);

        req.setDettagliOrdini(Collections.singleton(dReq));

        ResponseEntity<Resp> resp = controller.create(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = resp.getBody();

        Assertions.assertThat(r.getMsg()).isEqualTo("rest_created");
    }

    @Test
    @Order(2)
    public void getOrdine() {
        ResponseEntity<?> resp = controller.findByUtente(1);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        List<OrdiniDTO> ordini = (List<OrdiniDTO>) resp.getBody();
        Assertions.assertThat(ordini.get(0).getDettagliOrdine().get(0).getLibro().getTitolo()).isEqualTo("titolo");
    }

    @Test
    @Order(3)
    public void getOrdineError() {
        ResponseEntity<?> resp = controller.findByUtente(99);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        Assertions.assertThat(resp.getBody()).isEqualTo("utenti_ntfnd");
    }
}
