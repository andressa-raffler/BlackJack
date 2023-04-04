package br.com.altbank.blackJack;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;
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
        assertTrue(Arrays.equals(baralho.getCartas().toArray(), baralho.getCartas().toArray()));
        assertFalse(Arrays.equals(baralho.getCartas().toArray(), baralho.embaralhar().getCartas().toArray()));
        Assertions.assertEquals(52, baralho.embaralhar().getCartas().size());

    }
    @Test
    @DisplayName("Teste de puxarCarta")
    public void testPuxarCarta() {
        Carta carta = baralho.puxarCarta();
        Assertions.assertNotNull(carta);
        Assertions.assertEquals(51, baralho.getCartas().size());
    }


}


