dossier documentation : deux dossiers 
	-dossier documents linguistique : ex corpus
	-dossier documents de conception : ex diagramme de classe
dossier api google : c'est l'api google.
dossier sources : les sources  du projet + javadoc dans le dossier doc

Pour l'instant on peut tester la classe RequeteGoogle (envoyer une requ?te google), 
pour cela il faut ajout? au ClASSPATH "googleapi.jar".
Les arguments sont dans cet ordre  :
	-la langue (par ex lan_en pour anglais) 
	-le nombre max de reponses (entier) 
	-la requ?te (string).
par exemple :
java RequeteGoogle lan_en 10 "hello world"
