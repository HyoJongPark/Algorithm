import sys


def DFS(node: int):
    check[node] = True
    print(node, end=' ')
    for next_node in tree[node]:
        if not check[next_node]:
            DFS(next_node)


def BFS(start_node: int):
    check = [False for _ in range(N + 1)]
    q = []
    q.append(start_node)
    check[start_node] = True

    while q:
        node = q.pop(0)
        print(node, end=' ')
        for next_node in tree[node]:
            if not check[next_node]:
                check[next_node] = True
                q.append(next_node)


input = sys.stdin.readline
N, M, start_node = map(int, input().split())

tree = [[] for _ in range(N + 1)]
check = [False for _ in range(N + 1)]
for _ in range(M):
    node_a, node_b = map(int, input().split())
    tree[node_a].append(node_b)
    tree[node_b].append(node_a)

for i in range(1, N + 1):
    tree[i].sort()

DFS(start_node)
print()
BFS(start_node)
