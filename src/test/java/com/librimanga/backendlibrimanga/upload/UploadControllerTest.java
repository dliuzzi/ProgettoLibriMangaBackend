package com.librimanga.backendlibrimanga.upload;

import com.librimanga.backendlibrimanga.BaseTest;
import com.librimanga.backendlibrimanga.controller.LibriController;
import com.librimanga.backendlibrimanga.controller.UploadController;
import com.librimanga.backendlibrimanga.dto.outputs.LibriDTO;
import com.librimanga.backendlibrimanga.response.Resp;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UploadControllerTest extends BaseTest {
    @Autowired
    private UploadController controller;

    @Autowired
    private LibriController libriC;

    @Test
    @Order(1)
    public void uploadTest() {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "test.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "Hello, World!".getBytes()
        );

        ResponseEntity<Resp> resp = controller.uploadImage(file, 1);
        assertEquals(HttpStatus.CREATED, resp.getStatusCode());

        ResponseEntity<?> respL = libriC.findById(1);
        assertEquals(HttpStatus.OK, respL.getStatusCode());
        LibriDTO libro = (LibriDTO) respL.getBody();

        Assertions.assertThat(libro.getCopertina().substring(0, 5)).isEqualTo("test-");
        Assertions.assertThat(libro.getCopertina().substring(libro.getCopertina().length() - 4)).isEqualTo(".jpg");
    }

    @Test
    @Order(2)
    public void uploadErrorTest() {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "test.jpg",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        ResponseEntity<Resp> resp = controller.uploadImage(file, 1);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
        Resp r = resp.getBody();
        Assertions.assertThat(r.getMsg()).isEqualTo("upload_invalid");
    }
}
