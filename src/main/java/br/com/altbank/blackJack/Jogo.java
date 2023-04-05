package br.com.altbank.blackJack;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
@Data
public class Jogo {


    Jogador jogador;
    Jogador computador;
    Jogador jogadorDaVez;
    Baralho baralho;
    Scanner scanner = new Scanner(System.in);
    int numeroRodada = 0;
    int menu = 1;
    boolean novaRodada = true;
    boolean jogadorParou = false;
    boolean computadorParou = false;


    public Jogo(String nome){

    }

    public Jogo() {
        mensagemBoasVindas();
        menuEntrada();
    }

    private void menuEntrada() {
        //Menu de entrada
        while(menu >= 1){
            System.out.println("\n1 - Identificar-se");
            System.out.println("2 - Iniciar Jogo");
            System.out.println("0 - Sair");
            menu = scanner.nextInt();
            scanner.nextLine();
            if(menu == 1){
                identificarJogadores(menu);
                menu = 2;
            }
            if(menu == 2) {
                novaRodada = true;
                System.out.println("\nCOMEÇANDO O JOGO!");
                identificarJogadores(menu);
                jogar();
            }
            if(menu > 2){
                System.out.println("Menu inválido!");
            }
        }
    }
    //Jogar
    public void jogar() {
        iniciarJogo();
        rodadas();
    }

    //Embaralhar as cartas e iniciar o jogo
    private void iniciarJogo() {
        this.baralho = new Baralho();
         baralho.embaralhar();
    }

    //Identificar jogadores
    private void identificarJogadores(int menu){

        if(jogador == null || numeroRodada == 0) {
            this.jogador = new Jogador();
            this.computador = new Jogador();
        }
        if(menu == 1 ) {
            System.out.println("Digite seu nome");
            String nomeScanner = scanner.nextLine();
            jogador.setNome(nomeScanner);
            if (jogador.getNome().equals("Computador")) {
                System.out.println("Nome inválido");
                identificarJogadores(1);
            }
        }else if (menu == 2 && jogador.getNome() == null){
            jogador.setNome("Player1");
            computador.setNome("Computador");
        }
    }

    private void nomearJogadores(){

        this.jogador = new Jogador();
        this.computador = new Jogador();
        System.out.println("Digite seu nome");
        String nomeScanner = scanner.nextLine();
        jogador.setNome(nomeScanner);
        if (jogador.getNome().equals("Computador")) {
            System.out.println("Nome inválido");
            nomearJogadores();

        }
    }


    private void limparDados() {
        this.jogador = new Jogador();
        this.computador = new Jogador();
        jogadorParou = false;
        computadorParou = false;
        numeroRodada = 0;
        novaRodada = true;
    }

    //Nova rodada
    private void rodadas(){
        while(novaRodada) {
            numeroRodada += 1;
            identificarJogadorDaVez();
            if(pegarMaisUmaCarta()) {
                jogadorDaVez.segurarCarta(baralho.puxarCarta());
            }
            imprimirCartas();
            verificarPontuacao();
            if(!novaRodada){
                verificarQuemVenceu();
                desejaJogarNovamente();
            }
        }
    }

    private void desejaJogarNovamente() {
        System.out.println("\nDeseja jogar novamente? (S/N)");
        String resposta = scanner.nextLine();
        if(resposta.equalsIgnoreCase("s") || resposta.equalsIgnoreCase("sim")){
            limparDados();
            menuEntrada();
        } else {
            novaRodada = false;
            mensagemAgradecimento();
        }
    }

    private void imprimirCartas() {
        if (numeroRodada >= 4) { //Mostrar as cartas somente após dadas as 4 primeiras cartas
            imprimirCartasComputador();
            imprimirCartasJogador();
        }
    }
    private void imprimirCartasComputador(){
        if(!jogadorParou){
            mensagemMesa();
            System.out.print("MAO COMPUTADOR: " + computador.getMao().get(0).getNumerosCartas().getNome() + " "
                    + computador.getMao().get(0).getNaipesCartas().getSimboloNaipe() + ", ");
            for (int i = 1; i < computador.getMao().size(); i++) {
                System.out.print("XX, ");
            }
            System.out.println("PONTUAÇAO : " + computador.getMao().get(0).getNumerosCartas().getValor());
        }
        else if (jogadorParou || !novaRodada){
            mensagemMesa();
            System.out.print("MAO COMPUTADOR: ");
            for (Carta carta : computador.getMao()) {
                System.out.print(carta.getNumerosCartas().getNome()+" "
                        +carta.getNaipesCartas().getSimboloNaipe()+", ");
            }
            System.out.println("PONTUAÇAO : " + computador.getPontuacao());
        }
    }

    private void imprimirCartasJogador(){
        System.out.print("MAO " + jogador.getNome().toUpperCase() + ": ");
        for (Carta carta : jogador.getMao()) {
            System.out.print(carta.getNumerosCartas().getNome() + " " + carta.getNaipesCartas().getSimboloNaipe() +", ");
        }
        System.out.print("PONTUAÇAO: " + jogador.getPontuacao());
        System.out.println();
    }


    public void verificarPontuacao(){
        if(numeroRodada > 4) {
            jogadorDaVez.verificaSeJogadorEstourou();
            if(jogadorDaVez.getPontuacao() > 21) {
                jogadorParou = true;
                novaRodada = false;
            }
        }
    }


    private void verificarQuemVenceu(){
        if(computador.getPontuacao() > 21 && jogador.getPontuacao() > 21 ){
            mensagemFimDeJogo();
            System.out.println("\nOs dois estouraram!!");
            return;
        }
        else if(computador.getPontuacao() > 21){
            mensagemFimDeJogo();
            System.out.println("\nO/A "+jogador.getNome()+" venceu!!");
            return;
        }
        else if(jogador.getPontuacao() > 21){
            mensagemFimDeJogo();
            System.out.println("\nO computador venceu!!");
            return;
        }
        else if(computador.getPontuacao() == jogador.getPontuacao()){
            mensagemFimDeJogo();
            System.out.println("\nHouve um empate!!");
        }
        else if(computador.getPontuacao() > jogador.getPontuacao()){
            mensagemFimDeJogo();
            System.out.println("\nO Computador venceu!");
        }
        else {
            mensagemFimDeJogo();
            System.out.println("\nO/A "+jogador.getNome()+" venceu!");
        }
    }

    //Identificar o jogador da rodada
    private void identificarJogadorDaVez(){

        switch (numeroRodada) {
            case 1,2 -> jogadorDaVez = computador;
            case 3,4 -> jogadorDaVez = jogador;
            case 5 -> {
                      jogadorDaVez = jogador;
                      imprimirRodada(); }
            case 6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24 -> jogadasIntercaladas();
        }
    }

    //Passar a vez para o outro jogador, ou continuar caso o outro já tenha parado
    private void jogadasIntercaladas(){
        if (jogadorParou && computadorParou){
            novaRodada = false;
            return;
        }
        if(jogadorParou){
            jogadorDaVez = computador;
            imprimirRodada();
            return;
        }
        if(computadorParou){
            jogadorDaVez = jogador;
            imprimirRodada();
            return;
        }
        if (numeroRodada > 5 && !jogadorParou){    //Passar a vez para o outro jogador
            if(jogadorDaVez.getNome().equals("Computador")){
                jogadorDaVez = jogador;
                imprimirRodada();
            }else {
                jogadorDaVez = computador;
                imprimirRodada();
            }
        }
    }

    //Imprimir no console o nome do jogador da vez
    private void imprimirRodada(){
        System.out.println("\n");
        System.out.println("\nVEZ DO/A " + jogadorDaVez.getNome().toUpperCase());
    }


    private boolean pegarMaisUmaCarta(){

        switch (numeroRodada) {
            case 1,2,3,4 -> { return true; } //primeiras 2 cartas de cada jogador sao obrigatórias
        }
        if(vezDoComputador()){
            if(temMaisDe17Pontos()){
                computadorParou = true; //computador para quando atingir 17 pontos
                return false;
            }
        }else if (!jogadorParou){
            if (temMaisDe21Pontos() || !usuarioDesejaMaisUmaCarta()){
                jogadorParou = true;
                return false;
            }
        }
        return true;
    }

    private boolean vezDoComputador(){
        return  (jogadorDaVez.getNome().equals("Computador"));
    }

    private boolean temMaisDe17Pontos(){
        return (computador.getPontuacao() >= 17 ) ;
    }

    private boolean temMaisDe21Pontos(){
        return (jogador.getPontuacao() > 21);
    }

    private boolean usuarioDesejaMaisUmaCarta(){
        System.out.println("Deseja mais uma carta? S/N");
        String input = scanner.nextLine();
        return validarComandoEntrada(input);
    }

    //Validar resposta do usuário
    private Boolean validarComandoEntrada(String comandoEntrada) {
        if (comandoEntrada.equalsIgnoreCase("S") || comandoEntrada.equalsIgnoreCase("Sim")){
            return true;
        }
        if( comandoEntrada.equalsIgnoreCase("N") || comandoEntrada.equalsIgnoreCase("Nao")){
            return false;
        }
        System.out.println("Resposta incorreta");
        pegarMaisUmaCarta();
        return false;
    }


    private void mensagemBoasVindas() {
        System.out.println("\n###################################");
        System.out.println("#  Bem vindo ao jogo BlackJack!!  #");
        System.out.println("###################################");
    }

    private void mensagemMesa() {
        System.out.println("\n###################################");
        System.out.println("#####           MESA          #####");
        System.out.println("###################################");
    }

    private void mensagemFimDeJogo(){
        imprimirCartas();
        System.out.println("\n###################################");
        System.out.println("##          FIM DE JOGO          ##");
        System.out.println("###################################");
    }

    private void mensagemAgradecimento(){
        System.out.println("\n###################################");
        System.out.println("#  Obrigado por jogar BlackJack!! #");
        System.out.println("###################################");
    }

}





