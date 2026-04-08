package com.librimanga.backendlibrimanga.controller;

import com.librimanga.backendlibrimanga.dto.inputs.LibriReq;
import com.librimanga.backendlibrimanga.dto.inputs.OrdiniReq;
import com.librimanga.backendlibrimanga.enums.OrdineLibri;
import com.librimanga.backendlibrimanga.response.Resp;
import com.librimanga.backendlibrimanga.service.LibriService;
import com.librimanga.backendlibrimanga.service.MessaggiService;
import com.librimanga.backendlibrimanga.service.OrdiniService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("ordini")
public class OrdiniController {
    private final OrdiniService ordS;
    private final MessaggiService msgS;

    @PostMapping("/create")
    public ResponseEntity<Resp> create(@RequestBody OrdiniReq req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            ordS.create(req);
            r.setMsg(msgS.get("rest_created"));
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
}
