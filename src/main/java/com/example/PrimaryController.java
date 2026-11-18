package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class PrimaryController {

    @FXML private HBox mesaDoJogador;
    @FXML private HBox mesaDoComputador;
    @FXML private ImageView monte;

    @FXML private Label pontosMesa;
    @FXML private Label pontosJogador;
    @FXML private Label resultado;

    private Jogo jogo = new Jogo();

    private void turno(){
        jogo.distribuiCartaParaJogador(jogo.getJogador());
        jogo.distribuiCartaParaJogador(jogo.getComputador());
        atualizarTela();

        if (jogo.acabou()) {
            resultado.setText(jogo.resultado());
        }
    }

    private void atualizarTela(){
        pontosJogador.setText("Jogador: " + jogo.getJogador().getPontos());
        pontosMesa.setText("Mesa: " + jogo.getComputador().getPontos());

        mesaDoJogador.getChildren().clear();
        mesaDoComputador.getChildren().clear();

        jogo.getJogador().getCartas().forEach((carta) -> mesaDoJogador.getChildren().add(criarImagemDaCarta(carta)));
        jogo.getComputador().getCartas().forEach((carta) -> mesaDoComputador.getChildren().add(criarImagemDaCarta(carta)));
    }

    public void novoJogo(){
        jogo = new Jogo();
        mesaDoJogador.getChildren().clear();
        mesaDoComputador.getChildren().clear();
        pontosJogador.setText("Jogador: 0");
        pontosMesa.setText("Mesa: 0");
        resultado.setText("");
    }

    public void pedirCarta(){
        if (jogo.acabou()) {
            return;
        }
        turno();
    }

    public void parar(){
        if (jogo.acabou()) {
            return;
        }
        jogo.getJogador().parar();
        do {
            turno();
        } while (!jogo.acabou());
    }


    private ImageView criarImagemDaCarta(Carta carta) {
        return new ImageView(App.class.getResource(carta.imagePath()).toString());
    }

}
