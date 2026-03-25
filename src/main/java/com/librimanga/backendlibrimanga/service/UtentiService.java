package com.librimanga.backendlibrimanga.service;

import com.librimanga.backendlibrimanga.dto.inputs.LoginReq;
import com.librimanga.backendlibrimanga.dto.inputs.UtentiReq;
import com.librimanga.backendlibrimanga.dto.outputs.UtentiDTO;
import com.librimanga.backendlibrimanga.enums.Ruoli;
import com.librimanga.backendlibrimanga.exceptions.LibriException;
import com.librimanga.backendlibrimanga.model.Utenti;
import com.librimanga.backendlibrimanga.repository.UtentiRepository;
import com.librimanga.backendlibrimanga.utilities.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.librimanga.backendlibrimanga.utilities.Mapper.buildUtentiDTO;

@RequiredArgsConstructor
@Slf4j
@Service
public class UtentiService {
    private final UtentiRepository utR;
    private final MessaggiService msgS;

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

    public List<UtentiDTO> list() {
        log.debug("list");
        List<Utenti> lU = utR.findAll();
        return lU.stream()
                .map(Mapper::buildUtentiDTO)
                .toList();
    }

    public UtentiDTO getById(Integer id) throws LibriException {
        log.debug("getById {}", id);
        Utenti u = utR.findById(id)
                .orElseThrow(() -> new LibriException(msgS.get("utenti_ntfnd")));

        return buildUtentiDTO(u);
    }

    public UtentiDTO login(LoginReq req) throws LibriException {
        log.debug("login {}", req);
        Utenti ut = utR.findByEmail(req.getEmail())
                .orElseThrow(() -> new LibriException(msgS.get("login_invalid")));
        if (!ut.getPassword().equals(req.getPassword())) {
            throw new LibriException(msgS.get("login_invalid"));
        }

        return buildUtentiDTO(ut);
    }
}
