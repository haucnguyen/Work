"""
This is a minimal contest submission file. You may also submit the full
hog.py from Project 1 as your contest entry.

Only this file will be submitted. Make sure to include any helper functions
from `hog.py` that you'll need here! For example, if you have a function to
calculate Free Bacon points, you should make sure it's added to this file
as well.
"""

TEAM_NAME = 'somethinglol'

def free_bacon(opponent_score):
    high_score = (opponent_score % 10, opponent_score // 10)
    return max (high_score) + 1

def is_prime(k):
    """prime or no"""
    x = 2
    if k == 1:
        return False
    while x < k:
        if k % x == 0:
            return False
        x += 1
    return True

def next_prime(nextn_prime):
    """"next prime number"""
    nextn_prime += 1
    while is_prime(nextn_prime) == False:
        nextn_prime += 1
    return nextn_prime


def hogtimus_prime(player_score):
    """if roll prime number, gets next prime number"""
    if is_prime(player_score):
        return next_prime(player_score)
    else:
        return player_score

def bacon_strategy(score, opponent_score, margin=8, num_rolls=4):
    if margin <= hogtimus_prime(free_bacon(opponent_score)):
        return 0
    return num_rolls


def swap_strategy(score, opponent_score, margin=8, num_rolls=4):
    if margin <= free_bacon(opponent_score) or margin <= hogtimus_prime(free_bacon(opponent_score)):
        return 0
    return num_rolls


def final_strategy(score, opponent_score):
    margin = 5
    num_rolls = 5
    if score == 0:
        return -1
    return swap_strategy(score, opponent_score, margin, num_rolls)

    # x = max(opponent_score // 10, opponent_score % 10)
    # if score < opponent_score:
    #     if score + x == opponent_score // 2:
    #         return 0
    #     elif score + opponent_score % 7 == 0:
    #         return 10
    #     elif score + 1 == opponent_score // 2:
    #         return -1
    #     elif is_prime(score + 1):
    #         return 1
    #     else:
    #         return swap_strategy(score, opponent_score)
    # if score > opponent_score:
    #     if score + 1 == opponent_score * 2:
    #         return free_bacon(opponent_score)
    #     elif score + opponent_score % 7 == 0:
    #         return 10
    #     return bacon_strategy(score, opponent_score)
    # return 6
