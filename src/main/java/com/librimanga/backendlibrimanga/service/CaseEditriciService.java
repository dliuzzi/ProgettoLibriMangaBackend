package com.librimanga.backendlibrimanga.service;

import com.librimanga.backendlibrimanga.dto.inputs.AutoriReq;
import com.librimanga.backendlibrimanga.dto.inputs.CaseEditriciReq;
import com.librimanga.backendlibrimanga.dto.outputs.AutoriDTO;
import com.librimanga.backendlibrimanga.dto.outputs.CaseEditriciDTO;
import com.librimanga.backendlibrimanga.exceptions.LibriException;
import com.librimanga.backendlibrimanga.model.Autori;
import com.librimanga.backendlibrimanga.model.CaseEditrici;
import com.librimanga.backendlibrimanga.repository.AutoriRepository;
import com.librimanga.backendlibrimanga.repository.CaseEditriciRepository;
import com.librimanga.backendlibrimanga.utilities.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.librimanga.backendlibrimanga.utilities.Mapper.buildCaseEditriciDTO;

@RequiredArgsConstructor
@Slf4j
@Service
public class CaseEditriciService {
    private final CaseEditriciRepository caseR;
    private final MessaggiService msgS;

    @Transactional(rollbackFor = LibriException.class)
    public void create(CaseEditriciReq req) throws LibriException {
        log.debug("create {}", req);

        CaseEditrici c = new CaseEditrici();
        c.setNome(req.getNome());
        c.setSede(req.getSede());

        caseR.save(c);
    }

    public List<CaseEditriciDTO> list() {
        log.debug("list");
        List<CaseEditrici> lC = caseR.findAll();
        return lC.stream()
                .map(Mapper::buildCaseEditriciDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public CaseEditriciDTO getById(Integer id) throws LibriException {
        log.debug("getById {}", id);
        CaseEditrici c = caseR.findById(id)
                .orElseThrow(() -> new LibriException(msgS.get("case_ntfnd")));

        return buildCaseEditriciDTO(c);
    }
}
