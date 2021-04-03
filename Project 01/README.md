# Project 1 (writing simple VPL programs)

Working in your group write the following simple programs in VPL:
a. Create a VPL program that asks the user for an input value and displays 1 if that input is an odd number, and 2 if it is an even number.
b. Create a VPL program that gets an input value, say x, and repeatedly divides x by 2 if x is even or multiplies x by 3 and adds 1, until x reaches 1. Display the current value of x each time it changes.

The folder VPL at the course web site contains files named VPL.save and IntPair.save. Download these and rename them to VPL.class and IntPair.class.

My Unix script that uploads all the course files to my website refuses—by design—to upload .class files, so I’ve named them .save, and you have to download them and give the correct names for Java. The whole point here is that I’m not giving you my .java files for the VPL implementation, because I’m going to ask you to write them on a later project.

These files give my implementation of VPL so you can check your VPL programs before you actually implement VPL yourself on a later project. To do this at the command line (or equivalent with an IDE) just type, assuming you have put your VPL code in a text file named rp1a.vpl:

java VPL rp1a.vpl 100000

This should run your VPL program (it actually runs the Java program named VPL and feeds it the VPL code, along with the 100000 which is the number of memory cells requested).

Before I check-off your group’s Routine Project 1, I’ll want to see your VPL code for both programs actually being run in the VPL simulator.
