import collections
import sys

input = sys.stdin.readline

trees = collections.defaultdict(int)
total = 0
while True:
    tree = input().rstrip()
    if not tree:
        break
    trees[tree] += 1
    total += 1

tree_keys = trees.keys()
sorted_tree_keys = sorted(tree_keys)
for tree in sorted_tree_keys:
    print(f'{tree} {trees[tree] / total * 100:0.4f}')