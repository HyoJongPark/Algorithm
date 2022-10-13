import sys


def DFS(node: int):
    for current_node in tree[node]:
        if parent_node[current_node] == 0:
            parent_node[current_node] = node
            DFS(current_node)


sys.setrecursionlimit(1000000)  # 1,000,000
input = sys.stdin.readline
N = int(input())
tree = [[] for _ in range(N + 1)]

parent_node = [0 for _ in range(N + 1)]

for _ in range(N - 1):
    start, end = map(int, input().split())
    tree[start].append(end)
    tree[end].append(start)

DFS(1)
for i in range(2, N + 1):
    print(parent_node[i])
