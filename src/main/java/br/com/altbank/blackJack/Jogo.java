package br.com.altbank.blackJack;


import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
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
    boolean computadorParou = true;



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

    private void mensagemBoasVindas() {
        //Boas vindas ao jogo
        System.out.println("###################################");
        System.out.println("#  Bem vindo ao jogo BlackJack!!  #");
        System.out.println("###################################");
    }

    //Jogar
    public void jogar() {
        limparDados();
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

        this.jogador = new Jogador();
        this.computador = new Jogador();
        if(menu == 1 ) {
            System.out.println("Digite seu nome");
            jogador.setNome(scanner.nextLine());
            if (jogador.getNome().equals("Computador")) {
                System.out.println("Nome inválido");
                identificarJogadores(1);
            }
        }else if (menu == 2 && jogador.getNome() == null){
            jogador.setNome("Player1");
            computador.setNome("Computador");
        }
    }

    private void limparDados() {
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
            exibirDetalhes();
            verificarSePontuacaoEstourou();
            if(!novaRodada){
                verificarQuemVenceu();
                desejaJogarNovamente();
            }
        }

    }

    private void desejaJogarNovamente() {
        System.out.println("\nDeseja jogar novamente? (S/N)");
        String resposta = scanner.nextLine();
        if(resposta.equalsIgnoreCase("s")){
           menuEntrada();
        } else {
            novaRodada = false;
            System.out.println("\n###################################");
            System.out.println("#  Obrigado por jogar BlackJack!! #");
            System.out.println("###################################");
        }
    }


    //Verificar se pontuaçao estourou 21
    private void verificarSePontuacaoEstourou() {
        if(numeroRodada > 4) {
            verificarSeEstourou();
        }
    }

    private void exibirDetalhes() {
        if (numeroRodada >= 4) {
            System.out.println("\n###################################");
            System.out.println("#####           MESA          #####");
            System.out.println("###################################");
            System.out.println();
            if(!jogadorParou){
                System.out.print("MAO COMPUTADOR: " + computador.getMao().get(0).getNumerosCartas().getNome() + " "
                        + computador.getMao().get(0).getNaipesCartas().getSimboloNaipe() + ", ");
                for (int i = 1; i < computador.getMao().size(); i++) {
                    System.out.print("XX, ");
                }
                    System.out.println("PONTUAÇAO : " + computador.getMao().get(0).getNumerosCartas().getValor());
            }
            else if(jogadorParou) {
                System.out.print("MAO COMPUTADOR: ");
                for (Carta carta : computador.getMao()) {
                    System.out.print(carta.getNumerosCartas().getNome()+" "
                                    +carta.getNaipesCartas().getSimboloNaipe()+", ");
                }
                System.out.println("PONTUAÇAO : " + computador.getPontuacao());
            }

            System.out.print("\nMAO " + jogador.getNome().toUpperCase() + ": ");
            for (Carta carta : jogador.getMao()) {
                System.out.print(carta.getNumerosCartas().getNome() + " " + carta.getNaipesCartas().getSimboloNaipe() +", ");
            }
            System.out.print("PONTUAÇAO: " + jogador.getPontuacao());
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }


    private void verificarSeEstourou() {
        if(jogadorDaVez.getPontuacao() > 21){
            System.out.println(jogadorDaVez.getNome()+" estourou!!");
            novaRodada = false;
        }
    }

    private void verificarQuemVenceu(){
        if(computador.getPontuacao() > 21 && jogador.getPontuacao() > 21 ){
            System.out.println("Os dois estouraram!!");
            return;
        }
        else if(computador.getPontuacao() > 21){
            System.out.println("O/A "+jogador.getNome()+" venceu!!");
            return;
        }
        else if(jogador.getPontuacao() > 21){
            System.out.println("O computador venceu!!");
            return;
        }
        else if(computador.getPontuacao() == jogador.getPontuacao()){
            System.out.println("Houve um empate!!");
        }
        else if(computador.getPontuacao() > jogador.getPontuacao()){
            System.out.println("O Computador venceu!");
        }
        else {
            System.out.println("O/A "+jogador.getNome()+" venceu!");
        }
    }

    private void identificarJogadorDaVez(){
        if (numeroRodada == 1 || numeroRodada == 2){
            jogadorDaVez = computador;
            return;
        }
        if (numeroRodada >= 3 && numeroRodada <=5 && !jogadorParou){
            jogadorDaVez = jogador;
        }
        if (jogadorParou && computadorParou){
            novaRodada = false;
            return;
        }
        if(jogadorParou){
            jogadorDaVez = computador;
            System.out.println("RODADA " + numeroRodada + " VEZ DO COMPUTADOR");
            return;
        }
        if(computadorParou){
            jogadorDaVez = jogador;
            System.out.println("RODADA " + numeroRodada + " VEZ DO/A " + jogadorDaVez.getNome().toUpperCase());
            return;
        }
        if (numeroRodada > 5 && !jogadorParou){    //Passar a vez para o outro jogador
            if(jogadorDaVez.getNome().equals("Computador")){
                jogadorDaVez = jogador;
                System.out.println("RODADA " + numeroRodada + " VEZ DO/A " + jogadorDaVez.getNome().toUpperCase());
            }else {
                jogadorDaVez = computador;
                System.out.println("RODADA " + numeroRodada + " VEZ DO/A " + jogadorDaVez.getNome().toUpperCase());
            }
        }
    }

    private boolean pegarMaisUmaCarta(){
        Boolean comando;
        if(numeroRodada >=1 && numeroRodada <= 4){ //primeiras 2 cartas sao obrigatórias
            return true;
        }
        if (!jogadorDaVez.getNome().equals("Computador") && !jogadorParou){
            if(jogadorDaVez.getPontuacao() >21 ){ //se maior do que 21 para automaticamente
                jogadorParou = true;
                return false;
            }
            System.out.println("Deseja mais uma carta? S/N");
            String input = scanner.nextLine();
            comando = validarComandoEntrada(input);
            if(comando.equals(true)){
                return true;
            }else { //se o usuário digitar N é porque deseja parar
                jogadorParou = true;
                return false;
            }
        }
        if(jogadorDaVez.getNome().equals("Computador") && computador.getPontuacao() < 17){
            return true;
        }
        if(jogadorDaVez.getNome().equals("Computador") && computador.getPontuacao() >= 17 ){
            computadorParou = true; //computador para quando atingir 17 pontos
            return false;
        }
        return false;
    }

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


}





