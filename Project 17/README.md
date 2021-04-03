# Project 17 - A Slightly More Interesting Prolog Program

Your job on this project is to create a text file named p17.pl that contains some Prolog rules such that I can consult your file and be able to enter any list of numbers and have the next number in the sequence (assuming that the given numbers come from evaluation of a polynomial and that there are enough of them—your rules don’t have to check for this) be figured for me by doing something like  
```nextItem( [146, 394, 882, 1730, 3082], N).```  
with Prolog responding  
```N = 5106```  

Your nextItem rules must work by doing a technique that works when the given numbers come from a polynomial, which is to do subtractions successively as shown here for the example sequence given above:
<pre>
146     394     882     1730      3082
    248     488     848      1352
        240     360     504
            120     144
                 24
</pre>

Once a row with only one item is reached, then we know (given the assumptions that the original sequence came from a polynomial and that it contained enough numbers to be fair for the degree of polynomial) that the pattern for that row is constant, so we know the next value in that row. Given the next value in a row, we can add it to the last value in the row above to compute the next value in the row above. For the example, we figure these next values in each row:  
146 394 882 1730 3082
248 488 848 1352
240 360 504
120 144
24 24
168
672
2024
5106

Your p17.pl must only use fundamental Prolog constructs—no built-in predicates other than arithmetic and list syntax (using square brackets, commas, and the vertical bar).

As a possible hint, you might want to give rules for these predicates (in addition to the nextItem predicate)  
```rowBelow( A, B)``` 
meaning that A is a list of numbers and B is the row below it in the chart, and  
```myLast( A, X )```  
meaning that X is the last item in the list A.
