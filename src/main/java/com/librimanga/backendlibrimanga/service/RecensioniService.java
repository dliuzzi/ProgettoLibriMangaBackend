package com.librimanga.backendlibrimanga.service;

import com.librimanga.backendlibrimanga.dto.inputs.RecensioniReq;
import com.librimanga.backendlibrimanga.dto.outputs.RecensioniDTO;
import com.librimanga.backendlibrimanga.exceptions.LibriException;
import com.librimanga.backendlibrimanga.model.Libri;
import com.librimanga.backendlibrimanga.model.Recensioni;
import com.librimanga.backendlibrimanga.model.Utenti;
import com.librimanga.backendlibrimanga.repository.LibriRepository;
import com.librimanga.backendlibrimanga.repository.RecensioniRepository;
import com.librimanga.backendlibrimanga.repository.UtentiRepository;
import com.librimanga.backendlibrimanga.utilities.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.librimanga.backendlibrimanga.utilities.Mapper.buildRecensioniDTO;

@RequiredArgsConstructor
@Slf4j
@Service
public class RecensioniService {
    private final RecensioniRepository recR;
    private final UtentiRepository utR;
    private final LibriRepository libR;
    private final MessaggiService msgS;

    @Transactional(rollbackFor = LibriException.class)
    public void create(RecensioniReq req) throws LibriException {
        log.debug("create {}", req);

        Utenti utente = utR.findById(req.getIdUtente())
                .orElseThrow(() -> new LibriException(msgS.get("utenti_ntfnd")));
        Libri libro = libR.findById(req.getIdLibro())
                .orElseThrow(() -> new LibriException(msgS.get("libri_ntfnd")));

        Recensioni r = new Recensioni();
        if (req.getStelle() < 0 || req.getStelle() > 5) {
            throw new LibriException(msgS.get("stelle_invalide"));
        }
        r.setStelle(req.getStelle());
        r.setContenuto(req.getContenuto());
        r.setLibro(libro);
        r.setUtente(utente);
        r.setData(LocalDate.now());

        recR.save(r);
    }

    @Transactional(readOnly = true)
    public List<RecensioniDTO> list() {
        log.debug("list");
        List<Recensioni> lA = recR.findAll();
        return lA.stream()
                .map(Mapper::buildRecensioniDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<RecensioniDTO> getByLibro(Integer idLibro) {
        log.debug("getByLibro {}", idLibro);

        Libri libro = libR.findById(idLibro)
                .orElseThrow(() -> new LibriException(msgS.get("libri_ntfnd")));

        List<Recensioni> lA = recR.findByLibro(libro);
        return lA.stream()
                .map(Mapper::buildRecensioniDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public RecensioniDTO getById(Integer id) throws LibriException {
        log.debug("getById {}", id);
        Recensioni r = recR.findById(id)
                .orElseThrow(() -> new LibriException(msgS.get("recensioni_ntfnd")));

        return buildRecensioniDTO(r);
    }
}
