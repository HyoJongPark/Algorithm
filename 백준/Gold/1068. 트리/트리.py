import sys


def DFS(node: int):
    if -1 in tree[node]:
        return 0

    answer = 0
    if len(tree[node]) == 0 or (len(tree[node]) == 1 and -1 in tree[tree[node][0]]):
        return 1
    else:
        for next_node in tree[node]:
            answer += DFS(next_node)
    return answer


input = sys.stdin.readline
N = int(input())
tree = [[] for _ in range(N)]

parent_node = list(map(int, input().split()))
root = 0
for i, parent_node in enumerate(parent_node):
    if parent_node == -1:
        root = i
    else:
        tree[parent_node].append(i)

tree[int(input())] = [-1]
print(DFS(root))
