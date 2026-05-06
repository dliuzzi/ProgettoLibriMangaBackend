package com.librimanga.backendlibrimanga.libri;

import com.librimanga.backendlibrimanga.BaseTest;
import com.librimanga.backendlibrimanga.controller.LibriController;
import com.librimanga.backendlibrimanga.dto.inputs.LibriReq;
import com.librimanga.backendlibrimanga.dto.outputs.LibriDTO;
import com.librimanga.backendlibrimanga.dto.outputs.PaginaDTO;
import com.librimanga.backendlibrimanga.response.CreateResp;
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

        LibriReq req2 = new LibriReq();
        req2.setTitolo("titolo2");
        req2.setDescrizione("descrizione2");
        req2.setCopertina("");
        req2.setPrezzo(10.00);
        req2.setDataPubblicazione(LocalDate.now());
        req2.setQuantitaDisponibile(5);
        req2.setIdAutore(1);
        req2.setIdCasaEditrice(1);
        req2.setIdCategorie(Collections.singleton(1));

        ResponseEntity<CreateResp> resp2 = controller.create(req);
        assertEquals(HttpStatus.OK, resp2.getStatusCode());
        CreateResp r2 = resp2.getBody();

        Assertions.assertThat(r2.getMsg()).isEqualTo("rest_created");
        Assertions.assertThat(r2.getId()).isEqualTo(2);
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

    @Test
    @Order(4)
    public void listLibro() {
        HashSet<Integer> categorie = new HashSet<>();
        categorie.add(1);

        ResponseEntity<Object> resp = controller.list(0, 10, "titolo", null, categorie, 1);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        PaginaDTO<LibriDTO> libri = (PaginaDTO<LibriDTO>) resp.getBody();
        Assertions.assertThat(libri.getContent().get(0).getDescrizione()).isEqualTo("descrizione");
    }

    @Test
    @Order(5)
    public void deleteLibro() {
        ResponseEntity<Resp> resp = controller.delete(2);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Resp r = resp.getBody();
        Assertions.assertThat(r.getMsg()).isEqualTo("rest_deleted");
    }
}
