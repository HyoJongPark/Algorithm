import heapq
import sys

input = sys.stdin.readline
N = int(input())

notSolved = {}
problems_low = []
problems_high = []
for _ in range(N):
    problem_number, problem_level = map(int, input().split())
    notSolved[problem_number] = True
    heapq.heappush(problems_low, (problem_level, problem_number))
    heapq.heappush(problems_high, (-problem_level, -problem_number))

M = int(input())
for _ in range(M):
    command = list(input().split())
    if command[0] == 'recommend':
        if command[1] == '1':
            while not notSolved[- problems_high[0][1]]:
                heapq.heappop(problems_high)
            print(- problems_high[0][1])
        elif command[1] == '-1':
            while not notSolved[problems_low[0][1]]:
                heapq.heappop(problems_low)
            print(problems_low[0][1])
    elif command[0] == 'add':
        notSolved[int(command[1])] = True
        heapq.heappush(problems_low, (int(command[2]), int(command[1])))
        heapq.heappush(problems_high, (-int(command[2]), -int(command[1])))
    elif command[0] == 'solved':
        notSolved[int(command[1])] = False
        while problems_low and not notSolved[problems_low[0][1]]:
            heapq.heappop(problems_low)
        while problems_high and not notSolved[-problems_high[0][1]]:
            heapq.heappop(problems_high)


