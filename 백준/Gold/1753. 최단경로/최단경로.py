from heapq import heappush, heappop
import sys
input = sys.stdin.readline


V, E = map(int, input().split())
start = int(input())
graph = [[] for _ in range(V+1)]

for _ in range(E):
    u, v, w = map(int, input().split())
    graph[u].append((v, w))

INF = int(1e9)
distance = [INF] * (V+1)

def dijkstra(start):
    heap = []
    heappush(heap, [0, start])
    distance[start] = 0

    while heap:
        weight, point = heappop(heap)
        if distance[point] < weight:
            continue

        for i, j in graph[point]:
            new_weight = weight + j
            if distance[i] > new_weight:
                distance[i] = new_weight
                heappush(heap, [new_weight, i])

dijkstra(start)

for i in range(1, V+1):
    print(distance[i] if distance[i] != INF else 'INF')