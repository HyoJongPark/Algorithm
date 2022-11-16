import sys

input = sys.stdin.readline
N, K = map(int, input().split())
S = list(map(int, input().split()))

cnt, tmp, answer = 0, 0, 0
right = 0
for left in range(N):
    while cnt <= K and right < N:
        if S[right] % 2 != 0:
            if cnt == K:
                break
            cnt += 1
        tmp += 1
        right += 1

    answer = max(answer, tmp - cnt)
    cnt -= S[left] % 2
    tmp -= 1
print(answer)
