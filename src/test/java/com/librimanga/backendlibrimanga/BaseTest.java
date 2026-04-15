package com.librimanga.backendlibrimanga;

import com.librimanga.backendlibrimanga.service.MailService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
public abstract class BaseTest {
    @MockitoBean
    private MailService mailService;
}
