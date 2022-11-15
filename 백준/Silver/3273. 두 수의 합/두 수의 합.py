import sys

input = sys.stdin.readline
N = int(input())
nums = list(map(int, input().split()))
M = int(input())
nums.sort()

left, right = 0, N - 1
answer = 0
while left < right:
    sum_of_nums = nums[left] + nums[right]
    if sum_of_nums == M:
        answer += 1

    if sum_of_nums <= M:
        left += 1
    else:
        right -= 1
print(answer)
