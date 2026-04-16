package com.librimanga.backendlibrimanga.controller;

import com.librimanga.backendlibrimanga.dto.inputs.LibriReq;
import com.librimanga.backendlibrimanga.enums.OrdineLibri;
import com.librimanga.backendlibrimanga.response.CreateResp;
import com.librimanga.backendlibrimanga.response.Resp;
import com.librimanga.backendlibrimanga.service.LibriService;
import com.librimanga.backendlibrimanga.service.MessaggiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("libri")
public class LibriController {
    private final LibriService libS;
    private final MessaggiService msgS;

    @PostMapping("/create")
    public ResponseEntity<CreateResp> create(@RequestBody LibriReq req) {
        CreateResp r = new CreateResp();
        HttpStatus status = HttpStatus.OK;
        try {
            Integer id = libS.create(req);
            r.setMsg(msgS.get("rest_created"));
            r.setId(id);
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Resp> delete(@RequestParam Integer id) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;
        try {
            libS.delete(id);
            r.setMsg(msgS.get("rest_deleted"));
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> list(
            @RequestParam(required = false) OrdineLibri sort,
            @RequestParam(required = false) Set<Integer> categorie,
            @RequestParam(required = false) Integer autore
            ) {
        Object r;
        HttpStatus status = HttpStatus.OK;
        try {
            r= libS.list(sort, categorie, autore);
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
            r= libS.getById(id);
        } catch (Exception e) {
            r=e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(r);
    }
}
