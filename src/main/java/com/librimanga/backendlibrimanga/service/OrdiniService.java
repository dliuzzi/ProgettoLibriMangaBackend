package com.librimanga.backendlibrimanga.service;

import com.librimanga.backendlibrimanga.dto.inputs.DettagliOrdiniReq;
import com.librimanga.backendlibrimanga.dto.inputs.OrdiniReq;
import com.librimanga.backendlibrimanga.enums.StatoOrdine;
import com.librimanga.backendlibrimanga.exceptions.LibriException;
import com.librimanga.backendlibrimanga.model.*;
import com.librimanga.backendlibrimanga.repository.LibriRepository;
import com.librimanga.backendlibrimanga.repository.OrdiniRepository;
import com.librimanga.backendlibrimanga.repository.UtentiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrdiniService {
    private final OrdiniRepository ordR;
    private final UtentiRepository utR;
    private final LibriRepository libR;
    private final MessaggiService msgS;

    @Transactional(rollbackFor = LibriException.class)
    public void create(OrdiniReq req) throws LibriException {
        log.debug("create {}", req);

        Utenti utente = utR.findById(req.getIdUtente())
                .orElseThrow(() -> new LibriException(msgS.get("utenti_ntfnd")));

        Ordini o = new Ordini();
        o.setUtente(utente);

        HashSet<DettagliOrdini> dettagliOrdini = new HashSet<>();
        for (DettagliOrdiniReq dReq : req.getDettagliOrdini()) {
            Libri libro = libR.findById(dReq.getIdLibro())
                    .orElseThrow(() -> new LibriException(msgS.get("libri_ntfnd")));

            DettagliOrdini d = new DettagliOrdini();

            d.setLibro(libro);
            d.setQuantita(dReq.getQuantita());
            d.setStato(StatoOrdine.IN_CONSEGNA);
            d.setCostoTotale(libro.getPrezzo() * dReq.getQuantita());
            d.setData(LocalDate.now());

            d.setOrdine(o);

            dettagliOrdini.add(d);
        }

        o.setDettagliOrdini(dettagliOrdini);

        ordR.save(o);
    }
}
