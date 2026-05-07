package com.librimanga.backendlibrimanga.controller;

import com.librimanga.backendlibrimanga.dto.inputs.DettagliCarrelliReq;
import com.librimanga.backendlibrimanga.response.Resp;
import com.librimanga.backendlibrimanga.service.CarrelliService;
import com.librimanga.backendlibrimanga.service.MessaggiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("carrello")
public class CarrelloController {
    private final CarrelliService carS;
    private final MessaggiService msgS;

    @PostMapping("/create")
    public ResponseEntity<Resp> create(@RequestBody DettagliCarrelliReq req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            carS.create(req);
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
            r= carS.list();
        } catch (Exception e) {
            r=e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @PutMapping("/update")
    public ResponseEntity<Resp> update(@RequestBody DettagliCarrelliReq req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            carS.update(req);
            r.setMsg(msgS.get("rest_updated"));
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Resp> delete(@RequestParam Integer id) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            carS.delete(id);
            r.setMsg(msgS.get("rest_deleted"));
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @PostMapping("/acquista")
    public ResponseEntity<Resp> acquista(@RequestParam Integer idUtente) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            carS.acquista(idUtente);
            r.setMsg(msgS.get("rest_created"));
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @GetMapping("/findById")
    public ResponseEntity<Object> findById (@RequestParam Integer id){
        Object r;
        HttpStatus status = HttpStatus.OK;
        try {
            r= carS.getById(id);
        } catch (Exception e) {
            r=e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @GetMapping("/findByUtente")
    public ResponseEntity<Object> findByUtente (@RequestParam Integer idUtente){
        Object r;
        HttpStatus status = HttpStatus.OK;
        try {
            r= carS.getByUtente(idUtente);
        } catch (Exception e) {
            r=e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
}
