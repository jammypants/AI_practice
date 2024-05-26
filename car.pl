%database of car(brand,model,price,top speed,airbags) - struct
car('Porsche','Carrera',300,250,4).
car('Porsche','Carrera-GT',350,280,5).
car('Audi','Q8',200,220,4).
car('Audi','Q6',250,180,6).%SUV
car('Mercedez','Benz',180,160,2).

%predicate to get safest car
is_safe(Number_of_Airbags):-
car(Brand,Model,Price,Top,Airbags),% get all cars
Airbags>=Number_of_Airbags,% check if they are above
format('~w ~w',[Brand,Model]).

%Budget
enter_budget(Budget):-
car(Brand,Model,Price,Top,Airbags),
Budget>=Price,
write('Cars within budget are '),nl,
format('Brand: ~w Model: ~w Price: ~w',[Brand,Model,Price]).

is_faster(Car1,Car2):-
car(_,Car1,_,Top1,_),
car(_,Car2,_,Top2,_),
Car1 \= Car2,
Top1>=Top2,
Difference is Top1-Top2,
format('~w is faster than ~w by ~w km/h ',[Car1,Car2,Difference]).

is_slower(Car1,Car2):-
\+ is_faster(Car1,Car2),
car(_,Car1,_,Top1,_),
car(_,Car2,_,Top2,_),
Difference is Top2-Top1,
format('Slower').

%check if two cars are from same Brand
same_brand(Car1,Car2):-
car(Brand1,Car1,_,_,_),
car(Brand2,Car2,_,_,_),
Brand1 = Brand2,% equal to is unification, checks if two terms are same
format('Both are from ~w',[Brand1]).
