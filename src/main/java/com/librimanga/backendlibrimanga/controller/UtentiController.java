package com.librimanga.backendlibrimanga.controller;

import com.librimanga.backendlibrimanga.dto.inputs.LoginReq;
import com.librimanga.backendlibrimanga.dto.inputs.PreferitiReq;
import com.librimanga.backendlibrimanga.dto.inputs.UtentiReq;
import com.librimanga.backendlibrimanga.response.Resp;
import com.librimanga.backendlibrimanga.service.MessaggiService;
import com.librimanga.backendlibrimanga.service.UtentiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("utenti")
public class UtentiController {
    private final UtentiService utS;
    private final MessaggiService msgS;

    @PostMapping("/create")
    public ResponseEntity<Resp> create(@RequestBody UtentiReq req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            utS.create(req);
            r.setMsg(msgS.get("rest_created"));
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @PutMapping("/update")
    public ResponseEntity<Resp> update(@RequestBody UtentiReq req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            utS.update(req);
            r.setMsg(msgS.get("rest_updated"));
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> list() {
        Object r;
        HttpStatus status = HttpStatus.OK;
        try {
            r= utS.list();
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
            r= utS.getById(id);
        } catch (Exception e) {
            r=e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginReq req) {
        Object r;
        HttpStatus status = HttpStatus.OK;
        try {
            r= utS.login(req);
        } catch (Exception e) {
            r=new Resp();
            ((Resp)r).setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @PostMapping("/addFavourite")
    public ResponseEntity<Resp> addFavourite(@RequestBody PreferitiReq req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            utS.addFavourite(req);
            r.setMsg(msgS.get("rest_created"));
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @DeleteMapping("/deleteFavourite")
    public ResponseEntity<Resp> deleteFavourite(@RequestParam PreferitiReq req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            utS.deleteFavourite(req);
            r.setMsg(msgS.get("rest_deleted"));
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
}
