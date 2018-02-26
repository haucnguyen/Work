def multiple(a, b):
    """Return the smallest number n that is a multiple of both a and b.

    >>> multiple(3, 4)
    12
    >>> multiple(14, 21)
    42
    """
    "*** YOUR CODE HERE ***"

    for i in range (1, a+1):
            if (a%i == 0) and (b%i == 0):
                    gcd = i
    return (a*b)//gcd


def unique_digits(n):
    """Return the number of unique digits in positive integer n

    >>> unique_digits(8675309) # All are unique
    7
    >>> unique_digits(1313131) # 1 and 3
    2
    >>> unique_digits(13173131) # 1, 3, and 7
    3
    >>> unique_digits(10000) # 0 and 1
    2
    >>> unique_digits(101) # 0 and 1
    2
    >>> unique_digits(10) # 0 and 1
    2
    """
    "*** YOUR CODE HERE ***"

    value = 0
    counter = 0
    while value < 10:
        if has_digit(n, value):
            value += 1
            counter +=1
        else:
            value += 1
    return counter

def has_digit(n, k):
    """Determines whether a number n has digit k
	    >>> has_digit(5, 5)
	    True
		>>> has_digit(5, 6)
	    False
	    >>> has_digit(1257, 5)
	    True
	    >>> >>> has_digit(1234, 6)
	    False
        """
    while n > 0:
        last_digit = n % 10
        if last_digit == k:
            return True
        n //= 10
    return False
