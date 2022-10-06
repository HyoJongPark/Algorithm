import sys


def bfs(crr: str) -> None:
    answer.append(int(crr))
    for j in range(0, int(crr[-1])):
        bfs(crr + str(j))


input = sys.stdin.readline
N = int(input().rstrip())
try:
    answer = []
    for i in range(10):
        bfs(str(i))
    answer.sort()
    print(answer[N - 1])
except:
    print(-1)
