def solution(n):
    answer = ''
    country = [4, 1, 2]

    while True:
        # 자연수 만 존재 하는지 확인
        if n > 0:
            answer += str(country[n % 3])

        if n // 3 != 0 and n % 3 == 0:
            n = n // 3 - 1
        elif n // 3 != 0:
            n //= 3
        else:
            return "".join(reversed(answer))

    return None