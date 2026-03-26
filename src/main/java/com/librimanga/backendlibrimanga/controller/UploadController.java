package com.librimanga.backendlibrimanga.controller;

import com.librimanga.backendlibrimanga.response.Resp;
import com.librimanga.backendlibrimanga.service.MessaggiService;
import com.librimanga.backendlibrimanga.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("rest/upload")
public class UploadController {

    private final UploadService uplS;
    private final MessaggiService msgS;

    @PostMapping(value = "/image", consumes = "multipart/form-data")
    public ResponseEntity<Resp> uploadImage(
            @RequestParam MultipartFile file,
            @RequestParam Integer id) {
        Resp r = new Resp();
        try {
			 /*
			   Test del content type:
						PNG	image/png
						JPG	image/jpeg
						GIF	image/gif
			  */
            if (file.getContentType() == null || !file.getContentType().startsWith("image/")) {
                r.setMsg(msgS.get("upload_invalid"));
                return ResponseEntity.badRequest().body(r);
            }

            r.setMsg(uplS.saveImage(file, id));

            return ResponseEntity.status(HttpStatus.CREATED).body(r);

        } catch (Exception e) {
            r.setMsg(e.getMessage());
            return ResponseEntity.internalServerError().body(r);
        }
    }

    @GetMapping("getUrl")
    public ResponseEntity<Resp> getUrl(@RequestParam String filename) {
        Resp r = new Resp();
        HttpStatus status = HttpStatus.OK;

        r.setMsg(uplS.buildUrl(filename));
        return ResponseEntity.status(status).body(r);
    }
}
