package br.com.altbank.blackJack;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BaralhoTest {

    private Baralho baralho;
    @BeforeEach
    public void setup() {
        baralho = new Baralho();
    }

    @Test
    @DisplayName("Teste de embaralhar")
    public void testEmbaralhar() {
        //Verifica se a lista de cartas do baralho mudou de ordem após embaralhar
        assertTrue(Arrays.equals(baralho.getCartas().toArray(), baralho.getCartas().toArray()));
        assertFalse(Arrays.equals(baralho.getCartas().toArray(), baralho.embaralhar().getCartas().toArray()));
        //Verifica se o baralho continua com 52 cartas mesmo após embaralhar
        Assertions.assertEquals(52, baralho.embaralhar().getCartas().size());

    }
    @Test
    @DisplayName("Teste de puxar uma carta")
    public void testPuxarCarta() {
        Carta carta = baralho.puxarCarta();
        //Verifica se a carta do baralho nao esta nula
        Assertions.assertNotNull(carta);
        //Verifica se o baralho ficou com uma carta a menos após perder uma carta
        Assertions.assertEquals(51, baralho.getCartas().size());
    }
}


