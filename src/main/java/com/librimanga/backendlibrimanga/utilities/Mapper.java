package com.librimanga.backendlibrimanga.utilities;

import com.librimanga.backendlibrimanga.dto.outputs.*;
import com.librimanga.backendlibrimanga.model.*;
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
                .ruolo(u.getRuolo().toString())
                .via(u.getVia())
                .provincia(u.getProvincia())
                .telefono(u.getTelefono())
                .immagineProfilo(u.getImmagineProfilo())
                .libriPreferiti(u.getLibriPreferiti().stream().map(Mapper::buildLibriDTO).toList())
                .build();
    }

    public static OrdiniDTO buildOrdiniDTO(Ordini o) {
        return OrdiniDTO.builder()
                .id(o.getId())
                .utente(buildUtentiDTO(o.getUtente()))
                .dettagliOrdine(o.getDettagliOrdini().stream().map(Mapper::buildDettagliOrdiniDTO).toList())
                .build();
    }

    public static DettagliOrdiniDTO buildDettagliOrdiniDTO(DettagliOrdini d) {
        return DettagliOrdiniDTO.builder()
                .id(d.getId())
                .libro(buildLibriDTO(d.getLibro()))
                .quantita(d.getQuantita())
                .costoTotale(d.getCostoTotale())
                .stato(d.getStato().toString())
                .data(d.getData())
                .build();
    }

    public static RecensioniDTO buildRecensioniDTO(Recensioni r) {
        return RecensioniDTO.builder()
                .id(r.getId())
                .stelle(r.getStelle())
                .contenuto(r.getContenuto())
                .libro(buildLibriDTO(r.getLibro()))
                .utente(buildUtentiDTO(r.getUtente()))
                .data(r.getData())
                .build();
    }

    public static DettagliCarrelliDTO buildDettagliCarrelliDTO(DettagliCarrelli c) {
        return DettagliCarrelliDTO.builder()
                .id(c.getId())
                .quantita(c.getQuantita())
                .libro(buildLibriDTO(c.getLibro()))
                .utente(buildUtentiDTO(c.getUtente()))
                .build();
    }
}
