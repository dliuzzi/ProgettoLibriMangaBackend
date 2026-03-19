package com.librimanga.backendlibrimanga.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="messaggi_sistema")
public class Messaggi {

    @EmbeddedId
    private MessageID msgID;

    private String messaggio;
}

