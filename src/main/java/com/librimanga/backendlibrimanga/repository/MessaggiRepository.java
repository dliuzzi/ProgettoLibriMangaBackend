package com.librimanga.backendlibrimanga.repository;

import com.librimanga.backendlibrimanga.model.MessageID;
import com.librimanga.backendlibrimanga.model.Messaggi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessaggiRepository extends JpaRepository<Messaggi, MessageID> {
}
