package com.bluds.atividadegalao.algoritm

import com.bluds.atividadegalao.data.GraphData
import com.bluds.atividadegalao.data.SearchResult
import com.bluds.atividadegalao.data.totalDistance

class AlgoritmDFS {
    private var found = false

    fun search(start: Int, goal: Int): SearchResult {
        val n = GraphData.cities.size
        val visited = BooleanArray(n)
        val parent = IntArray(n) { -1 }
        val treeEdges = mutableListOf<Triple<Int, Int, Int>>()

        fun go(u: Int) {
            if (found) return
            visited[u] = true
            if (u == goal) { found = true; return }
            for (e in GraphData.neighbors(u).sortedBy { it.w }) {
                if (!visited[e.to]) {
                    parent[e.to] = u
                    treeEdges.add(Triple(u, e.to, e.w))
                    go(e.to)
                    if (found) return
                }
            }
        }

        go(start)

        val path = mutableListOf<Int>()
        if (parent[goal] != -1 || start == goal) {
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
        return SearchResult(treeEdges, path, dist)
    }
}
