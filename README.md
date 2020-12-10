# Hobbits
Dr. Goldstein's PA 5: hobbits
***
## Input:
A list of numbers that represent the value of each floating pad.
## Process:
<u>Pseudocode:</u>

Let h be the maximal number of hobbits that can be saved such that <b>0 < h <= maximal pads</b>.

Easy case: the minimal pad is also a maximal pad

For (v in V): 

    If v == minimal && == maximal:
        make_path(v)
## Output:
Each line of output represents the path a hobbit can cross. Thus, the number of lines
determine the maximum number of hobbit(s) that can be saved.
