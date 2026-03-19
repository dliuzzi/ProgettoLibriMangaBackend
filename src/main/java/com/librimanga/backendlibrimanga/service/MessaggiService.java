package com.librimanga.backendlibrimanga.service;

import com.librimanga.backendlibrimanga.model.MessageID;
import com.librimanga.backendlibrimanga.model.Messaggi;
import com.librimanga.backendlibrimanga.repository.MessaggiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessaggiService {
    private final MessaggiRepository msgR;

    @Value("${lang}")
    private String lang;

    public String get(String code) {
        log.debug("get  {} ",code);
        String r = null;
        Optional<Messaggi> m = msgR.findById(new MessageID(lang, code));
        if (m.isEmpty())
            r = code;
        else
            r = m.get().getMessaggio();

        return r;
    }
}
