import collections
import sys

input = sys.stdin.readline
N = int(input())

tree = collections.defaultdict(list)
for i in range(N):
    root, left, right = input().split()
    tree[root].append(left)
    tree[root].append(right)


def DFS_front(node: str):
    print(node, end='')
    for next_node in tree[node]:
        if next_node != '.':
            DFS_front(next_node)


def DFS_mid(node: str):
    if tree[node][0] != '.':
        DFS_mid(tree[node][0])

    print(node, end='')
    if tree[node][1] != '.':
        DFS_mid(tree[node][1])


def DFS_back(node: str):
    for next_node in tree[node]:
        if next_node != '.':
            DFS_back(next_node)
    print(node, end='')


DFS_front('A')
print()
DFS_mid('A')
print()
DFS_back('A')
