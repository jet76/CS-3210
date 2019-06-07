nextItem([N|[]],N).
nextItem(List,N):-rowBelow(List,ListBelow),nextItem(ListBelow,N1),myLast(List,Last), N is Last+N1.
rowBelow([A,B],[C]):-C is B-A.
rowBelow([A,B|List],[C|Result]):-C is B-A,rowBelow([B|List],Result).
myLast([Last|[]],Last).
myLast([First|Rest],Last):-myLast(Rest,Last).
