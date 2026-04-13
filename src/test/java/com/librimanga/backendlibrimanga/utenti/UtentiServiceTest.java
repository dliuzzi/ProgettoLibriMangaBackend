package com.librimanga.backendlibrimanga.utenti;

import com.librimanga.backendlibrimanga.BaseTest;
import com.librimanga.backendlibrimanga.dto.inputs.UtentiReq;
import com.librimanga.backendlibrimanga.dto.outputs.UtentiDTO;
import com.librimanga.backendlibrimanga.exceptions.LibriException;
import com.librimanga.backendlibrimanga.service.UtentiService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtentiServiceTest extends BaseTest {
    @Autowired
    private UtentiService service;

    @Test
    @Order(1)
    public void createUtenteTest() {
        log.debug("create utente");
            UtentiReq req = new UtentiReq();
            req.setNome("pinco");
            req.setCognome("pallo");
            req.setEmail("pinco@outlook.com");
            req.setPassword("miku2");
            req.setRuolo("ADMIN");
            req.setVia("Via pinco");
            req.setProvincia("Pallo");
            req.setTelefono("1234567890");

            service.create(req);

            List<UtentiDTO> list = service.list();
            UtentiDTO utente = list.stream()
                    .filter(u -> u.getEmail().equals("pinco@outlook.com"))
                    .findFirst()
                    .orElseThrow(() -> new LibriException("Utente non trovato"));

            Assertions.assertThat(utente.getCognome()).isEqualTo("pallo");
    }

    @Test
    @Order(2)
    public void createUtenteErrorTest() {
        log.debug("create utente in error");

        UtentiReq req = new UtentiReq();
        req.setNome("pinco");
        req.setCognome("pallo");
        req.setEmail("pinco@outlook.com");
        req.setPassword("miku2");
        req.setRuolo("ADMIN");
        req.setVia("Via pinco");
        req.setProvincia("Pallo");
        req.setTelefono("1234567890");

        assertThrows(Exception.class, () -> {
            service.create(req);
        });
    }
}
