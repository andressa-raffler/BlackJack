package br.com.altbank.blackJack;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Component
public class Baralho {

    private List<Carta> cartas;
    public Baralho() {
        this.cartas = new ArrayList<>();
        for (NaipesCartas naipeCarta : NaipesCartas.values()) {
            for (NumerosCartas numeroCarta : NumerosCartas.values()) {
                Carta carta = new Carta();
                carta.setNaipesCartas(naipeCarta);
                carta.setNumerosCartas(numeroCarta);
                this.cartas.add(carta);
            }
        }
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public Baralho embaralhar() {
        Collections.shuffle(this.cartas);
        return this;
    }

    public Carta puxarCarta() {
        return this.cartas.remove(0);
    }
}

