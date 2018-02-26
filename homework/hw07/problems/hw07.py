class Fib():
    """A Fibonacci number.

    >>> start = Fib()
    >>> start
    0
    >>> start.next()
    1
    >>> start.next().next()
    1
    >>> start.next().next().next()
    2
    >>> start.next().next().next().next()
    3
    >>> start.next().next().next().next().next()
    5
    >>> start.next().next().next().next().next().next()
    8
    """

    def __init__(self):
        self.value = 0

    def next(self):
        nextFib = Fib()
        nextFib.previous, nextFib.value = 0, 0
        if self.value == 0:
            nextFib.value = 1
            return nextFib
        nextFib.previous = self.value
        nextFib.value = self.previous + self.value
        return nextFib

    def __repr__(self):
        return str(self.value)

class VendingMachine:
    """A vending machine that vends some product for some price.

    >>> v = VendingMachine('candy', 10)
    >>> v.vend()
    'Machine is out of stock.'
    >>> v.restock(2)
    'Current candy stock: 2'
    >>> v.vend()
    'You must deposit $10 more.'
    >>> v.deposit(7)
    'Current balance: $7'
    >>> v.vend()
    'You must deposit $3 more.'
    >>> v.deposit(5)
    'Current balance: $12'
    >>> v.vend()
    'Here is your candy and $2 change.'
    >>> v.deposit(10)
    'Current balance: $10'
    >>> v.vend()
    'Here is your candy.'
    >>> v.deposit(15)
    'Machine is out of stock. Here is your $15.'

    >>> w = VendingMachine('soda', 2)
    >>> w.restock(3)
    'Current soda stock: 3'
    >>> w.deposit(2)
    'Current balance: $2'
    >>> w.vend()
    'Here is your soda.'
    """
    stock = 0
    credit = 0
    remaining_balance = 0


    def __init__(self, merchandise, price):
        self.merchandise = merchandise
        self.price = price
        self.stock = VendingMachine.stock
        self.credit = VendingMachine.credit

    def vend(self):
        if self.stock == 0:
            if self.credit > 0:
                self.credit = 0
                return 'Machine is out of stock. Here is your $' + str(self.credit) + '.'
            return 'Machine is out of stock.'

        self.remaining_balance = abs(self.price - self.credit)

        if self.credit < self.price:
            return 'You must deposit $' + str(self.remaining_balance) + ' more.'

        if self.credit >= self.price:
            self.credit = 0
            self.stock -= 1
            if self.remaining_balance == 0:
                return 'Here is your ' + self.merchandise + '.'
            else:
                return 'Here is your ' + self.merchandise + ' and $' + str(self.remaining_balance) + ' change.'

    def restock(self, quantity):
        self.stock += quantity
        return 'Current ' + self.merchandise + ' stock: ' + str(self.stock)

    def deposit(self, money):
        self.credit += money
        if self.stock == 0:
            string_credit = self.credit
            self.credit = 0
            return 'Machine is out of stock. Here is your $' + str(string_credit) + '.'
        return 'Current balance: $' + str(self.credit)


class MissManners:
    """A container class that only forward messages that say please.

    >>> v = VendingMachine('teaspoon', 10)
    >>> v.restock(2)
    'Current teaspoon stock: 2'

    >>> m = MissManners(v)
    >>> m.ask('vend')
    'You must learn to say please first.'
    >>> m.ask('please vend')
    'You must deposit $10 more.'
    >>> m.ask('please deposit', 20)
    'Current balance: $20'
    >>> m.ask('now will you vend?')
    'You must learn to say please first.'
    >>> m.ask('please hand over a teaspoon')
    'Thanks for asking, but I know not how to hand over a teaspoon.'
    >>> m.ask('please vend')
    'Here is your teaspoon and $10 change.'

    >>> double_fussy = MissManners(m) # Composed MissManners objects
    >>> double_fussy.ask('deposit', 10)
    'You must learn to say please first.'
    >>> double_fussy.ask('please deposit', 10)
    'Thanks for asking, but I know not how to deposit.'
    >>> double_fussy.ask('please please deposit', 10)
    'Thanks for asking, but I know not how to please deposit.'
    >>> double_fussy.ask('please ask', 'please deposit', 10)
    'Current balance: $10'
    """
    def __init__(self, obj):
        self.obj = obj

    def ask(self, message, *args):
        magic_word = 'please '
        if not message.startswith(magic_word):
            return 'You must learn to say please first.'
        else:
            rest = message[7:]
            if hasattr(self.obj, rest):
                return getattr(self.obj, rest)(*args)
            else:
                return "Thanks for asking, but I know not how to " + rest + "."
