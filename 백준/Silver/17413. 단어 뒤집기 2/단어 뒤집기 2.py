import sys

word = list(sys.stdin.readline().rstrip())
index = 0
while index < len(word):
    if word[index] == '<':
        while word[index] != '>':
            index += 1
        index += 1
    elif word[index].isalnum():
        start = index
        while index < len(word) and word[index].isalnum():
            index += 1
        reverse_word = word[start:index]
        reverse_word.reverse()
        word[start:index] = reverse_word
    else:
        index += 1

print(''.join(word))
