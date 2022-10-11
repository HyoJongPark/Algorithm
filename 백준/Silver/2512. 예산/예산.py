import sys

input = sys.stdin.readline
N = int(input())
budget = list(map(int, input().split()))
total_budget = int(input())

left, right = 0, max(budget)
while left <= right:
    mid = (left + right) // 2
    current_budget = 0
    for b in budget:
        if b >= mid:
            current_budget += mid
        else:
            current_budget += b

    if current_budget <= total_budget:
        left = mid + 1
    else:
        right = mid - 1

print(right)