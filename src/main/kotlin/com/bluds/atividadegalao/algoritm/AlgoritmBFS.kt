package com.bluds.atividadegalao.algoritm

import com.bluds.atividadegalao.data.GraphData
import com.bluds.atividadegalao.data.SearchResult
import com.bluds.atividadegalao.data.totalDistance
import java.util.*

class AlgoritmBFS {
    fun search(start: Int, goal: Int): SearchResult {
        val n = GraphData.cities.size
        val visited = BooleanArray(n)
        val parent = IntArray(n) { -1 }
        val q: ArrayDeque<Int> = ArrayDeque()
        val treeEdges = mutableListOf<Triple<Int, Int, Int>>()

        visited[start] = true
        q.add(start)

        var found = false
        while (q.isNotEmpty() && !found) {
            val u = q.removeFirst()
            for (e in GraphData.neighbors(u).sortedBy { it.w }) {
                val v = e.to
                if (!visited[v]) {
                    visited[v] = true
                    parent[v] = u
                    treeEdges.add(Triple(u, v, e.w))
                    if (v == goal) { found = true; break }
                    q.add(v)
                }
            }
        }

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
