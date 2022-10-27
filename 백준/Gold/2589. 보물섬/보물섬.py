import collections
import sys

input = sys.stdin.readline
dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]
N, M = map(int, input().split())
board = [list(input()) for _ in range(N)]


def isValid(next_x, next_y, check):
    return 0 <= next_x < N and 0 <= next_y < M and board[next_x][next_y] == 'L' and not check[next_x][next_y]


def bfs(x, y):
    q = collections.deque()
    q.append((x, y, 1))
    check = [[False for _ in range(M)] for _ in range(N)]
    check[x][y] = True
    cnt = 0
    while q:
        x, y, distance = q.popleft()
        for i in range(4):
            next_x = x + dx[i]
            next_y = y + dy[i]

            if isValid(next_x, next_y, check):
                check[next_x][next_y] = True
                cnt = distance + 1
                q.append((next_x, next_y, distance + 1))
    return cnt - 1


answer = 0
for x in range(N):
    for y in range(M):
        if board[x][y] == 'L':
            answer = max(answer, bfs(x, y))

print(answer)
