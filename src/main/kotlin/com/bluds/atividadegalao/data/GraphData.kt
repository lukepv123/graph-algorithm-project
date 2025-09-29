package com.bluds.atividadegalao.data
// pacote de dados

data class Edge(val to: Int, val w: Int)
// uma aresta tem destino (to) e peso (w = distância)

object GraphData {
    // aqui deixei os dados do grafo (as cidades e distâncias)

    val cities = listOf(
        "Piracicaba", "Americana", "Paulinia", "Sumare", "Monte Mor",
        "Campinas", "Indaiatuba", "Capivari", "Salto", "Itu",
        "Sorocaba", "Boituva", "Tatui", "Tiete", "Porto Feliz"
    )
    // lista de cidades

    private val M: Array<Array<Int?>> = arrayOf(
        // matriz de adjacência: null = sem conexão, número = distância

        arrayOf(0,30,null,null,null,null,null,32,null,null,null,null,null,35,null),
        arrayOf(30,0,22,18,null,null,null,null,null,null,null,null,null,null,null),
        arrayOf(null,22,0,null,null,25,null,null,null,null,null,null,null,null,null),
        arrayOf(null,18,null,0,null,23,null,null,null,null,null,null,null,null,null),
        arrayOf(null,null,null,null,0,22,null,15,null,null,null,null,null,null,null),
        arrayOf(null,null,25,23,22,0,20,null,null,null,null,null,null,null,null),
        arrayOf(null,null,null,null,null,20,0,null,20,null,null,null,null,null,null),
        arrayOf(32,null,null,null,15,null,null,0,25,null,null,null,null,30,null),
        arrayOf(null,null,null,null,null,null,20,25,0,10,null,null,null,null,null),
        arrayOf(null,null,null,null,null,null,null,null,10,0,8,null,null,null,12),
        arrayOf(null,null,null,null,null,null,null,null,null,8,0,23,null,null,null),
        arrayOf(null,null,null,null,null,null,null,null,null,null,23,0,17,null,12),
        arrayOf(null,null,null,null,null,null,null,null,null,null,null,17,0,25,null),
        arrayOf(35,null,null,null,null,null,null,30,null,null,null,null,25,0,30),
        arrayOf(null,null,null,null,null,null,null,null,null,12,null,12,null,30,0)
    )

    fun weight(u: Int, v: Int): Int? = M[u][v] ?: M[v][u]
    // retorna o peso entre duas cidades (pode estar em u->v ou v->u)

    fun neighbors(u: Int): List<Edge> {
        // retorna todos vizinhos da cidade u

        val res = mutableListOf<Edge>()
        for (v in cities.indices) {
            val w = weight(u, v)
            if (w != null && u != v) res.add(Edge(v, w))
        }
        return res
    }
}

fun totalDistance(path: List<Int>): Int {
    // calcula a distância total de um caminho

    if (path.size < 2) return 0
    var sum = 0
    for (i in 0 until path.lastIndex) {
        val w = GraphData.weight(path[i], path[i+1])
        sum += w ?: 0
    }
    return sum
}
