package com.librimanga.backendlibrimanga.categorie;

import com.librimanga.backendlibrimanga.BaseTest;
import com.librimanga.backendlibrimanga.controller.CategorieController;
import com.librimanga.backendlibrimanga.dto.inputs.CategorieReq;
import com.librimanga.backendlibrimanga.dto.outputs.CategorieDTO;
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
public class CategorieControllerTest extends BaseTest {
    @Autowired
    private CategorieController controller;

    @Test
    @Order(1)
    public void createCategoria() {
        CategorieReq req = new CategorieReq();
        req.setCategoria("categoria");

        ResponseEntity<Resp> resp = controller.create(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = resp.getBody();

        Assertions.assertThat(r.getMsg()).isEqualTo("rest_created");
    }

    @Test
    @Order(2)
    public void getCategoria() {
        ResponseEntity<?> resp = controller.findById(1);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        CategorieDTO categoria = (CategorieDTO) resp.getBody();
        Assertions.assertThat(categoria.getCategoria()).isEqualTo("categoria");
    }

    @Test
    @Order(3)
    public void getCategoriaError() {
        ResponseEntity<?> resp = controller.findById(99);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        Assertions.assertThat(resp.getBody()).isEqualTo("categorie_ntfnd");
    }
}
