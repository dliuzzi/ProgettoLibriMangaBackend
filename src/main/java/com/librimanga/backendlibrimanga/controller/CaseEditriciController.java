package com.librimanga.backendlibrimanga.controller;

import com.librimanga.backendlibrimanga.dto.inputs.CaseEditriciReq;
import com.librimanga.backendlibrimanga.dto.outputs.CaseEditriciDTO;
import com.librimanga.backendlibrimanga.response.Resp;
import com.librimanga.backendlibrimanga.service.CaseEditriciService;
import com.librimanga.backendlibrimanga.service.MessaggiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("case_editrici")
public class CaseEditriciController {

    private final CaseEditriciService caseS;
    private final MessaggiService msgS;

    @PostMapping("/create")
    public ResponseEntity<Resp> create(@RequestBody CaseEditriciReq req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            caseS.create(req);
            r.setMsg(msgS.get("rest_created"));
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    // 🔥 RESTITUISCE UN ARRAY PURO → PERFETTO PER ANGULAR
    @GetMapping("/list")
    public List<CaseEditriciDTO> list() {
        return caseS.list();
    }

    @GetMapping("/findById")
    public ResponseEntity<Object> findById(@RequestParam Integer id) {
        Object r;
        HttpStatus status = HttpStatus.OK;
        try {
            r = caseS.getById(id);
        } catch (Exception e) {
            r = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
}

