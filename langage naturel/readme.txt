dossier documentation : deux dossiers 
-dossier documents linguistique : ex corpus
-dossier documents de conception : ex diagramme de classe
dossier api google : c'est l'api google.
dossier sources : les sources  du projets + javadoc dans le dossier doc

Pour l'instant on peut tester la classe RequeteGoogle (envoyer une requête google), 
pour cela il faut ajouté au ClASSPATH "googleapi.jar".
Les arguments sont dans cet ordre  :
	-la langue (par ex lan_en pour anglais) 
	-le nombre max de reponses (entier) 
	-la requête (string).
par exemple :
java RequeteGoogle lan_en 10 "hello world"
