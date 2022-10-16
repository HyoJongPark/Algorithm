import sys

input = sys.stdin.readline
T = int(input())

for _ in range(T):
    N = int(input())
    remember = set(map(int, input().split()))
    m = int(input())
    questions = list(map(int, input().split()))
    for num in questions:
        if num in remember:
            print(1)
        else:
            print(0)
