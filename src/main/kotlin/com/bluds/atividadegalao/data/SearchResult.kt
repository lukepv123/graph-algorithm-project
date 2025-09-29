package com.bluds.atividadegalao.data
// pacote de dados

data class SearchResult(
    val treeEdges: List<Triple<Int, Int, Int>>,
    // arestas da árvore de busca (pai, filho, peso)

    val path: List<Int>,
    // caminho final em lista de índices

    val pathDistance: Int
    // distância total do caminho
)
