import sys

input = sys.stdin.readline
N = int(input())

left, right = 1, 2
tmp, answer = 3, 1
while left < right:
    if tmp == N:
        answer += 1

    if tmp >= N:
        tmp -= left
        left += 1
    else:
        right += 1
        tmp += right
print(answer)
