%bob likes beer and whiskey
man(bob).
like(bob, beer).
like(bob, whiskey).
%alice likes whiskey and does not like beer
woman(alice).
like(alice, whiskey).
notLike(alice,beer).
%mike likes whiskey and does not like beer
man(mike).
like(mike, whiskey).
notLike(mike,beer).
%john only likes whiskey
man(john).
like(john, whiskey).
%dave only likes beer and hates everyone who likes whiskey and does not like beer
man(dave).
like(dave, beer).
hate(dave, X) :-
    like(X,whiskey),
    notLike(X,beer).
