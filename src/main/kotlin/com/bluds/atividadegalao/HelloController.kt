package com.bluds.atividadegalao
// pacote principal

import com.bluds.atividadegalao.algoritm.AlgoritmBFS
import com.bluds.atividadegalao.algoritm.AlgoritmDFS
import com.bluds.atividadegalao.data.GraphData
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView

class HelloController {
    // esse controller controla os eventos da tela

    @FXML private lateinit var cboAlgo: ComboBox<String>
    // combo pra escolher o algoritmo

    @FXML private lateinit var cboOrig: ComboBox<String>
    // combo da cidade de origem

    @FXML private lateinit var cboDest: ComboBox<String>
    // combo da cidade de destino

    @FXML private lateinit var output: TextArea
    // área de texto pra mostrar o resultado

    @FXML private lateinit var imgFatec: ImageView
    // imagem de cima (logo fatec)

    @FXML
    fun initialize() {
        // esse método roda quando a tela abre

        val imageUrl = javaClass.getResource("/com/bluds/atividadegalao/fatec.jpg")
        // tento carregar a imagem

        if (imageUrl != null) {
            val image = Image(imageUrl.toExternalForm())
            imgFatec.image = image
            // coloco a imagem no ImageView
        } else {
            println("⚠️ Imagem não encontrada no classpath!")
        }

        cboAlgo.items.addAll("BFS", "DFS")
        cboAlgo.selectionModel.selectFirst()
        // preencho o combo de algoritmos e já deixo BFS selecionado

        cboOrig.items.addAll(GraphData.cities)
        cboDest.items.addAll(GraphData.cities)
        // preencho as cidades de origem e destino

        cboOrig.selectionModel.select("Campinas")
        cboDest.selectionModel.select("Tatui")
        // deixo padrão de origem = Campinas e destino = Tatui
    }

    @FXML
    private fun onStartClick() {
        // esse método roda quando clica no botão "Iniciar"

        val algo = cboAlgo.value
        // pego qual algoritmo foi escolhido

        val start = GraphData.cities.indexOf(cboOrig.value)
        val goal = GraphData.cities.indexOf(cboDest.value)
        // pego o índice das cidades escolhidas

        val result = when(algo) {
            "BFS" -> AlgoritmBFS().search(start, goal)
            else -> AlgoritmDFS().search(start, goal)
        }
        // rodo a busca dependendo da escolha

        val sb = StringBuilder()
        // stringbuilder pra montar o texto de saída

        sb.appendLine("Algoritmo: $algo")
        sb.appendLine("Origem: ${cboOrig.value}")
        sb.appendLine("Destino: ${cboDest.value}")
        sb.appendLine()

        sb.appendLine("Árvore de busca:")
        result.treeEdges.forEach { (p,f,w) ->
            sb.appendLine("  ${GraphData.cities[p]} -> ${GraphData.cities[f]} [$w]")
        }
        // mostro as arestas da busca

        sb.appendLine()
        sb.append("Caminho: ")
        sb.append(result.path.joinToString(" -> ") { GraphData.cities[it] })
        sb.appendLine()
        // mostro o caminho encontrado

        sb.appendLine("Distância: ${result.pathDistance}")
        // mostro a distância total

        output.text = sb.toString()
        // jogo tudo no textArea
    }
}
