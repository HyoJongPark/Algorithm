import sys

input = sys.stdin.readline
N, M = map(int, input().split())
nums = list(map(int, input().split()))

left, right = 0, 1
answer = nums.count(M)
while left < right < N:
    sum_of_nums = sum(nums[left:right + 1])
    if sum_of_nums == M:
        answer += 1
        left += 1
        right += 1
    elif sum_of_nums > M:
        right += 1 if left == right - 1 else 0
        left += 1
    else:
        right += 1
print(answer)
