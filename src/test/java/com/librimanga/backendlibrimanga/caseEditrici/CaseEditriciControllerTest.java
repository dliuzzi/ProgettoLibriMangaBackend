package com.librimanga.backendlibrimanga.caseEditrici;

import com.librimanga.backendlibrimanga.BaseTest;
import com.librimanga.backendlibrimanga.controller.CaseEditriciController;
import com.librimanga.backendlibrimanga.dto.inputs.CaseEditriciReq;
import com.librimanga.backendlibrimanga.dto.outputs.CaseEditriciDTO;
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
public class CaseEditriciControllerTest extends BaseTest {
    @Autowired
    private CaseEditriciController controller;

    @Test
    @Order(1)
    public void createCasaEditrice() {
        CaseEditriciReq req = new CaseEditriciReq();
        req.setNome("casa");

        ResponseEntity<Resp> resp = controller.create(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = resp.getBody();

        Assertions.assertThat(r.getMsg()).isEqualTo("rest_created");
    }

    @Test
    @Order(2)
    public void getCasaEditrice() {
        ResponseEntity<?> resp = controller.findById(1);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        CaseEditriciDTO casa = (CaseEditriciDTO) resp.getBody();
        Assertions.assertThat(casa.getNome()).isEqualTo("casa");
    }

    @Test
    @Order(3)
    public void getCasaEditriceError() {
        ResponseEntity<?> resp = controller.findById(99);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        Assertions.assertThat(resp.getBody()).isEqualTo("case_ntfnd");
    }
}
