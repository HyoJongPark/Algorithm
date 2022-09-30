import sys
import heapq

input = sys.stdin.readline
n = int(input())

q = []
for i in range(n):
    command = int(input().rstrip())
    if command == 0 and not q:
        print(0)
    elif command == 0:
        print(heapq.heappop(q)[1])
    else:
        heapq.heappush(q, (-command, command))
