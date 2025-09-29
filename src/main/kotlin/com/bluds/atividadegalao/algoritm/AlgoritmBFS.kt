package com.bluds.atividadegalao.algoritm

import com.bluds.atividadegalao.data.GraphData
import com.bluds.atividadegalao.data.SearchResult
import com.bluds.atividadegalao.data.totalDistance
import java.util.*
// importei os dados e funções do grafo

class AlgoritmBFS {
    // aqui criei a classe que faz a busca em largura (BFS)

    fun search(start: Int, goal: Int): SearchResult {
        // metodo que faz a busca entre um ponto inicial e o final

        val n = GraphData.cities.size
        // peguei quantas cidades tem no grafo

        val visited = BooleanArray(n)
        // criei um array pra marcar as cidades que já foram visitadas

        val parent = IntArray(n) { -1 }
        // criei um array pra guardar quem é o "pai" de cada cidade (pra reconstruir o caminho depois)

        val q: ArrayDeque<Int> = ArrayDeque()
        // criei a fila que o BFS usa

        val treeEdges = mutableListOf<Triple<Int, Int, Int>>()
        // aqui guardo as arestas da árvore de busca (quem ligou com quem e qual peso)

        visited[start] = true
        // marquei a cidade inicial como visitada

        q.add(start)
        // coloquei a cidade inicial na fila

        var found = false
        // variável pra parar quando encontrar o destino

        while (q.isNotEmpty() && !found) {
            // enquanto a fila não estiver vazia e não tiver encontrado ainda

            val u = q.removeFirst()
            // tiro o primeiro da fila (cidade atual)

            for (e in GraphData.neighbors(u).sortedBy { it.w }) {
                // percorro os vizinhos da cidade, ordenando pelo peso (distância menor primeiro)

                val v = e.to
                // v é o vizinho

                if (!visited[v]) {
                    // se ainda não foi visitado

                    visited[v] = true
                    // marco como visitado

                    parent[v] = u
                    // digo que o pai dele é o atual

                    treeEdges.add(Triple(u, v, e.w))
                    // guardo essa aresta na lista

                    if (v == goal) { found = true; break }
                    // se cheguei no destino, já marco que achei e saio

                    q.add(v)
                    // senão, coloco na fila pra visitar depois
                }
            }
        }

        val path = mutableListOf<Int>()
        // lista pra guardar o caminho final

        if (parent[goal] != -1 || start == goal) {
            // se o destino tem um pai ou se o destino é o mesmo que o início

            var cur = goal
            // começo pelo destino

            path.add(cur)
            // coloco ele no caminho

            while (cur != start) {
                // volto até chegar no início

                cur = parent[cur]
                // ando pro pai

                if (cur == -1) break
                // se não tem pai, paro

                path.add(cur)
                // adiciono no caminho
            }
            path.reverse()
            // inverto pra ficar do início até o fim
        }

        val dist = totalDistance(path)
        // calculo a distância total desse caminho

        return SearchResult(treeEdges, path, dist)
        // retorno o resultado da busca (árvore, caminho e distância)
    }
}
