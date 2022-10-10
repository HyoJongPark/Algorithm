import sys

input = sys.stdin.readline
N, M = map(int, input().split())
trees = list(map(int, input().split()))

left, right = 0, max(trees)
while left <= right:
    mid = (left + right) // 2
    current_length = 0
    for tree in trees:
        if tree > mid:
            current_length += tree - mid

    if current_length >= M:
        left = mid + 1
    else:
        right = mid - 1

print(right)