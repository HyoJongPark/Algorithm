import sys

input = sys.stdin.readline
N, M = map(int, input().split())
arr = list(map(int, input().split()))

left, right, answer = 0, max(arr), 0
while left <= right:
    mid = (left + right) // 2
    low, high, cnt = arr[0], arr[0], 1

    for i in arr:
        if high < i:
            high = i
        if low > i:
            low = i
        if (high - low) > mid:
            cnt += 1
            low = i
            high = i

    if cnt <= M:
        right = mid - 1
        answer = mid
    else:
        left = mid + 1
print(answer)