package com.librimanga.backendlibrimanga.service;

import com.librimanga.backendlibrimanga.dto.MailReq;
import com.librimanga.backendlibrimanga.dto.inputs.LoginReq;
import com.librimanga.backendlibrimanga.dto.inputs.PreferitiReq;
import com.librimanga.backendlibrimanga.dto.inputs.UtentiReq;
import com.librimanga.backendlibrimanga.dto.outputs.UtentiDTO;
import com.librimanga.backendlibrimanga.enums.Ruoli;
import com.librimanga.backendlibrimanga.exceptions.LibriException;
import com.librimanga.backendlibrimanga.model.Libri;
import com.librimanga.backendlibrimanga.model.Utenti;
import com.librimanga.backendlibrimanga.repository.LibriRepository;
import com.librimanga.backendlibrimanga.repository.UtentiRepository;
import com.librimanga.backendlibrimanga.utilities.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.librimanga.backendlibrimanga.utilities.Mapper.buildUtentiDTO;

@RequiredArgsConstructor
@Slf4j
@Service
public class UtentiService {
    private final UtentiRepository utR;
    private final LibriRepository libR;
    private final MessaggiService msgS;
    private final MailService mailS;

    @Transactional(rollbackFor = LibriException.class)
    public void create(UtentiReq req) throws LibriException {
        log.debug("create {}", req);

        if (utR.existsByEmail(req.getEmail())) {
            throw new LibriException(msgS.get("user_exist"));
        }

        Utenti u = new Utenti();
        u.setNome(req.getNome());
        u.setCognome(req.getCognome());
        u.setEmail(req.getEmail());
        u.setPassword(req.getPassword());
        u.setRuolo(Ruoli.valueOf(req.getRuolo()));
        u.setVia(req.getVia());
        u.setProvincia(req.getProvincia());
        u.setTelefono(req.getTelefono());

        utR.save(u);
    }

    @Transactional(rollbackFor = LibriException.class)
    public void update(UtentiReq req) throws LibriException {
        log.debug("update {}", req);

        Utenti u = utR.findById(req.getId())
                .orElseThrow(() -> new LibriException(msgS.get("utenti_ntfnd")));

        if (req.getNome() != null)
            u.setNome(req.getNome());
        if (req.getCognome() != null)
            u.setCognome(req.getCognome());
        if (req.getEmail() != null)
            u.setEmail(req.getEmail());
        if (req.getPassword() != null)
            u.setPassword(req.getPassword());
        if (req.getRuolo() != null)
            u.setRuolo(Ruoli.valueOf(req.getRuolo()));
        if (req.getVia() != null)
            u.setVia(req.getVia());
        if (req.getProvincia() != null)
            u.setProvincia(req.getProvincia());
        if (req.getTelefono() != null)
            u.setTelefono(req.getTelefono());

        utR.save(u);
    }

    @Transactional(readOnly = true)
    public List<UtentiDTO> list() {
        log.debug("list");
        List<Utenti> lU = utR.findAll();
        return lU.stream()
                .map(Mapper::buildUtentiDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public UtentiDTO getById(Integer id) throws LibriException {
        log.debug("getById {}", id);
        Utenti u = utR.findById(id)
                .orElseThrow(() -> new LibriException(msgS.get("utenti_ntfnd")));

        return buildUtentiDTO(u);
    }

    @Transactional(readOnly = true)
    public UtentiDTO login(LoginReq req) throws LibriException {
        log.debug("login {}", req);
        Utenti ut = utR.findByEmail(req.getEmail())
                .orElseThrow(() -> new LibriException(msgS.get("login_invalid")));
        if (!ut.getPassword().equals(req.getPassword())) {
            throw new LibriException(msgS.get("login_invalid"));
        }

        return buildUtentiDTO(ut);
    }

    @Transactional(rollbackFor = LibriException.class)
    public void addFavourite(PreferitiReq req) throws LibriException {
        log.debug("addFavourite {} {}", req);

        Utenti ut = utR.findById(req.getIdUtente())
                .orElseThrow(() -> new LibriException(msgS.get("utente_ntfnd")));

        Libri l = libR.findById(req.getIdLibro())
                .orElseThrow(() -> new LibriException(msgS.get("libri_ntfnd")));

        Set<Libri> preferiti = ut.getLibriPreferiti();
        preferiti.add(l);

        utR.save(ut);
    }

    @Transactional(rollbackFor = LibriException.class)
    public void deleteFavourite(Integer idUtente, Integer idLibro) throws LibriException {
        log.debug("deleteFavourite {} {}", idUtente, idLibro);

        Utenti ut = utR.findById(idUtente)
                .orElseThrow(() -> new LibriException(msgS.get("utente_ntfnd")));

        Libri l = libR.findById(idLibro)
                .orElseThrow(() -> new LibriException(msgS.get("libri_ntfnd")));

        Set<Libri> preferiti = ut.getLibriPreferiti();
        preferiti.remove(l);

        utR.save(ut);
    }

    @Value("${mail.passwordResetUrl}")
    private String passwordResetUrl;

    public void sendPasswordResetMail(String email) throws Exception {
        Utenti utente = utR.findByEmail(email)
                .orElseThrow(() -> new LibriException(msgS.get("utenti_ntfnd")));

        StringBuilder body = new StringBuilder();
        body.append("Buongiorno %s<br>".formatted(utente.getNome()));
        body.append("Clicca il link per resettare la password<br>");
        body.append("<a>%s<a>".formatted(passwordResetUrl + utente.getId()));

        sendMail(utente, "Password dimenticata", body.toString());
    }

    private void sendMail(Utenti utente, String oggetto, String body) throws Exception{
        mailS.sendMail(MailReq.builder()
                .to(utente.getEmail())
                .oggetto(oggetto)
                .body(body)
                .build()
        );
    }
}
