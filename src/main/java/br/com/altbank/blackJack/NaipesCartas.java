package br.com.altbank.blackJack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;



@Getter

public enum NaipesCartas {
    PAUS ("Paus", "♣"),
    COPAS ("Copas", "♥"),
    ESPADAS ("Espadas", "♠"),
    OURO ("Ouro", "♦");

    private final String nomeNaipe;

    private final String simboloNaipe;


    private NaipesCartas(String nomeNaipe, String simboloNaipe){
        this.nomeNaipe = nomeNaipe;
        this.simboloNaipe = simboloNaipe;
    }


}
