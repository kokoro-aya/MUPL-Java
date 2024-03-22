# Made-Up-Programming-Language interpreter in Java

MUPL is a fictive language by Dan Grossman to illustrate some core concepts of programming languages in the 
Racket part of the Programming Language courses, namely the Part B on Coursera. It corresponds to the CSE341
course of UW.

This language is minimalist but turing complete, making it a nice playground for possible enhancements.

The lab homework of this course was to implement a simple MUPL interpreter and use it to implement features like
`let*` and `map` functions just like in Racket.

This repository is not concerning the Racket code. I inspired from my Racket solution and ported it to Java. The
interesting point is that you don't need to use any FP-related features to achieve a FP-based language. To do so,
I didn't use any advanced features of Java like pattern-matching etc. The visitor pattern was indeed a delicate 
topic, but it worked finally well with the MUPL language.

Further extensions are planned but under consideration.

The interpreter is not optimized in terms of performance, it will create and use lots of objects.

Warning: You should have finished the lab exercises before looking at my codebase. If you are unfamiliar with
lambda and FP concepts, maybe you want to reverse engineering the Java code to solve the lab, but this is a
bad habit and you should really get used to those concepts like lexical scope, closure and the way to program
in Racket.
