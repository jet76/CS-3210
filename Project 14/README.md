# Project 14 - Binary search trees in Lisp

- Create a function named buildbst that takes as input a list of integers and returns an expression that encodes a binary tree, where the numbers in the input list are added, in first to last order, to form a binary search tree.

For example, the call
(buildbst ’(3 1 7 5) )
should produce as output
( 3 (1 () ()) (7 (5 () ()) () )
Use the encoding of a binary tree indicated, namely a binary tree consists of null, if it has no items in it, or a list of three forms, where the first is the root data item, and the second and third items are the left and right subtrees of the root.
Note that you will also need to create one or more auxiliary functions—certainly one that adds a given number to a given tree.

- Create a function named iot that takes as input a binary search tree using the format specified in the previous part, and returns a list with its items in the order produced by doing an in-order traversal of the given binary search tree.
For example, the call
(iot ’( 3 (1 () ()) (7 (5 () ()) ()) ) )
should produce as output
(1 3 5 7)
