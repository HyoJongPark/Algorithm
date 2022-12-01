from itertools import combinations
import sys


input = sys.stdin.readline
N = int(input())
materials = [list(map(int,input().split())) for _ in range(N)]

result = sys.maxsize
for cmbs in [combinations(materials, i) for i in range(1, N+1)]:
    for c in cmbs:
        S, B=1, 0     
        for s, b in c:
            S *= s
            B += b 
        result = min(result, abs(S - B))
print(result)