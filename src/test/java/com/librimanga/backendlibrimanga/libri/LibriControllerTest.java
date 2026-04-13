package com.librimanga.backendlibrimanga.libri;

import com.librimanga.backendlibrimanga.BaseTest;
import com.librimanga.backendlibrimanga.controller.LibriController;
import com.librimanga.backendlibrimanga.dto.inputs.LibriReq;
import com.librimanga.backendlibrimanga.dto.outputs.LibriDTO;
import com.librimanga.backendlibrimanga.response.CreateResp;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LibriControllerTest extends BaseTest {
    @Autowired
    private LibriController controller;

    @Test
    @Order(1)
    public void createLibro() {
        LibriReq req = new LibriReq();
        req.setTitolo("titolo");
        req.setDescrizione("descrizione");
        req.setCopertina("");
        req.setPrezzo(5.00);
        req.setDataPubblicazione(LocalDate.now());
        req.setQuantitaDisponibile(10);
        req.setIdAutore(1);
        req.setIdCasaEditrice(1);
        req.setIdCategorie(Collections.singleton(1));

        ResponseEntity<CreateResp> resp = controller.create(req);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        CreateResp r = resp.getBody();

        Assertions.assertThat(r.getMsg()).isEqualTo("rest_created");
        Assertions.assertThat(r.getId()).isEqualTo(1);
    }

    @Test
    @Order(2)
    public void getLibro() {
        ResponseEntity<?> resp = controller.findById(1);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        LibriDTO libro = (LibriDTO) resp.getBody();
        Assertions.assertThat(libro.getTitolo()).isEqualTo("titolo");
        Assertions.assertThat(libro.getDescrizione()).isEqualTo("descrizione");
    }

    @Test
    @Order(3)
    public void getLibroError() {
        ResponseEntity<?> resp = controller.findById(99);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        Assertions.assertThat(resp.getBody()).isEqualTo("libri_ntfnd");
    }
}
