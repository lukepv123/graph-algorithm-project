package com.bluds.atividadegalao.data

data class SearchResult(
    val treeEdges: List<Triple<Int, Int, Int>>,
    val path: List<Int>,
    val pathDistance: Int
)