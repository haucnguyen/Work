def gcd(a, b):
    """Returns the greatest common divisor of a and b.
    Should be implemented using recursion.

    >>> gcd(34, 19)
    1
    >>> gcd(39, 91)
    13
    >>> gcd(20, 30)
    10
    >>> gcd(40, 40)
    40
    """

    if a == 1:
        return 1
    if a == b:
        return a
    elif (b % a == 0 or a % b == 0) and b != a:
        return min(a,b)
    else:
        return gcd(b, a % b)

    # for i in range (1, a+1):
    #         if (a%i == 0) and (b%i == 0):
    #                 gcd = i
    # return gcd

def hailstone(n):
    """Print out the hailstone sequence starting at n, and return the
    number of elements in the sequence.

    >>> a = hailstone(10)
    10
    5
    16
    8
    4
    2
    1
    >>> a
    7
    """
    print(n)
    if n == 1:
        return 1
    elif n % 2 == 0:
        return hailstone(n // 2) + 1
    else:
        return hailstone(n * 3 + 1) + 1
