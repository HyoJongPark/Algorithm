import sys

input = sys.stdin.readline
N, X = map(int, input().split())
blog = list(map(int, input().split()))

tmp_value = sum(blog[:X])
max_value = tmp_value
count = 1
for i in range(X, N):
    tmp_value -= blog[i - X]
    tmp_value += blog[i]

    if tmp_value > max_value:
        max_value = tmp_value
        count = 1
    elif tmp_value == max_value:
        count += 1

if max_value == 0:
    print("SAD")
else:
    print(f"{max_value}\n{count}")