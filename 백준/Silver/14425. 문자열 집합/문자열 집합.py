import collections
import sys


input = sys.stdin.readline
n, m = map(int, input().split())
cnt, strs = 0, collections.defaultdict(int)

for i in range(n):
    index = input().strip()
    strs[index] = 1

for j in range(m):
    if input().strip() in strs.keys():
        cnt += 1
        
print(cnt)
