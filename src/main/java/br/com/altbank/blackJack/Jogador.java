package br.com.altbank.blackJack;



import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Jogador {

    private String nome;
    private ArrayList<Carta> mao = new ArrayList<>();
    private int pontuacao;
    private int jogadas;
    private boolean parou = false;


    public String getNome(){
        if(nome == null){
            return "Player1";
        }
        return nome;
    }

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

    public void setParou(boolean parou) {
        this.parou = parou;
    }

    public void verificaSeJogadorEstourou(){
        String status;
        if (pontuacao < 21) {
                status = "ainda nao estourou";
            } else if (pontuacao == 21) {
                status = "atingiu 21!!";
            } else {
                status = "estourou!!";
            }
            System.out.printf("\nO/A %s %s", nome, status);
        }
    }


