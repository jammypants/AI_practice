% three symptoms for each type of disease
symptom(fever,cold).
symptom(headache,cold).
symptom(sore_throat,cold).

symptom(runny_nose,flu).
symptom(sneezing,flu).
symptom(body_ache,flu).

symptom(joint_pain,arthritis).
symptom(weakness,arthritis).
symptom('unable to hold items',arthritis).

curable(cold).
curable(flu).

diagnose(Disease):-
write('Enter patient name'),read(Name),nl,
format('Hello ~s, welcome to VIT Hospital. Please enter your symptoms',[Name]),
write('Enter symptom1'),read(S1),nl,
write('Enter symptom2'),read(S2),nl,
write('Enter symptom3'),read(S3),nl,
identify_disease(S1,S2,S3).



identify_disease(S1,S2,S3):-
(symptom(S1,Disease);symptom(S2,Disease);symptom(S3,Disease)),
format('You have been diagnozed with ~s',[Disease]).

is_curable(Disease):-
(curable(Disease),format(' ~s is curable, no need to worry!',[Disease]);
format(' ~s is not curable',[Disease])).

start:-
diagnose(Disease),
is_curable(Disease).



