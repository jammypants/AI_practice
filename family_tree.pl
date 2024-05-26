% create family tree
male(avish).
male(sanjeev).
male(gulabchand).
male(ashok).
male(bhupendra).

female(ayushi).
female(rekha).
female(prem).
female(pratibha).

mother(rekha,avish).
mother(rekha,ayushi).
mother(prem,rekha).
mother(prem,pratibha).

father(sanjeev,avish).
father(sanjeev,ayushi).
father(gulabchand,sanjeev).
father(gulabchand,ashok).
father(nana,rekha).
father(nana,bhupendra).

% when comparing two strings ensure they are of same type/predicate
brother(X,Y):- male(X),((male(Y),X\=Y);female(Y)),((mother(Z,X),mother(Z,Y));(father(Z,X),father(Z,Y))).

sister(X,Y):- female(X),(male(Y);(female(Y),X\=Y)),((mother(Z,X),mother(Z,Y));(father(Z,X),father(Z,Y))).

grandfather(X,Y):- male(X),(father(Z,Y),father(X,Z));(mother(Z,Y),father(X,Z)).

grandmother(X,Y):- female(X),(father(Z,Y),father(X,Z));(mother(Z,Y),mother(X,Z)).

uncle(X,Y):- male(X),((father(Z,Y),brother(X,Z));mother(Z,Y),brother(X,Z)).