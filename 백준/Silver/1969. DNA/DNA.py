import sys

input = sys.stdin.readline
N, M = map(int, input().rstrip().split())
DNA = [input() for _ in range(N)]

answer, distance = '', 0
for i in range(M):
    hamming_distance = {}
    for j in range(N):
        if DNA[j][i] not in hamming_distance:
            hamming_distance[DNA[j][i]] = 1
        else:
            hamming_distance[DNA[j][i]] += 1

    result = list(hamming_distance.items())
    result.sort(key=lambda x: (-x[1], x[0]))
    answer += result[0][0]
    distance += N - result[0][1]

print(answer)
print(distance)
