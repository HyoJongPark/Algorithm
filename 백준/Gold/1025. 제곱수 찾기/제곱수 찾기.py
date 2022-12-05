import sys
import math

input = sys.stdin.readline
M, N = map(int, input().split())
numbers = [list(map(int, list(input().rstrip()))) for _ in range(M)]

answer = -1
for i in range(M):
    for j in range(N):
        for weight_m in range(-M, M):
            for weight_n in range(-N, N):
                if weight_m == 0 and weight_n == 0:
                    continue
                x, y = i, j
                step, value = 0, ''
                while 0 <= x < M and 0 <= y < N:
                    value += str(numbers[x][y])
                    step += 1

                    value_int = int(''.join(value))
                    value_sqrt = math.sqrt(value_int)
                    if value_sqrt == int(value_sqrt) and value_int > answer:
                        answer = value_int

                    x = i + step * weight_m
                    y = j + step * weight_n
print(answer)
