import sys

input = sys.stdin.readline
N, M = int(input()), int(input())
friends = [[] for _ in range(N + 1)]
check = [False for _ in range(N + 1)]
for _ in range(M):
    a, b = map(int, input().split())
    friends[a].append(b)
    friends[b].append(a)


def BFS():
    queue = []
    check[1] = True
    for friend in friends[1]:
        queue.append(friend)
        check[friend] = True
    answer = len(queue)
    while queue:
        current_friend = queue.pop(0)
        for next_friend in friends[current_friend]:
            if not check[next_friend]:
                check[next_friend] = True
                answer += 1
    return answer


print(BFS())
