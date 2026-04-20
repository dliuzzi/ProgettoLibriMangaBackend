package com.librimanga.backendlibrimanga.controller;


import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @PostMapping
    public Map<String, String> chat(@RequestBody Map<String, String> body) {
        String msg = body.get("message").toLowerCase();

        if (msg.contains("buongiorno") || msg.contains("ciao")) {
            return Map.of("reply", "Ciao! Come posso aiutarti?");
        }

        if (msg.contains("telefono") || msg.contains("numero")) {
            return Map.of("reply", "Puoi contattarci al 080-1234567.");
        }

        if (msg.contains("orari") || msg.contains("aperti")) {
            return Map.of("reply", "Siamo aperti dal lunedì al sabato, 9:00 - 19:00.");
        }

        if (msg.contains("prodotti") || msg.contains("disponibili")) {
            return Map.of("reply", "Puoi vedere tutti i prodotti nella sezione Prodotti del sito.");
        }

        return Map.of("reply", "Non ho capito bene, puoi ripetere?");
    }
}

