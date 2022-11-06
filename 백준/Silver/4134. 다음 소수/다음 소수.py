import math
import sys


def is_prime(x):
    if x == 0 or x == 1:
        return False
    for i in range(2, int(math.sqrt(x)) + 1):
        if x % i == 0:
            return False
    return True


input = sys.stdin.readline
T = int(input())
for i in range(T):
    N = int(input())
    while True:
        if is_prime(N):
            print(N)
            break
        else:
            N += 1
