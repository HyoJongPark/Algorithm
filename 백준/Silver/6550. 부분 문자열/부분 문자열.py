import sys


def is_substring(S_T):
    S, T = S_T.split()
    idx = 0
    for i in range(len(T)):
        if T[i] == S[idx]:
            idx += 1
            if idx == len(S):
                return "Yes"
    return "No"


input = sys.stdin.readline
while True:
    S_T = input().rstrip()
    if not S_T:
        break

    print(is_substring(S_T))
