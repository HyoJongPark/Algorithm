import sys

input = sys.stdin.readline
N, K = map(int, input().split())
numbers = list(map(int, input().split()))
counter = [0] * (max(numbers) + 1)

left, right = 0, 0
result = 0
while right < N:
    if counter[numbers[right]] < K:
        counter[numbers[right]] += 1
        right += 1
    else:
        counter[numbers[left]] -= 1
        left += 1
    result = max(result, right - left)

print(result)
