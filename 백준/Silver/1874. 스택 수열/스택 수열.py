import sys

input = sys.stdin.readline
N = int(input())
current_value = 1
stack = []
answer = []

for i in range(N):
    target = int(input())
    while current_value <= target:
        stack.append(current_value)
        answer.append("+")
        current_value += 1

    if stack[-1] == target:
        stack.pop()
        answer.append("-")
    else:
        print("NO")
        answer = []
        break

for i in answer:
    print(i)