import sys

input = sys.stdin.readline
s = input().rstrip()
count = {}
for i in range(1, len(s) + 1):
    for j in range(len(s) + 1 - i):
        count[s[j:j+i]] = 1

print(len(count))