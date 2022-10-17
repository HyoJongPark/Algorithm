import sys


class Point:
    def __init__(self, x, y, distance):
        self.x = x
        self.y = y
        self.distance = distance


input = sys.stdin.readline
N, M, T = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]
check = [[False for _ in range(M)] for _ in range(N)]
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def isValid(next_x, next_y, distance):
    return 0 <= next_x < N and 0 <= next_y < M and not check[next_x][next_y] and distance <= T


def canGo(next_x, next_y):
    return board[next_x][next_y] != 1


def BFS():
    if N == 1 and M == 1:
        return 0
    q = []
    gram = -1

    if board[0][0] == 2:
        gram = N - 1 + M - 1
    q.append(Point(0, 0, 0))
    check[0][0] = True
    while len(q) != 0:
        point = q.pop(0)
        for i in range(4):
            next_x = point.x + dx[i]
            next_y = point.y + dy[i]

            if next_x == N - 1 and next_y == M - 1:
                return min(gram, point.distance + 1) if gram != -1 else point.distance + 1
            if isValid(next_x, next_y, point.distance) and canGo(next_x, next_y):
                if board[next_x][next_y] == 2:
                    gram = N - 1 - next_x + M - 1 - next_y + point.distance + 1
                q.append(Point(next_x, next_y, point.distance + 1))
                check[next_x][next_y] = True
    return gram


distance = BFS()
if distance == -1 or distance > T:
    print("Fail")
else:
    print(distance)
