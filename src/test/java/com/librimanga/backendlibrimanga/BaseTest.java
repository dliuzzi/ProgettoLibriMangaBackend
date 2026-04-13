package com.librimanga.backendlibrimanga;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
public abstract class BaseTest {
    @MockitoBean
    private JavaMailSender javaMailSender;
}
