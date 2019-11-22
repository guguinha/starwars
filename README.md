# Star Wars Resistence Social Network


## Descri��o do problema

O imp�rio continua sua luta incessante de dominar a gal�xia, tentando ao m�ximo expandir seu territ�rio e eliminar os rebeldes.

Voc�, como um soldado da resist�ncia, foi designado para desenvolver um sistema para compartilhar recursos entre o rebeldes.

## Requisitos

Voc� ir� desenvolver uma **API REST** (sim, n�s levamos a arquitetura da aplica��o a s�rio mesmo no meio de uma guerra), ao qual ir� armazenar informa��o sobre os rebeldes, bem como os recursos que eles possuem.

* **Adicionar rebeldes**

  Um rebelde deve ter um *nome*, *idade*, *g�nero*, *localiza��o*(latitude, longitude e nome, na gal�xia, da base ao qual faz parte).

  Um rebelde tamb�m possui um invent�rio que dever� ser passado na requisi��o com os recursos em sua posse.

* **Atualizar localiza��o do rebelde**

  Um rebelde deve possuir a capacidade de reportar sua �ltima localiza��o, armazenando a nova latitude/longitude/nome (n�o � necess�rio rastrear as localiza��es, apenas sobrescrever a �ltima � o suficiente).

* **Reportar o rebelde como um traidor** (falta implementação)

  Eventualmente algum rebelde ir� trair a resist�ncia e se aliar ao imp�rio. Quando isso acontecer, n�s precisamos informar que o rebelde � um traidor.

  Um traidor n�o pode negociar os recursos com os demais rebeldes, n�o pode manipular seu invent�rio, nem ser exibido em relat�rios.

  **Um rebelde � marcado como traidor quando, ao menos, tr�s outros rebeldes reportarem a trai��o.**

  Uma vez marcado como traidor, os itens do invent�rio se tornam inacess�veis (eles n�o podem ser negociados com os demais).

* **Rebeldes n�o podem Adicionar/Remover itens do seu invent�rio**

  Seus pertences devem ser declarados quando eles s�o registrados no sistema. Ap�s isso eles s� poder�o mudar seu invent�rio atrav�s de negocia��o com os outros rebeldes.

* **Negociar itens**

  Os rebeldes poder�o negociar itens entre eles.

  Para isso, eles devem respeitar a tabela de pre�os abaixo, onde o valor do item � descrito em termo de pontos.

  Ambos os lados dever�o oferecer a mesma quantidade de pontos. Por exemplo, 1 arma e 1 �gua (1 x 4 + 1 x 2) valem 6 comidas (6 x 1) ou 2 muni��es (2 x 3).

  A negocia��o em si n�o ser� armazenada, mas os itens dever�o ser transferidos de um rebelde a outro.

  | Item      | Pontos   |
  |-----------|----------|
  | 1 Arma    | 4 pontos |
  | 1 Muni��o | 3 pontos |
  | 1 �gua    | 2 pontos |
  | 1 Comida  | 1 ponto  |

* **Relat�rios**

  A API deve oferecer os seguintes relat�rios:

  1. Porcentagem de traidores.
  2. Porcentagem de rebeldes.
  3. Quantidade m�dia de cada tipo de recurso por rebelde (Ex: 2 armas por rebelde).
  4. Pontos perdidos devido a traidores.

# Notas

1. Dever� ser utilizado Java, Spring boot, Spring Data, Hibernate (pode ser usado o banco de dados H2) e como gerenciador de depend�ncia Maven ou Gradle.
2. N�o ser� necess�rio autentica��o.
3. N�s ainda nos preocupamos com uma programa��o adequada (c�digo limpo) e t�cnicas de arquitetura, voc� deve demonstrar que � um digno soldado da resist�ncia atrav�s das suas habilidades.
4. N�o esque�a de minimamente documentar os endpoints da sua API e como usa-los.
5. Sua API deve estar minimamente coberta por testes (Unit�rios e/ou integra��o).
6. Da descri��o acima voc� pode escrever uma solu��o b�sica ou adicionar requisitos n�o descritos. Use seu tempo com sabedoria; Uma solu��o �tima e definitiva pode levar muito tempo para ser efetiva na guerra, ent�o voc� deve trazer a melhor solu��o poss�vel, que leve o m�nimo de tempo, mas que ainda seja capaz de demonstrar suas habilidades e provar que voc� � um soldado valioso para a resist�ncia.
7. Comente qualquer d�vida e cada decis�o tomada.

# Tasks 

- [x] Adicionar rebeldes
- [x] Atualizar localização do rebelde OK
- [] Reportar o rebelde como um traidor NOT
   -  [] Modificar Rebelde como traidor NOT
- [] Rebeldes não podem Adicionar/Remover itens do seu inventário Verificar NOT
- [] Negociar itens NOT
- [x] Relatórios Porcentagem de traidores
- [x] Relatórios Porcentagem de rebeldes
- [x] Relatórios Quantidade média de cada tipo de recurso por rebelde
- [x] Relatórios Pontos perdidos devido a traidores

## Verificar Mellhorias
- [] de Logica
- [] de comentarios

- [] Adicionar Testes (Unitários e/ou integração)

- [] Documentação Swagger