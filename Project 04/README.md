# Project 4

Your task on this project is to write a VPL program that will compute *n* choose *k* efficiently, as detailed below.

Recall that *n* choose *k* is the number of ways to choose *k8 things out of *n*, and is the *k*<sup>th</sup> item in the *n*<sup>t</sup>h row of Pascal’s Triangle, which is often drawn like this:
<pre>
               1
            1     1
         1     2     1
      1     3     3    1
    1    4     6     4   1
  1   5    10    10    5   1
1   6   15    20    15   6   1
</pre>

A simple recursive algorithm can be based on the fact that each inner number in Pascal’s Triangle is the sum of its two neighbors above and the outer values are all 1. These facts lead to code like this:

```
int pasTri( int n, int k ) {  
  if ( n==0 )  
    return 1;  
  else if ( n==k )  
    return 1;  
   else  
  return pasTri( n-1, k-1 ) + pasTri( n-1, k );  
}  
```

Actually, the test program test6 in the VPL/Tests folder implements these ideas. This program works great, but it takes way too long for larger numbers. For example, try it on the inputs 30 and 15 (this took about 42 seconds to run on my computer).

To create a much more efficient VPL program, first organize Pascal’s Triangle to make it more obvious how it fits into a 2D array:
<table>
  <tr align="center">
    <td></td>
    <td>0</td>
    <td>1</td>
    <td>2</td>
    <td>3</td>
    <td>4</td>
    <td>5</td>
    <td>6</td>
  </tr>
  <tr align="center">
    <td>0</td>
    <td>1</td>
    <td>-</td>
    <td>-</td>
    <td>-</td>
    <td>-</td>
    <td>-</td>
    <td>-</td>
  </tr>
  <tr align="center">
    <td>1</td>
    <td>1</td>
    <td>1</td>
    <td>-</td>
    <td>-</td>
    <td>-</td>
    <td>-</td>
    <td>-</td>
  </tr>
  <tr align="center">
    <td>2</td>
    <td>1</td>
    <td>2</td>
    <td>1</td>
    <td>-</td>
    <td>-</td>
    <td>-</td>
    <td>-</td>
  </tr>
  <tr align="center">
    <td>3</td>
    <td>1</td>
    <td>3</td>
    <td>3</td>
    <td>1</td>
    <td>-</td>
    <td>-</td>
    <td>-</td>
  </tr>
  <tr align="center">
    <td>4</td>
    <td>1</td>
    <td>4</td>
    <td>6</td>
    <td>4</td>
    <td>1</td>
    <td>-</td>
    <td>-</td>
  </tr>
  <tr align="center">
    <td>5</td>
    <td>1</td>
    <td>5</td>
    <td>10</td>
    <td>10</td>
    <td>5</td>
    <td>1</td>
    <td>-</td>
  </tr>
  <tr align="center">
    <td>6</td>
    <td>1</td>
    <td>6</td>
    <td>15</td>
    <td>20</td>
    <td>15</td>
    <td>6</td>
    <td>1</td>
  </tr>
</table>


Write a VPL program in a file named project4.vpl that efficiently computes any requested value *n* choose *k* (up to *n = 50*—much beyond that and the numbers in Pascal’s Triangle become too large to be represented as int values) very quickly.

The idea for this efficient algorithm is simply to simulate a 2D array in VPL, and to fill in values in that array until the desired value is reached.

Note that to compute 50 choose 50 will require a 51 by 51 matrix, which only takes 51<sup>2</sup> = 2601 memory cells, which is quite feasible (so you don’t need to worry about storing Pascal’s Triangle cleverly so as to avoid the wasted space for the unused cells).

You will probably want to create subprograms that store and retrieve values to and from a simulated 2D array.

You don’t need to error-check the user’s input values for *n* and *k*.

If you don’t like the limit on the size of *n*, feel free to modify your VPL implementation to use BigInteger objects everywhere instead of int values, and then the only limit will be the time it takes to fill in the 2D array.

When you are happy with your work, email with your single file named project4.vpl attached.
