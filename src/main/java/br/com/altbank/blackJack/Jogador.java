package br.com.altbank.blackJack;



import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Getter
@Component
public class Jogador {

    private String nome;
    private ArrayList<Carta> mao = new ArrayList<>();
    private int pontuacao;
    private int jogadas;


    public void segurarCarta(Carta carta) {
        mao.add(carta);
        contadorPontuacao(carta.getNumerosCartas().getValor());
        contadorJogadas(1);
    }

    public void contadorPontuacao(int pontos){
       this.pontuacao = this.pontuacao + pontos;
    }

    public void contadorJogadas(int jogada){
        this.jogadas = this.jogadas + jogada;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
}
