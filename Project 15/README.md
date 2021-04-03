# Project 15 - Euclidean Traveling Salesperson Problem

The overall purpose of the work on this Project is to produce a Racket function (with no inappropriate imperative language features used) that will solve the Euclidean traveling salesperson problem (ETSP).

Note that this Project is of course intended to give you further practice with and appreciation of Lisp, so please don’t waste your time looking on-line for someone else’s solution to this problem!

## Definition of ETSP

Given a list of *n* vertices in the plane, named *v<sub>j</sub> = (x<sub>j</sub> , y<sub>j<sub>)* for *j = 1, ..., n*, find a *tour* with the smallest length, where a tour starts at *v<sub>1</sub>*, goes from vertex to vertex, visiting each vertex exactly once, and returns to *v<sub>1</sub>*. The length of a tour is the sum of the Euclidean (ordinary, everyday geometry) lengths of all the edges in the tour.

For example, suppose we have these six points: *v<sub>1</sub> = (3, 1), v<sub>2</sub> = (8, 2), v<sub>3</sub> = (5, 4), v<sub>4</sub> = (2, 6), v<sub>5</sub> = (6, 6), and v<sub>6</sub> = (4, 8)*.

Insert Image 1 Here

One possible tour is 1 − 2 − 3 − 4 − 5 − 6 − 1:

Insert Image 2 Here

To figure the length of this tour, we first find the distance between *v<sub>1</sub>* and *v<sub>2</sub>*, which is (by the Pythagorean theorem)  
&Sqrt;[(8 − 3)<sup>2</sup> + (2 − 1)<sup>2</sup>] = &Sqrt;[25 + 1] = &Sqrt;[26] &thickapprox; 5.10  
Here are all the other distances between vertices (shown rounded to two decimal places), where row *j*, column *k* holds the distance from *v<sub>j</sub>* to *v<sub>k</sub>*:  
<table>
  <tr>
    <td></td>
    <td>1:</td>
    <td>2:</td>
    <td>3:</td>
    <td>4:</td>
    <td>5:</td>
    <td>6:</td>
  </tr>
  <tr>
    <td>1:</td>
    <td>0.00</td>
    <td>5.10</td>
    <td>3.61</td>
    <td>5.10</td>
    <td>5.83</td>
    <td>7.07</td>
  </tr>
  <tr>
    <td>2:</td>
    <td>5.10</td>
    <td>0.00</td>
    <td>3.61</td>
    <td>7.21</td>
    <td>4.47</td>
    <td>7.21</td>
  </tr>
  <tr>
    <td>3:</td>
    <td>3.61</td>
    <td>3.61</td>
    <td>0.00</td>
    <td>3.61</td>
    <td>2.24</td>
    <td>4.12</td>
  </tr>
  <tr>
    <td>4:</td>
    <td>5.10</td>
    <td>7.21</td>
    <td>3.61</td>
    <td>0.00</td>
    <td>4.00</td>
    <td>2.83</td>
  </tr>
  <tr>
    <td>5:</td>
    <td>5.83</td>
    <td>4.47</td>
    <td>2.24</td>
    <td>4.00</td>
    <td>0.00</td>
    <td>2.83</td>
  </tr>
  <tr>
    <td>6:</td>
    <td>7.07</td>
    <td>7.21</td>
    <td>4.12</td>
    <td>2.83</td>
    <td>2.83</td>
    <td>0.00</td>
  </tr>  
</table>  

After adding up all the distances from vertex to vertex along the tour, we get tour length 
5.10 + 3.61 + 3.61 + 4.00 + 2.83 + 7.07 = 26.22 

On the other hand, the tour 1 − 2 − 3 − 5 − 6 − 4 − 1 has length 21.70, which turns out to be the optimal tour.

Here are the functions you need to produce, along with any additional supporting functions that you need:
- Create a function genTours that takes one integer argument, n, and returns a list of all possible tours with the vertices 1 through n. To make this project considerably easier, due to shortage of time, you are allowed to use the built-in Racket function permutations.  
Here is a sample run (intended to show the Dr.Racket interactions window), showing one possible ordering of the tours—your function may generate them in any order you like:
```console
> (genTours 4)
((1 2 3 4) (1 2 4 3) (1 3 2 4) (1 3 4 2) (1 4 2 3) (1 4 3 2))
```
- Create a function named score that takes as inputs a list of positions of n vertices and a tour and returns the tour length.  
Here is a sample run (the first example given earlier, put into Lisp format, with the numeric function values not necessarily shown with the correct number of decimals):
```console
> (score ’((3 1) (8 2) (5 4) (2 6) (6 6) (4 8)) ’(1 2 3 4 5 6))
26.22
```
- Create a function named etsp that takes as its single argument a list of positions and returns an optimal tour.  
Here is a sample run (using the first example given earlier):
```console
> (etsp ’((3 1) (8 2) (5 4) (2 6) (6 6) (4 8)))
(1 2 3 5 6 4)
```

Don’t worry about efficiency in your approaches to the various functions. For the overall algorithm, just use the suggested brute force approach, which is to generate all the tours and find the best one. 

You must follow the approach specified, with additional functions that you will need. For example, given a list of point locations, you will need to determine how many there are, so you will probably want to create a count function that returns the number of items in a given list.
