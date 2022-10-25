import sys

input = sys.stdin.readline
N = int(input())
liquid = list(map(int, input().split()))
liquid.sort()

left, right = 0, N - 1
min_status_value, answer = sys.maxsize, [0, 0]
while left < right and min_status_value != 0:
    current_status_value = liquid[left] + liquid[right]

    if abs(current_status_value) < min_status_value:
        min_status_value = abs(current_status_value)
        answer[0], answer[1] = liquid[left], liquid[right]

    if current_status_value < 0:
        left += 1
    else:
        right -= 1


print(answer[0], answer[1])
