import sys


def find_parent_node(node: int):
    parent_nodes = [node]
    level = 0
    while True:
        if tree[node] == 0:
            break
        level += 1
        parent_nodes.append(tree[node])
        node = tree[node]

    return parent_nodes


def find_nearest_common_anscestor(parent_node_A, parent_node_B):
    for i, node in enumerate(parent_node_A):
        if node in parent_node_B:
            return node


input = sys.stdin.readline
T = int(input())

for _ in range(T):
    N = int(input())
    tree = [0 for i in range(N + 1)]
    for i in range(N - 1):
        parent_node, child_node = map(int, input().split())
        tree[child_node] = parent_node
    child_node_A, child_node_B = map(int, input().split())
    parent_node_A = find_parent_node(child_node_A)
    parent_node_B = find_parent_node(child_node_B)

    print(find_nearest_common_anscestor(parent_node_A, parent_node_B))
