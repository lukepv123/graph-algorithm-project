package com.bluds.atividadegalao

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class HelloApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("fatec-view.fxml"))
        val scene = Scene(fxmlLoader.load(), 400.0, 400.0)
        stage.title = "Atividade Fatec"
        stage.scene = scene
        stage.toFront()
        stage.show()
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}