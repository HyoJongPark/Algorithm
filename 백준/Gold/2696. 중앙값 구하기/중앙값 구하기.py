import heapq
import sys


def solution():
    global numbers
    result = [numbers[0]]
    mid_value = numbers[0]
    high = []
    low = []

    for i, value in enumerate(numbers[1:], 1):
        if value > mid_value:
            heapq.heappush(high, value)
        else:
            heapq.heappush(low, -value)

        if i % 2 == 0:
            if len(low) > len(high):
                heapq.heappush(high, mid_value)
                mid_value = -heapq.heappop(low)
            elif len(low) < len(high):
                heapq.heappush(low, -mid_value)
                mid_value = heapq.heappop(high)
            result.append(mid_value)

    print(len(result))
    for i in range(len(result)):
        if i != 0 and i % 10 == 0:
            print()
        print(result[i], end=' ')
    print()


input = sys.stdin.readline
T = int(input())

for _ in range(T):
    N = int(input())
    numbers = []
    if N % 10 == 0:
        for i in range(N // 10):
            numbers.extend(list(map(int, input().split())))
    else:
        for i in range(N // 10 + 1):
            numbers.extend(list(map(int, input().split())))
    solution()
