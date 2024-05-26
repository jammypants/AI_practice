%base case
gcd(A,0):-
format('gcd is ~w',[A]).

gcd(A,B):-
A>=B,
Var is A mod B,
gcd(B,Var).
