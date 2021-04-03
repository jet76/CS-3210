# Project 13 - Simulating memory

Suppose you want to simulate memory in the purely functional subset of Racket, as in an imperative language, by using a list of pairs, where the first thing in each pair is a variable name and the second thing is its current numeric value. For example, the list ```((x 3) (y 7) (z -2) (a 11) (b 0))```  
simulates memory storing values for variables *x*, *y*, *z*, *a*, and *b*.

- Create the function (store value var table) that returns a modified memory table that has var with its value changed to value, if var is already in table, and otherwise adds the pair (var value) to table.
- Create the function (retrieve var table) that returns the value stored for var in the table (assume that a single pair for var is in fact in table).
- Create the function (assign x y table ) that simulates the assignment statement x = y in an imperative language by return the table that has table with the value stored for x replaced by the current value stored for y in table.

Here are some sample expression evaluations (order of pairs may be different for your functions):
```console
> (store 37 ’y ’( (x 5) (y 2) (z 7) ) )
( (x 5) (y 37) (z 7) )
```
```console
> (store 13 ’w ’( (x 5) (y 2) (z 7) ) )
( (x 5) (y 2) (z 7) (w 13) )
```
```console
> (retrieve ’y ’( (x 5) (y 37) (z 7) ) )
37
```
```console
> (assign ’x ’y ’( (x 3) (y 7) (z -2) (a 11) (b 0) ) )
( (x 7) (y 7) (z -2) (a 11) (b 0) )
```
