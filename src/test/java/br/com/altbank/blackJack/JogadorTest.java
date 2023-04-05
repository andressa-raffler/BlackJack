package br.com.altbank.blackJack;

import org.junit.jupiter.api.*;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class JogadorTest {

    private Jogador jogador;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @BeforeEach
    public void setup(){
        jogador = new Jogador();
        System.setOut(new PrintStream(outContent));
    }
    @Test
    @DisplayName("Teste do contador da pontuaçao")
    void testContadorPontuacao() {
        //Given
        jogador.contadorPontuacao(10);
        //Then Verifica se a pontuaçao foi adicionada
        assertEquals(10, jogador.getPontuacao());
        //Given
        jogador.contadorPontuacao(5);
        //Then Verifica se a pontuaçao foi adicionada a pontuaçao existente
        assertEquals(15, jogador.getPontuacao());
    }

    @Test
    @DisplayName("Testa se o jogador segurou a carta")
    void testSegurarCarta() {
        // Given
        Jogo jogo = new Jogo("teste");
        Carta carta = new Carta();
        carta.setNaipesCartas( NaipesCartas.PAUS);
        carta.setNumerosCartas(NumerosCartas.AS);
        //When
        jogador.segurarCarta(carta);
        //Then
        // Verifica se a carta foi adicionada na mão
        assertTrue(jogador.getMao().contains(carta));
        // Verifica se a pontuação foi atualizada corretamente
        assertEquals(1, jogador.getPontuacao());
        // Verifica se o contador de jogadas foi atualizado corretamente
        assertEquals(1, jogador.getJogadas());
    }

    @Test
    @DisplayName("Verifica se pontuaçao do jogador ainda nao estourou")
    void testVerificaSeJogadorAindaNaoEstourou() {
        // Teste para pontuação menor que 21
        // Given
        jogador.contadorPontuacao(15);
        jogador.setNome("Andressa");
        //When
        jogador.verificaSeJogadorEstourou();
        //Then
        assertEquals("O/A Andressa ainda nao estourou", outContent.toString().trim());
    }

    @Test
    @DisplayName("Verifica se o jogador atingiu 21 pontos")
    void testVerificaSeJogadorAtingiu21() {
        // Teste para pontuação igual a 21
        // Given
        jogador.contadorPontuacao(21);
        // When
        jogador.setNome("Andressa");
        jogador.verificaSeJogadorEstourou();
        //Then
        assertEquals("O/A Andressa atingiu 21!!", outContent.toString().trim());
    }

    @Test
    @DisplayName("Verifica se o jogador estourou os 21 pontos")
    void verificaSeJogadorEstourou(){
        // Teste para pontuação maior que 21
        //Given
        jogador.contadorPontuacao(22);
        jogador.setNome("Andressa");
        //When
        jogador.verificaSeJogadorEstourou();
        //Then
        assertEquals("O/A Andressa estourou!!", outContent.toString().trim());
    }

    @AfterEach
    public void afterAll(){
        System.setOut(originalOut);
    }


}