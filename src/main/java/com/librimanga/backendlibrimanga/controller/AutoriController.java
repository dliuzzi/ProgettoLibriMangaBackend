package com.librimanga.backendlibrimanga.controller;

import com.librimanga.backendlibrimanga.dto.inputs.AutoriReq;
import com.librimanga.backendlibrimanga.dto.inputs.LoginReq;
import com.librimanga.backendlibrimanga.dto.inputs.UtentiReq;
import com.librimanga.backendlibrimanga.model.Autori;
import com.librimanga.backendlibrimanga.response.Resp;
import com.librimanga.backendlibrimanga.service.AutoriService;
import com.librimanga.backendlibrimanga.service.MessaggiService;
import com.librimanga.backendlibrimanga.service.UtentiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("autori")
public class AutoriController {
    private final AutoriService atS;
    private final MessaggiService msgS;

    @PostMapping("/create")
    public ResponseEntity<Resp> create(@RequestBody AutoriReq req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            atS.create(req);
            r.setMsg(msgS.get("rest_created"));
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> list() {
        Object r;
        HttpStatus status = HttpStatus.OK;
        try {
            r= atS.list();
        } catch (Exception e) {
            r=e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @GetMapping("/findById")
    public ResponseEntity<Object> findById (@RequestParam Integer id){
        Object r;
        HttpStatus status = HttpStatus.OK;
        try {
            r= atS.getById(id);
        } catch (Exception e) {
            r=e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
}
