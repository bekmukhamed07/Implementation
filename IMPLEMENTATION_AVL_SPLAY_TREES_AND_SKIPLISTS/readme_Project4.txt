READ ME



This package version includes the program for the following requirement mentioned for Project-4:


The dictionary ADT is defined on a set of (key,value) pairs, where the
   keys are totally ordered.  The following operations are defined:

   a. Insert(k,v): insert a new entry with key k, value v.  If a key with
                   key k already exists, its value is replaced by v.

   b. Find(k): return the value associated with key k.  If there is
               no element with key k, it returns null (or 0).

   c. FindMin(): return (k,v) corresponding to the current smallest key

   d. FindMax(): return (k,v) corresponding to the current largest key

   e. Remove(k): remove element with key k.  Returns value of deleted
                 element (null or 0 if such a key does not exist).

   f. RemoveValue(v): remove all elements whose value is v.  Returns
                      number of elements deleted.

   g. Size(): return the number of elements currently stored.

   h. IsEmpty(): boolean indicating whether the current store is empty.

Implement the ADT using (a) Skip lists, (b) Splay trees,
(c) AVL or Red-Black trees and any other data structure that you think
is best suited for the problem, and compare their performances empirically,
by comparing their running times over a large number of operations.
Recommend the best implementation from your experiments.
You may download any implementations of these data structures from
the web and modify the code as needed.


Files Impacted:

--AVL Tree Files
AVLNode.java
AVLTree.java
AVLTreeOperations.java

--SkipList Files
SkipList.java
SkipListOperations.java

-- Splay Tree Files
BinaryNode.java
SplayOperations.java
SplayTree.java



NOTE: 
* For each implementation X the "XOperations.java" file is the main class for executing the program.
X-> Splay/AVLTree/SkipList

* The input is taken from an input file- “input.txt”, and the output will be displayed on the console.