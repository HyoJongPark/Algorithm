import sys
from itertools import product

input = sys.stdin.readline
A, B = map(int, input().split())
x = len(str(A))
y = len(str(B))

cnt = 0
for i in range(x, y + 1):
    nums = list(product([4, 7], repeat=i))
    for num in nums:
        n = int(''.join(map(str, num)))
        if A <= n <= B:
            cnt += 1

print(cnt)
