% locate(Target,List,Index) means Target is in List at position Index
locate(Target,[Target|Rest],0).
locate(Target,[First|Rest],Index):-locate(Target,Rest,Index1),Index is Index1+1.

