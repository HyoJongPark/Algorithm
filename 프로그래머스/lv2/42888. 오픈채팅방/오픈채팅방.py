import collections


def solution(record):
    answer = []
    record_commands = []
    record_nicknames = collections.defaultdict(str)

    for i in record:
        tmp = i.split()
        if tmp[0] == 'Enter':
            record_commands.append(tmp[1] + '님이 들어왔습니다.')
        elif tmp[0] == 'Leave':
            record_commands.append(tmp[1] + '님이 나갔습니다.')

        if len(tmp) == 3 and record_nicknames[tmp[1]] != tmp[2]:
            record_nicknames[tmp[1]] = tmp[2]

    for letter in record_commands:
        id = letter.split()[0][:-2] #
        answer.append(record_nicknames[id] + '님이 ' + letter.split()[1])
    return answer