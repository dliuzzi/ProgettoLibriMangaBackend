package com.librimanga.backendlibrimanga.service;

import com.librimanga.backendlibrimanga.dto.inputs.CaseEditriciReq;
import com.librimanga.backendlibrimanga.dto.inputs.CategorieReq;
import com.librimanga.backendlibrimanga.dto.outputs.CaseEditriciDTO;
import com.librimanga.backendlibrimanga.dto.outputs.CategorieDTO;
import com.librimanga.backendlibrimanga.exceptions.LibriException;
import com.librimanga.backendlibrimanga.model.CaseEditrici;
import com.librimanga.backendlibrimanga.model.Categorie;
import com.librimanga.backendlibrimanga.repository.CaseEditriciRepository;
import com.librimanga.backendlibrimanga.repository.CategorieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class CategorieService {
    private final CategorieRepository catR;
    private final MessaggiService msgS;

    public void create(CategorieReq req) throws LibriException {
        log.debug("create {}", req);

        Categorie c = new Categorie();
        c.setCategoria(req.getCategoria());

        catR.save(c);
    }

    public List<CategorieDTO> list() {
        log.debug("list");
        List<Categorie> lC = catR.findAll();
        return lC.stream()
                .map((c -> CategorieDTO.builder()
                        .id(c.getId())
                        .categoria(c.getCategoria())
                        .build())
                ).toList();
    }

    public CategorieDTO getById(Integer id) throws LibriException {
        log.debug("getById {}", id);
        Categorie c = catR.findById(id)
                .orElseThrow(() -> new LibriException(msgS.get("categorie_ntfnd")));

        return CategorieDTO.builder()
                .id(c.getId())
                .categoria(c.getCategoria())
                .build();
    }
}
