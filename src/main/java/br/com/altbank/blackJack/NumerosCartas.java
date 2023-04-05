package br.com.altbank.blackJack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;



@Getter
public enum NumerosCartas {
    DOIS("Dois", 2),
    TRES("Tres", 3),
    QUATRO("Quatro",4),
    CINCO("Cinco",5),
    SEIS("Seis",6),
    SETE("Sete",7),
    OITO("Oito",8),
    NOVE("Nove",9),
    DEZ("Dez",10),
    VALETE("Valete",10),
    DAMA("Dama",10),
    REI("Rei",10),
    AS("As",1);

    private final String nome;
    private final int valor;

    private NumerosCartas(String nome, int valor){
        this.nome = nome;
        this.valor = valor;
    }


}
