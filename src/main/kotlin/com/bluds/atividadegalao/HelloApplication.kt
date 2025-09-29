package com.bluds.atividadegalao
// pacote principal

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage

class HelloApplication : Application() {
    override fun start(stage: Stage) {
        // esse é o ponto de entrada do JavaFX

        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("fatec-view.fxml"))
        // carrego o layout da tela a partir do arquivo fxml

        val scene = Scene(fxmlLoader.load(), 400.0, 400.0)
        // crio a cena com tamanho inicial

        val iconUrl = HelloApplication::class.java.getResource("/com/bluds/atividadegalao/fatec.jpg")
        // tento carregar o ícone do app

        if (iconUrl != null){
            stage.icons.add(Image(iconUrl.toExternalForm()))
            // se achei, defino como ícone da janela
        }

        stage.title = "Atividade Fatec"
        // titulo da janela

        stage.scene = scene
        // coloco a cena na janela

        stage.toFront()
        // jogo a janela pra frente

        stage.show()
        // mostro a janela
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
    // ponto principal pra rodar o app
}
