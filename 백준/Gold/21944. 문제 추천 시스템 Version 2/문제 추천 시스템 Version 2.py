import collections
import heapq
import sys


class Problem:
    def __init__(self):
        self.level = [0 for _ in range(100001)]
        self.classification = [0 for _ in range(100001)]
        self.hard = []
        self.easy = []
        self.hard_classification = [[(0, 0, 0)] for _ in range(101)]
        self.easy_classification = [[(0, 0, 0)] for _ in range(101)]
        self.hard_level = [[(0, 0)] for _ in range(101)]
        self.easy_level = [[(0, 0)] for _ in range(101)]
        self.notSolved = collections.defaultdict(bool)
        self.notSolved[0] = False

    def recommend(self, classification: int, x: str) -> int:
        if x == '1':
            while not self.notSolved[-self.hard_classification[classification][0][1]] or \
                    self.level[-self.hard_classification[classification][0][1]] != - \
                    self.hard_classification[classification][0][0] or \
                    self.classification[-self.hard_classification[classification][0][1]] != classification:
                heapq.heappop(self.hard_classification[classification])

            return -self.hard_classification[classification][0][1]
        elif x == '-1':
            while not self.notSolved[self.easy_classification[classification][0][1]] or \
                    self.level[self.easy_classification[classification][0][1]] != \
                    self.easy_classification[classification][0][0] or \
                    self.classification[self.easy_classification[classification][0][1]] != classification:
                heapq.heappop(self.easy_classification[classification])
            return self.easy_classification[classification][0][1]

    def recommend2(self, x: str) -> int:
        if x == '1':
            while not self.notSolved[-self.hard[0][1]] or self.level[-self.hard[0][1]] != -self.hard[0][0] or \
                    self.classification[-self.hard[0][1]] != self.hard[0][2]:
                heapq.heappop(self.hard)
            return -self.hard[0][1]
        elif x == '-1':
            while not self.notSolved[self.easy[0][1]] or self.level[self.easy[0][1]] != self.easy[0][0] or \
                    self.classification[self.easy[0][1]] != self.easy[0][2]:
                heapq.heappop(self.easy)
            return self.easy[0][1]

    def recommend3(self, level: int, x: str) -> int:
        if x == '1':
            for l in range(level, 101):
                while self.easy_level[l] and (
                        not self.notSolved[self.easy_level[l][0][0]] or self.level[self.easy_level[l][0][0]] != l or
                        self.classification[self.easy_level[l][0][0]] != self.easy_level[l][0][1]):
                    heapq.heappop(self.easy_level[l])
                if len(self.easy_level[l]) == 0:
                    continue
                else:
                    return self.easy_level[l][0][0]
            return -1

        elif x == '-1':
            for l in range(level - 1, -1, -1):
                while self.hard_level[l] and (
                        not self.notSolved[-self.hard_level[l][0][0]] or self.level[-self.hard_level[l][0][0]] != l or
                        self.classification[-self.hard_level[l][0][0]] != self.hard_level[l][0][1]):
                    heapq.heappop(self.hard_level[l])
                if len(self.hard_level[l]) == 0:
                    continue
                else:
                    return -self.hard_level[l][0][0]
            return -1

    def add(self, problem_number: int, classification: int, level: int) -> None:
        heapq.heappush(self.hard, (-level, -problem_number, classification))
        heapq.heappush(self.easy, (level, problem_number, classification))
        heapq.heappush(self.hard_classification[classification], (-level, -problem_number, classification))
        heapq.heappush(self.easy_classification[classification], (level, problem_number, classification))
        heapq.heappush(self.hard_level[level], (-problem_number, classification))
        heapq.heappush(self.easy_level[level], (problem_number, classification))
        self.level[problem_number] = level
        self.classification[problem_number] = classification
        self.notSolved[problem_number] = True

    def solved(self, problem_number: int) -> None:
        self.notSolved[problem_number] = False
        self.level[problem_number] = 0
        self.classification[problem_number] = 0
        # for classification in range(1, 101):
        #     while self.hard_classification[classification] \
        #             and not self.notSolved[-self.hard_classification[classification][0][1]]:
        #         heapq.heappop(self.hard_classification[classification])
        #
        # for classification in range(1, 101):
        #     while self.easy_classification[classification] \
        #             and not self.notSolved[self.easy_classification[classification][0][1]]:
        #         heapq.heappop(self.easy_classification[classification])
        #
        # for l in range(1, 101):
        #     while self.hard_level[l] and not self.notSolved[-self.hard_level[l][0][0]]:
        #         heapq.heappop(self.hard_level[l])
        #
        # for l in range(1, 101):
        #     while self.easy_level[l] and not self.notSolved[self.easy_level[l][0][0]]:
        #         heapq.heappop(self.easy_level[l])


input = sys.stdin.readline
problem = Problem()
N = int(input())

for _ in range(N):
    problem_number, problem_level, problem_classification = map(int, input().split())
    problem.add(problem_number, problem_classification, problem_level)

M = int(input())
for _ in range(M):
    command = list(input().split())
    if command[0] == 'recommend':
        print(problem.recommend(int(command[1]), command[2]))
    elif command[0] == 'recommend2':
        print(problem.recommend2(command[1]))
    elif command[0] == 'recommend3':
        print(problem.recommend3(int(command[2]), command[1]))
    elif command[0] == 'add':
        problem.add(int(command[1]), int(command[3]), int(command[2]))
    elif command[0] == 'solved':
        problem.solved(int(command[1]))
