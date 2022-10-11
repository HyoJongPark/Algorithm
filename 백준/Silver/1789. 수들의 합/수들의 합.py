import sys

input = sys.stdin.readline
N, S = 0, int(input())
sum = 0

for i in range(1, S + 1):
    sum += i
    N += 1
    if sum > S:
        N -= 1
        break

print(N)