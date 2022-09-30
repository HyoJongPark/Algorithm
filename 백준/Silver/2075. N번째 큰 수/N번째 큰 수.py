import sys
import heapq

input = sys.stdin.readline
N = int(input())

q = []
numbers = list(map(int, input().split()))
for i in range(N):
    heapq.heappush(q, numbers[i])

for _ in range(N - 1):
    numbers = list(map(int, input().split()))
    for i in numbers:
        if i > q[0]:
            heapq.heappush(q, i)
            heapq.heappop(q)

print(q[0])
