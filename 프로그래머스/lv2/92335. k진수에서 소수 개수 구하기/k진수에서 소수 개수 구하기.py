def to_k_number(n, k):
    ret = ""
    while n > 0:
        ret += str(n % k)
        n //= k
    return ''.join(reversed(ret))

def is_prime_number(k):
    if k == 2 or k == 3:
        return True
    if k % 2 == 0 or k < 2:
        return False
    
    for i in range(3, int(k ** 0.5) + 1, 2):
        if k % i == 0:
            return False
    return True
    
def solution(n, k):
    answer = 0
    k_num = to_k_number(n, k)
    
    for num in k_num.split('0'):
        if num == "":
            continue
        if is_prime_number(int(num)):
            answer += 1
            
    
    return answer