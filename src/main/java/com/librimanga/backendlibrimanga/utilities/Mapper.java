package com.librimanga.backendlibrimanga.utilities;

import com.librimanga.backendlibrimanga.dto.outputs.*;
import com.librimanga.backendlibrimanga.model.*;
import com.librimanga.backendlibrimanga.repository.CategorieRepository;
import org.springframework.stereotype.Service;

@Service
public class Mapper {
    public static CategorieDTO buildCategorieDTO(Categorie c) {
        return CategorieDTO.builder()
                .id(c.getId())
                .categoria(c.getCategoria())
                .build();
    }

    public static AutoriDTO buildAutoriDTO(Autori a) {
        return AutoriDTO.builder()
                .id(a.getId())
                .nome(a.getNome())
                .cognome(a.getCognome())
                .build();
    }

    public static CaseEditriciDTO buildCaseEditriciDTO(CaseEditrici c) {
        return CaseEditriciDTO.builder()
                .id(c.getId())
                .nome(c.getNome())
                .sede(c.getSede())
                .build();
    }

    public static LibriDTO buildLibriDTO(Libri l) {
        return LibriDTO.builder()
                .id(l.getId())
                .titolo(l.getTitolo())
                .descrizione(l.getDescrizione())
                .copertina(l.getCopertina())
                .prezzo(l.getPrezzo())
                .dataPubblicazione(l.getDataPubblicazione())
                .quantitaDisponibile(l.getQuantitaDisponibile())
                .autore(buildAutoriDTO(l.getAutore()))
                .categorie(l.getCategorie().stream().map(Mapper::buildCategorieDTO).toList())
                .casaEditrice(buildCaseEditriciDTO(l.getCasaEditrice()))
                .build();
    }

    public static UtentiDTO buildUtentiDTO(Utenti u) {
        return UtentiDTO.builder()
                .id(u.getId())
                .nome(u.getNome())
                .cognome(u.getCognome())
                .email(u.getEmail())
                .password(u.getPassword())
                .ruolo(u.getRuolo().toString())
                .via(u.getVia())
                .provincia(u.getProvincia())
                .telefono(u.getTelefono())
                .build();
    }
}
