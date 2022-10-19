import sys


def DFS(node: int):
    global count
    check[node] = True
    for next_node in computer[node]:
        if not check[next_node]:
            count += 1
            DFS(next_node)


input = sys.stdin.readline
N = int(input())
M = int(input())

computer = [[] for _ in range(N + 1)]
check = [False for _ in range(N + 1)]
count = 0
for _ in range(M):
    node_a, node_b = map(int, input().split())
    computer[node_a].append(node_b)
    computer[node_b].append(node_a)

DFS(1)
print(count)