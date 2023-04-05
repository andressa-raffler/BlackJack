# ♣♠♥♦ BLACKJACK 21 ♣♠♥♦


## PRÉ REQUISITOS

Para inicializar o jogo será necessária a instalação das seguintes ferramentas:

- [Java](https://www.java.com/en/download/help/download_options.html)
- [maven](https://maven.apache.org/download.cgi)

## INICIALIZAR O JOGO

Primeiro, baixe o jogo do meu repositorio e builde o projeto.

```
git clone https://github.com/andressa-raffler/BlackJack21.git

cd BlackJack21

mvn clean install
```

Agora com o projeto buildado, para iniciar uma partida pelo terminal, execute o seguinte comando abaixo:

```
cd target/ && java -jar blackJack-0.0.1-SNAPSHOT.jar
```

O programa irá iniciar e apresentar a seguinte tela:
```
$
###################################
#  Bem vindo ao jogo BlackJack!!  #
###################################

1 - Identificar-se
2 - Iniciar Jogo
0 - Sair
```

## COMO JOGAR?

Voce pode identificar-se ou iniciar direto uma nova partida, nesse caso, será chamado de player1.
Voce irá jogar contra o computador.
As primeiras duas cartas de cada jogador sao distribuídas obrigatoriamente, após isso, voce poderá optar por pegar mais uma carta ou parar.

Abaixo exemplo da mesa após a primeira rodada:
```
###################################
#####           MESA          #####
###################################

MAO COMPUTADOR: Quatro ♠, XX, PONTUAÇAO : 4
MAO PLAYER1: Dama ♥, Dez ♣, PONTUAÇAO: 20
```
Durante o jogo, apenas a primeira carta do Computador fica exposta, desta forma nao é possivel saber a sua pontuaçao total da rodada.

Quando for sua vez de jogar, voce poderá optar por pegar mais uma carta, ou parar.

```
VEZ DO/A PLAYER1
Deseja mais uma carta? S/N
````

Ganha quem conseguir chegar mais perto dos 21 pontos, sem ultrapassar.



# TESTES UNITÁRIOS
Execute o seguinte comando para rodar os testes unitários do programa.

```
cd BlackJack21
mvn test
```


## SOBRE A APLICAÇAO

- Esta primeira versao contempla a funcionalidade de jogar BlackJack contra o computador. Para uma próxima versao esta prevista a implementaçao de um sistema de fichas e apostas.
