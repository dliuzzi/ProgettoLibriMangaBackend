package com.librimanga.backendlibrimanga.service;

import com.librimanga.backendlibrimanga.dto.inputs.LibriReq;
import com.librimanga.backendlibrimanga.dto.inputs.LoginReq;
import com.librimanga.backendlibrimanga.dto.inputs.UtentiReq;
import com.librimanga.backendlibrimanga.dto.outputs.LibriDTO;
import com.librimanga.backendlibrimanga.dto.outputs.LoginDTO;
import com.librimanga.backendlibrimanga.dto.outputs.UtentiDTO;
import com.librimanga.backendlibrimanga.exceptions.LibriException;
import com.librimanga.backendlibrimanga.model.*;
import com.librimanga.backendlibrimanga.repository.*;
import com.librimanga.backendlibrimanga.utilities.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.librimanga.backendlibrimanga.utilities.Mapper.*;

@RequiredArgsConstructor
@Slf4j
@Service
public class LibriService {
    private final LibriRepository libR;
    private final AutoriRepository autR;
    private final CategorieRepository catR;
    private final CaseEditriciRepository caseR;
    private final MessaggiService msgS;

    public void create(LibriReq req) throws LibriException {
        log.debug("create {}", req);

        Autori autore = autR.findById(req.getIdAutore())
                .orElseThrow(() -> new LibriException(msgS.get("autori_ntfnd")));

        HashSet<Categorie> categorie = new HashSet<>();
        for (Integer idCategoria : req.getIdCategorie()) {
            Categorie categoria = catR.findById(idCategoria)
                    .orElseThrow(() -> new LibriException(msgS.get("categorie_ntfnd")));
            categorie.add(categoria);
        }

        CaseEditrici casa = caseR.findById(req.getIdCasaEditrice())
                .orElseThrow(() -> new LibriException(msgS.get("case_ntfnd")));

        Libri l = new Libri();
        l.setTitolo(req.getTitolo());
        l.setDescrizione(req.getDescrizione());
        l.setCopertina(req.getCopertina());
        l.setPrezzo(req.getPrezzo());
        l.setDataPubblicazione(req.getDataPubblicazione());
        l.setQuantitaDisponibile(req.getQuantitaDisponibile());
        l.setAutore(autore);
        l.setCategorie(categorie);
        l.setCasaEditrice(casa);

        libR.save(l);
    }

    public List<LibriDTO> list() {
        log.debug("list");
        List<Libri> lL = libR.findAll();
        return lL.stream()
                .map(Mapper::buildLibriDTO)
                .toList();
    }

    public LibriDTO getById(Integer id) throws LibriException {
        log.debug("getById {}", id);
        Libri l = libR.findById(id)
                .orElseThrow(() -> new LibriException(msgS.get("libri_ntfnd")));

        return buildLibriDTO(l);
    }
}
