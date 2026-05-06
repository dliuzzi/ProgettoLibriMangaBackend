package com.librimanga.backendlibrimanga.controller;

import com.librimanga.backendlibrimanga.dto.inputs.RecensioniReq;
import com.librimanga.backendlibrimanga.response.Resp;
import com.librimanga.backendlibrimanga.service.MessaggiService;
import com.librimanga.backendlibrimanga.service.RecensioniService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("recensioni")
public class RecensioniController {
    private final RecensioniService recS;
    private final MessaggiService msgS;

    @PostMapping("/create")
    public ResponseEntity<Resp> create(@RequestBody RecensioniReq req) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            recS.create(req);
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
            r= recS.list();
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
            r= recS.getById(id);
        } catch (Exception e) {
            r=e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @GetMapping("/findByIdLibro")
    public ResponseEntity<Object> findByIdLibro(@RequestParam Integer idLibro){
        Object r;
        HttpStatus status = HttpStatus.OK;
        try {
            r= recS.getByLibro(idLibro);
        } catch (Exception e) {
            r=e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
}
