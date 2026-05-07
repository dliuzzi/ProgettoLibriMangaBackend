package com.librimanga.backendlibrimanga.service;

import com.librimanga.backendlibrimanga.dto.inputs.DettagliCarrelliReq;
import com.librimanga.backendlibrimanga.dto.outputs.DettagliCarrelliDTO;
import com.librimanga.backendlibrimanga.enums.StatoOrdine;
import com.librimanga.backendlibrimanga.exceptions.LibriException;
import com.librimanga.backendlibrimanga.model.*;
import com.librimanga.backendlibrimanga.repository.*;
import com.librimanga.backendlibrimanga.utilities.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static com.librimanga.backendlibrimanga.utilities.Mapper.buildDettagliCarrelliDTO;

@RequiredArgsConstructor
@Slf4j
@Service
public class CarrelliService {
    private final DettagliCarrelliRepository carR;
    private final UtentiRepository utR;
    private final LibriRepository libR;
    private final OrdiniRepository ordR;
    private final MessaggiService msgS;

    @Transactional(rollbackFor = LibriException.class)
    public void create(DettagliCarrelliReq req) throws LibriException {
        log.debug("create {}", req);

        Utenti utente = utR.findById(req.getIdUtente())
                .orElseThrow(() -> new LibriException(msgS.get("utenti_ntfnd")));
        Libri libro = libR.findById(req.getIdLibro())
                .orElseThrow(() -> new LibriException(msgS.get("libri_ntfnd")));

        DettagliCarrelli c = new DettagliCarrelli();
        c.setUtente(utente);
        c.setLibro(libro);
        c.setQuantita(req.getQuantita());

        carR.save(c);
    }

    @Transactional(rollbackFor = LibriException.class)
    public void update(DettagliCarrelliReq req) throws LibriException {
        log.debug("update {}", req);

        DettagliCarrelli c = carR.findById(req.getId())
                .orElseThrow(() -> new LibriException(msgS.get("carrelli_ntfnd")));

        if (req.getQuantita() != null) {
            c.setQuantita(req.getQuantita());
        }

        carR.save(c);
    }

    @Transactional(rollbackFor = LibriException.class)
    public void delete(Integer idCarrello) throws LibriException {
        log.debug("delete {}", idCarrello);

        DettagliCarrelli c = carR.findById(idCarrello)
                .orElseThrow(() -> new LibriException(msgS.get("carrelli_ntfnd")));

        carR.delete(c);
    }

    @Transactional(rollbackFor = LibriException.class)
    public void acquista(Integer idUtente) throws LibriException {
        log.debug("acquista {}", idUtente);

        Utenti utente = utR.findById(idUtente)
                .orElseThrow(() -> new LibriException(msgS.get("utenti_ntfnd")));

        Ordini o = new Ordini();
        o.setUtente(utente);

        HashSet<DettagliOrdini> dettagliOrdini = new HashSet<>();
        for (DettagliCarrelli dC : carR.findByUtente(utente)) {
            DettagliOrdini d = new DettagliOrdini();

            d.setLibro(dC.getLibro());
            d.setQuantita(dC.getQuantita());
            d.setStato(StatoOrdine.IN_CONSEGNA);
            d.setCostoTotale(dC.getLibro().getPrezzo() * dC.getQuantita());
            d.setData(LocalDate.now());

            d.setOrdine(o);

            dettagliOrdini.add(d);
            carR.delete(dC);
        }

        o.setDettagliOrdini(dettagliOrdini);

        ordR.save(o);
    }

    @Transactional(readOnly = true)
    public List<DettagliCarrelliDTO> list() {
        log.debug("list");
        List<DettagliCarrelli> lC = carR.findAll();
        return lC.stream()
                .map(Mapper::buildDettagliCarrelliDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<DettagliCarrelliDTO> getByUtente(Integer idUtente) {
        log.debug("getByUtente {}", idUtente);

        Utenti utente = utR.findById(idUtente)
                .orElseThrow(() -> new LibriException(msgS.get("utenti_ntfnd")));

        List<DettagliCarrelli> lC = carR.findByUtente(utente);
        return lC.stream()
                .map(Mapper::buildDettagliCarrelliDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public DettagliCarrelliDTO getById(Integer id) throws LibriException {
        log.debug("getById {}", id);
        DettagliCarrelli c = carR.findById(id)
                .orElseThrow(() -> new LibriException(msgS.get("carrelli_ntfnd")));

        return buildDettagliCarrelliDTO(c);
    }
}
