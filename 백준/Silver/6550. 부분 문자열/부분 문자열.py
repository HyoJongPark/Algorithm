import sys


def is_substring(S, T):
    idx = 0
    for i in range(len(T)):
        if T[i] == S[idx]:
            idx += 1
            if idx == len(S):
                return "Yes"
    return "No"


input = sys.stdin.readline
while True:
    try:
        S, T = input().rstrip().split()
        print(is_substring(S, T))
    except:
        break
