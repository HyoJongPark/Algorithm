import sys

input = sys.stdin.readline
N, K = map(int, input().split())

left, right = 0, N // 2 + 1
while left <= right:
    mid = (left + right) // 2
    current_result = (mid + 1) * (N - mid + 1)

    if current_result == K:
        print("YES")
        sys.exit(0)
    elif current_result > K:
        right = mid - 1
    else:
        left = mid + 1

print("NO")
