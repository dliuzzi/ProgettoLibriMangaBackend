package com.librimanga.backendlibrimanga.controller;

import com.librimanga.backendlibrimanga.dto.inputs.OrdiniReq;
import com.librimanga.backendlibrimanga.dto.inputs.UpdateStatoReq;
import com.librimanga.backendlibrimanga.response.Resp;
import com.librimanga.backendlibrimanga.service.MessaggiService;
import com.librimanga.backendlibrimanga.service.OrdiniService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/list")
    public ResponseEntity<Object> list() {
        Object r;
        HttpStatus status = HttpStatus.OK;
        try {
            r= ordS.list();
        } catch (Exception e) {
            r=e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Resp> delete(@RequestParam Integer id) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            ordS.delete(id);
            r.setMsg(msgS.get("rest_deleted"));
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<Resp> updateStatus(
            @RequestBody UpdateStatoReq req
            ) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            ordS.updateStatus(req);
            r.setMsg(msgS.get("rest_updated"));
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @GetMapping("/findByUtente")
    public ResponseEntity<Object> findByUtente(@RequestParam Integer idUtente) {
        Object r;
        HttpStatus status = HttpStatus.OK;
        try {
            r= ordS.findByUtente(idUtente);
        } catch (Exception e) {
            r=e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
}
