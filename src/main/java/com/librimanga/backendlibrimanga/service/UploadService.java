package com.librimanga.backendlibrimanga.service;

import com.librimanga.backendlibrimanga.exceptions.LibriException;
import com.librimanga.backendlibrimanga.model.Libri;
import com.librimanga.backendlibrimanga.repository.LibriRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UploadService {

    private final Path uploadPath;
    private final MessaggiService msgS;
    private final LibriRepository libR;


    public UploadService(@Value("${app.upload.dir:uploads}") String uploadDir,  // valore per default della value
                      MessaggiService msgS, LibriRepository libR ) {
        this.uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize(); // transform relative path in absolute  path
        this.msgS = msgS;
        this.libR = libR;
        init();
    }

    private void init() {
        try {
            if (Files.notExists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
        } catch (IOException e) {
            throw new RuntimeException(msgS.get("upload_create"));
        }
    }

    @Transactional(rollbackFor = LibriException.class)
    public String saveImage(MultipartFile file, Integer id) {
        log.debug("saveImage {}", id);

        Assert.isTrue(!file.isEmpty(),() -> msgS.get("upload_empty")); // control file loaded

        String original = file.getOriginalFilename();
        String originalName = original.trim().replaceAll("\\s+", "_"); // normalize file name

        log.debug("originalName: {}" , originalName);

        String extension = Optional.of(originalName)         // search extension file
                .filter(name -> name.contains("."))
                .map(name -> name.substring(name.lastIndexOf(".")))
                .orElse("");

        // Build unique name
        String uniqueName =  originalName.substring(0, originalName.lastIndexOf(".")) + "-" +  UUID.randomUUID() + extension;

        Path destinationFile = uploadPath.resolve(uniqueName);

        try {
            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
            Libri l = libR.findById(id)
                    .orElseThrow(() -> new LibriException(msgS.get("libri_ntfnd")));
            l.setCopertina(uniqueName);

        } catch (IOException e) {
            throw new RuntimeException(msgS.get("upload_save_error"));
        }

        return uniqueName;
    }

    public String buildUrl(String filename) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()  // recupera la parte iniziale dell URL // localhost:8080/
                .path("/images/")    // il prefisse sarebbe image
                .path(filename)                 // il nome del file
                .toUriString();

    }

}
