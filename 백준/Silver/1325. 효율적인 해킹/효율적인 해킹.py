import collections
import sys

input = sys.stdin.readline
N, M = map(int, input().split())
tree = [[] for _ in range(N + 1)]
for _ in range(M):
    child, parent = map(int, input().split())
    tree[parent].append(child)


def BFS(node: int):
    q, check = collections.deque(), [False for _ in range(N + 1)]
    cnt = 0
    q.append(node)
    check[node] = True

    while q:
        current_node = q.popleft()
        for next_node in tree[current_node]:
            if not check[next_node]:
                check[next_node] = True
                cnt += 1
                q.append(next_node)
    return cnt


max_cnt = 0
result = []
for node in range(1, N + 1):
    cnt = BFS(node)
    if cnt > max_cnt:
        max_cnt = cnt
        result = [node]
    elif cnt == max_cnt:
        result.append(node)

for node in result:
    print(node, end=' ')