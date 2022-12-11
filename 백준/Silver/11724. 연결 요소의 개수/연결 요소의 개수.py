import sys
sys.setrecursionlimit(10000)

input = sys.stdin.readline
N, M = map(int, input().split())
graph = [[] for _ in range(N + 1)]
check = [False] * (N + 1)
for _ in range(M):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)


def dfs(node):
    check[node] = True
    for next_node in graph[node]:
        if not check[next_node]:
            dfs(next_node)


count = 0
for node in range(1, N + 1):
    if not check[node]:
        dfs(node)
        count += 1
print(count)
