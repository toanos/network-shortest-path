# Hobbits
Dr. Goldstein's PA 5: hobbits
***
# Objective:
1. There are a certain amount of maximal and minimal pads for Hobbits to reach to the other side. Either one of those pads run out, GAME OVER!
2. Use Dijkstra's algorithm to help Frodo finds efficient paths.
3. Definition of efficient: Find the shortest path that has the smallest traffic of traversal.
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
