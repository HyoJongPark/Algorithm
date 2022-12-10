import sys

sys.setrecursionlimit(100000)
input = sys.stdin.readline

N, M = map(int, input().split())
check = [False] * N
relationship = [[] for _ in range(N)]
for _ in range(M):
    a, b = map(int, input().split())
    relationship[a].append(b)
    relationship[b].append(a)


def DFS(current_people, cnt):
    if cnt == 5:
        print(1)
        sys.exit(0)

    check[current_people] = True
    for friend in relationship[current_people]:
        if not check[friend]:
            DFS(friend, cnt + 1)
            check[friend] = False


for i in range(N):
    DFS(i, 1)
    check[i] = False
print(0)
