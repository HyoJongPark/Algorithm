import sys

input = sys.stdin.readline
T = int(input())


def DFS(level, sum_of_status):
    if level == 11:
        global answer
        answer = max(sum_of_status, answer)
        return

    for i, status in enumerate(board[level]):
        if status and not check[i]:
            check[i] = 1
            DFS(level + 1, sum_of_status + status)
            check[i] = False


for _ in range(T):
    board = [list(map(int, input().split())) for _ in range(11)]
    check = [0 for _ in range(11)]
    answer = 0
    DFS(0, 0)
    print(answer)
