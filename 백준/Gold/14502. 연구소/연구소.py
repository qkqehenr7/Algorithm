from collections import deque
import sys
input = sys.stdin.readline


dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def bfs():
    queue = deque()
    graph = [arr[:] for arr in graph_tmp]
    for i in range(n):
        for j in range(m):
            if graph[i][j] == 2:
                queue.append((i,j))

    while queue:
        x, y = queue.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0<= nx < n and 0 <= ny < m:
                if graph[nx][ny] == 0:
                    graph[nx][ny] = 2
                    queue.append((nx, ny))

    global result
    count = 0
    for i in range(n):
        for j in range(m):
            if graph[i][j] == 0:
                count += 1
    result = max(result, count)

def wall(count):
    if count == 3:
        bfs()
        return
    for i in range(n):
        for j in range(m):
            if graph_tmp[i][j] == 0:
                graph_tmp[i][j] = 1
                wall(count + 1)
                graph_tmp[i][j] = 0

n, m = map(int, input().split())
graph_tmp = [list(map(int, input().split())) for _ in range(n)]

result = 0
wall(0)
print(result)