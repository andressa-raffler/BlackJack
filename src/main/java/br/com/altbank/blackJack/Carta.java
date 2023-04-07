package br.com.altbank.blackJack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;


@Data
public class Carta {

    private NaipesCartas naipesCartas;
    private NumerosCartas numerosCartas;


}