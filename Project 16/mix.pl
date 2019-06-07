% mix( X, Y, M ) that means “M is a list formed by alternating items
% from lists X and Y, which are assumed to have the same number of
% items.”
mix([],[],[]).
mix([X|XRest],[Y|YRest],[X,Y|M]):-mix(XRest,YRest,M).
