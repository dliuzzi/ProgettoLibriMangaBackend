package com.librimanga.backendlibrimanga.service;

import com.librimanga.backendlibrimanga.dto.inputs.LibriReq;
import com.librimanga.backendlibrimanga.dto.outputs.LibriDTO;
import com.librimanga.backendlibrimanga.dto.outputs.PaginaDTO;
import com.librimanga.backendlibrimanga.enums.OrdineLibri;
import com.librimanga.backendlibrimanga.exceptions.LibriException;
import com.librimanga.backendlibrimanga.model.*;
import com.librimanga.backendlibrimanga.repository.*;
import com.librimanga.backendlibrimanga.utilities.Mapper;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = LibriException.class)
    public Integer create(LibriReq req) throws LibriException {
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

        l = libR.save(l);

        return l.getId();
    }

    @Transactional(rollbackFor = LibriException.class)
    public void delete(Integer id) throws LibriException {
        log.debug("delete {}", id);
        Libri libro = libR.findById(id)
                .orElseThrow(() -> new LibriException(msgS.get("libri_ntfnd")));

        libR.delete(libro);
    }

    @Transactional(readOnly = true)
    public PaginaDTO<LibriDTO> list(OrdineLibri sort, Set<Integer> categorie, Integer autore, Integer page, Integer limit) {
        log.debug("list");

        Sort dynamicSort = switch (sort) {
            case annoAsc -> Sort.by(Sort.Direction.ASC, "dataPubblicazione");
            case annoDesc -> Sort.by(Sort.Direction.DESC, "dataPubblicazione");
            case prezzoAsc -> Sort.by(Sort.Direction.ASC, "prezzo");
            case prezzoDesc -> Sort.by(Sort.Direction.DESC, "prezzo");
            case null, default -> Sort.by(Sort.Direction.ASC, "id");
        };

        Pageable pageRequest = PageRequest.of(page, limit, dynamicSort);

        Specification<Libri> spec = (_, _, cb) -> cb.conjunction();

        if (autore != null) {
            spec = spec.and(((root, _, cb) ->
                cb.equal(root.get("autore").get("id"), autore)
            ));
        }

        if (categorie != null && !categorie.isEmpty()) {
            spec = spec.and(((root, query, cb) -> {
                var predicates = categorie.stream().map(id -> {
                    Subquery<Integer> subquery = query.subquery(Integer.class);
                    Root<Libri> subRoot = subquery.from(Libri.class);
                    Join<Libri, Categorie> subJoin = subRoot.join("categorie");

                    subquery.select(subRoot.get("id"))
                            .where(cb.equal(subJoin.get("id"), id));

                    return root.get("id").in(subquery);
                }).toArray(jakarta.persistence.criteria.Predicate[]::new);

                return cb.and(predicates);
            }
            ));
        }

        Page<Libri> result = libR.findAll(spec, pageRequest);

        List<LibriDTO> libri = result.stream()
                .map(Mapper::buildLibriDTO)
                .toList();

        return PaginaDTO.<LibriDTO>builder()
                .content(libri)
                .page(result.getNumber())
                .size(result.getSize())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .first(result.isFirst())
                .last(result.isLast())
                .build();
    }

    @Transactional(readOnly = true)
    public LibriDTO getById(Integer id) throws LibriException {
        log.debug("getById {}", id);
        Libri l = libR.findById(id)
                .orElseThrow(() -> new LibriException(msgS.get("libri_ntfnd")));

        return buildLibriDTO(l);
    }
}
