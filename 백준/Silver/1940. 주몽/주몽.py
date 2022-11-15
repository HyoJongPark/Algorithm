import sys

input = sys.stdin.readline
N = int(input())
M = int(input())
material = list(map(int, input().split()))
material.sort()

left, right = 0, N - 1
answer = 0
while left < right < N:
    armor = material[left] + material[right]
    if armor == M:
        answer += 1
        left += 1
        right -= 1
    elif armor < M:
        left += 1
    else:
        right -= 1
print(answer)
