package com.librimanga.backendlibrimanga.service;

import com.librimanga.backendlibrimanga.dto.inputs.AutoriReq;
import com.librimanga.backendlibrimanga.dto.inputs.LoginReq;
import com.librimanga.backendlibrimanga.dto.inputs.UtentiReq;
import com.librimanga.backendlibrimanga.dto.outputs.AutoriDTO;
import com.librimanga.backendlibrimanga.dto.outputs.LoginDTO;
import com.librimanga.backendlibrimanga.dto.outputs.UtentiDTO;
import com.librimanga.backendlibrimanga.exceptions.LibriException;
import com.librimanga.backendlibrimanga.model.Autori;
import com.librimanga.backendlibrimanga.model.Utenti;
import com.librimanga.backendlibrimanga.repository.AutoriRepository;
import com.librimanga.backendlibrimanga.repository.UtentiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class AutoriService {
    private final AutoriRepository atR;
    private final MessaggiService msgS;

    public void create(AutoriReq req) throws LibriException {
        log.debug("create {}", req);

        Autori a = new Autori();
        a.setNome(req.getNome());
        a.setCognome(req.getCognome());

        atR.save(a);
    }

    public List<AutoriDTO> list() {
        log.debug("list");
        List<Autori> lA = atR.findAll();
        return lA.stream()
                .map((a -> AutoriDTO.builder()
                        .id(a.getId())
                        .nome(a.getNome())
                        .cognome(a.getCognome())
                        .build())
                ).toList();
    }

    public AutoriDTO getById(Integer id) throws LibriException {
        log.debug("getById {}", id);
        Autori a = atR.findById(id)
                .orElseThrow(() -> new LibriException(msgS.get("autori_ntfnd")));

        return AutoriDTO.builder()
                .id(a.getId())
                .nome(a.getNome())
                .cognome(a.getCognome())
                .build();
    }
}
