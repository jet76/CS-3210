# Project 16 - Practicing Recursive, List-Based Prolog

Here are a couple of fairly simple programs for you to create.

- Write rules to implement a Prolog predicate locate( Target, List, Index ) that means “Target is at position Index in the List,” assuming that List is a list of one or more integers that includes Target. Use 0 for the first position in the list.  
For example, the query  
```locate( 3, [1, 2, 3, 4, 5, 6], Index).```  
should produce ```Index = 2.```

- Write rules to implement a Prolog predicate mix( X, Y, M ) that means “M is a list formed by alternating items from lists X and Y, which are assumed to have the same number of items.”  
For example, the query  
```mix( [1,2,3], [4,5,6], M ).```  
should produce ```M = [1,4,2,5,3,6].```
