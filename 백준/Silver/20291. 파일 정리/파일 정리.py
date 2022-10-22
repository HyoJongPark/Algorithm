import collections
import sys

input = sys.stdin.readline
N = int(input())

counter = collections.defaultdict(int)
for _ in range(N):
    extension = input().rstrip().split('.')[1]
    counter[extension] += 1

sorted_keys = sorted(counter)
for key in sorted_keys:
    print(key, counter[key])