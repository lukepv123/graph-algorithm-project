package com.bluds.atividadegalao

import com.bluds.atividadegalao.algoritm.AlgoritmBFS
import com.bluds.atividadegalao.algoritm.AlgoritmDFS
import com.bluds.atividadegalao.data.GraphData
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.image.ImageView

class HelloController {
    @FXML private lateinit var cboAlgo: ComboBox<String>
    @FXML private lateinit var cboOrig: ComboBox<String>
    @FXML private lateinit var cboDest: ComboBox<String>
    @FXML private lateinit var output: TextArea
    @FXML private lateinit var imgFatec: ImageView
    @FXML private lateinit var iconFatec: ImageView


    @FXML
    fun initialize() {


        //algoritmos
        cboAlgo.items.addAll("BFS", "DFS")
        cboAlgo.selectionModel.selectFirst()

        // cidades
        cboOrig.items.addAll(GraphData.cities)
        cboDest.items.addAll(GraphData.cities)

        //iniciando com cidades de campinas e tatui
        cboOrig.selectionModel.select("Campinas")
        cboDest.selectionModel.select("Tatui")
    }

    @FXML
    private fun onStartClick() {
        val algo = cboAlgo.value
        val start = GraphData.cities.indexOf(cboOrig.value)
        val goal = GraphData.cities.indexOf(cboDest.value)

        val result = when(algo) {
            "BFS" -> AlgoritmBFS().search(start, goal)
            else -> AlgoritmDFS().search(start, goal)
        }

        val sb = StringBuilder()
        sb.appendLine("Algoritmo: $algo")
        sb.appendLine("Origem: ${cboOrig.value}")
        sb.appendLine("Destino: ${cboDest.value}")
        sb.appendLine()
        sb.appendLine("Árvore de busca:")
        result.treeEdges.forEach { (p,f,w) ->
            sb.appendLine("  ${GraphData.cities[p]} -> ${GraphData.cities[f]} [$w]")
        }
        sb.appendLine()
        sb.append("Caminho: ")
        sb.append(result.path.joinToString(" -> ") { GraphData.cities[it] })
        sb.appendLine()
        sb.appendLine("Distância: ${result.pathDistance}")

        output.text = sb.toString()
    }
}
