import heapq
import sys


class Problem:
    def __init__(self):
        self.notSolved = {}
        self.problems_low = []
        self.problems_high = []

    def add(self, number: int, level: int):
        self.notSolved[number] = True
        heapq.heappush(self.problems_low, (level, number))
        heapq.heappush(self.problems_high, (-level, -number))

    def recommend(self, command: str) -> int:
        if command == '1':
            while not self.notSolved[- self.problems_high[0][1]]:
                heapq.heappop(self.problems_high)
            return -self.problems_high[0][1]
        elif command == '-1':
            while not self.notSolved[self.problems_low[0][1]]:
                heapq.heappop(self.problems_low)
            return self.problems_low[0][1]

    def solved(self, number: int):
        self.notSolved[number] = False
        while self.problems_low and not self.notSolved[self.problems_low[0][1]]:
            heapq.heappop(self.problems_low)
        while self.problems_high and not self.notSolved[-self.problems_high[0][1]]:
            heapq.heappop(self.problems_high)


input = sys.stdin.readline
problem = Problem()
N = int(input())

for _ in range(N):
    problem_number, problem_level = map(int, input().split())
    problem.add(problem_number, problem_level)

M = int(input())

for _ in range(M):
    command = list(input().split())
    if command[0] == 'recommend':
        print(problem.recommend(command[1]))
    elif command[0] == 'add':
        problem.add(int(command[1]), int(command[2]))
    elif command[0] == 'solved':
        problem.solved(int(command[1]))
