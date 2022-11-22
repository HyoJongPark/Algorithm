import sys

N = int(sys.stdin.readline())
left, right = 0, N
while left <= right:
    mid = (left + right) // 2
    if mid ** 2 >= N:
        right = mid - 1
    else:
        left = mid + 1

print(left)