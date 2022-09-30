import sys


input = lambda: sys.stdin.readline().rstrip()
N, M = map(int, input().split())

pokemon_by_name = {}
pokemon_by_number = {}
for i in range(1, N+1):
    current_pokemon = input()
    pokemon_by_name[current_pokemon] = i
    pokemon_by_number[i] = current_pokemon

for j in range(M):
    name_or_number = input()
    if name_or_number.isdigit():
        print(pokemon_by_number[int(name_or_number)])
    else:
        print(pokemon_by_name[name_or_number])