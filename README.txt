Les fonction de test pour chaque question sont ecrites dans la classe texte. 

Pour creer un graph : le constructeur de la classe graph prend en argument le nom du fichier : fil. 

Pour creer un graph aleatoire : grid. Le constructeur prend en argument la taille n du graph n*n.

Pour lancer un parcours : 
- avec un graph classique : constructeur Parcours(fil, time, dep) où fil est le nom du fichier, time le temps maximal du trajet et dep le point de depart. 

- Avec un graph aleatoire : constructeur Parcours(n, time, dep) où n est la taille du graph. 

Pour analyser les resultats du parcours : 
- resultatsVisualisation(tim) renvoie le nombre de points à exactement tim ms du point de depart (tim est suppose inferieur à la duree maximale du parcours). Il ecrit aussi les coordonnees des points atteignables dans le fichier points.js afin de permettre la visualisation,

- resultatExact(tim) fait la meme chose que la methode precedente mais n ecrit pas dans le fichier points.js,

- resultatsNbPoints(nbPoints) renvoie une liste de taille nbPoints des nombres de points atteignables pour des duree comprises entre 1 min et this.time,

- resultatsInter(tim, timeInt) renvoie le nombres à timeInt ms du points de depart sur un trajet de duree tim, 

- resultatsNbPointsInter(timeInter, nbPoints) renvoie une liste de taille nbPoints : comprendre les valeurs de resultatsInter(time, timeInter) pour time compris entre 1.1h et this.time ms.  En fait timeInter vaut 1 dans touts les tests. 

-Plot(title, tableauDot) construit et affiche une interface graphique représentant les points de tableauDot.

NB : On utilise les memes fonctions d analyse pouts les graphs aletoires mais les resultats semblent incoherents.



	
