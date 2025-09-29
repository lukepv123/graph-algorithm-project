package com.bluds.atividadegalao.algoritm


import com.bluds.atividadegalao.data.GraphData
import com.bluds.atividadegalao.data.SearchResult
import com.bluds.atividadegalao.data.totalDistance
// importei os dados e funções do grafo

class AlgoritmDFS {
    // essa classe é a busca em profundidade (DFS)

    private var found = false
    // variável pra parar quando achar o destino

    fun search(start: Int, goal: Int): SearchResult {
        // método pra buscar de um ponto até outro

        val n = GraphData.cities.size
        // número de cidades

        val visited = BooleanArray(n)
        // array de visitados

        val parent = IntArray(n) { -1 }
        // array de pais

        val treeEdges = mutableListOf<Triple<Int, Int, Int>>()
        // lista de arestas percorridas

        fun go(u: Int) {
            // função recursiva que faz a DFS

            if (found) return
            // se já achei, não preciso continuar

            visited[u] = true
            // marco atual como visitado

            if (u == goal) { found = true; return }
            // se cheguei no destino, marco e retorno

            for (e in GraphData.neighbors(u).sortedBy { it.w }) {
                // percorro os vizinhos ordenados pelo peso

                if (!visited[e.to]) {
                    // se ainda não foi visitado

                    parent[e.to] = u
                    // marco o pai dele

                    treeEdges.add(Triple(u, e.to, e.w))
                    // guardo a aresta

                    go(e.to)
                    // chamo recursivamente

                    if (found) return
                    // se achei, paro
                }
            }
        }

        go(start)
        // inicio a DFS pela cidade inicial

        val path = mutableListOf<Int>()
        // lista do caminho final

        if (parent[goal] != -1 || start == goal) {
            // reconstruo o caminho se existe

            var cur = goal
            path.add(cur)

            while (cur != start) {
                cur = parent[cur]
                if (cur == -1) break
                path.add(cur)
            }
            path.reverse()
        }

        val dist = totalDistance(path)
        // calculo a distância

        return SearchResult(treeEdges, path, dist)
        // retorno o resultado
    }
}
