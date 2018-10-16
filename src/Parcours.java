
import java.util.*;
import java.io.*;

public class Parcours {
	HashMap<Integer, Integer> visited;  // contient pour chaque sommet visité le temps de trajet
	Graph graph;
	Grid grid;
	double time;  // Valeur maximale pour le temps de trajet
	Integer depart;  // point de départ
	HashMap<Integer,Integer>parents; // A un sommet on associe le sommet précédent sur le trajet
	int nbPoints;

	
	// Constructeur pour un un parcours dans les graphs France, id ou Malte
	public Parcours (String fil, double time, Integer dep) throws Exception {
		time=time*3600000;  // conversion heures en ms
		graph = new Graph(fil);
		this.depart=Integer.MAX_VALUE - dep;
		nbPoints=0;
		visited= new HashMap<Integer, Integer>();
		parents = new HashMap<Integer, Integer>();
		this.time=time;
		visited.put(depart,0);
		parents.put(depart, depart);
		PriorityQueue <Arc> q = new PriorityQueue<Arc>();
		LinkedList<Arc> list = graph.successors(depart);
		if (list ==null){
			System.out.println("Le point de départ n'est pas dans le graphe");
			return;
		}
		q.addAll(list);
		while (!q.isEmpty()){
			Arc arc = q.poll();
			int timeParcouru=visited.get(arc.origin);
			if ((timeParcouru)<time){
				Integer timePrecedent = visited.get(arc.destination);
				if (timePrecedent==null){ // première fois qu'on visite le sommet
					visited.put(arc.destination, timeParcouru+arc.time);
					LinkedList<Arc> succ = graph.successors(arc.destination);
					parents.put(arc.destination, arc.origin);
					q.addAll(succ);
					
				}
				else {
					if (timePrecedent>timeParcouru+arc.time){  // si ce trajet est plus intéressant on actualise
						visited.put(arc.destination, timeParcouru+arc.time);
						LinkedList<Arc> succ = graph.successors(arc.destination);
						parents.put(arc.destination, arc.origin);
						q.addAll(succ);
						
					}
				}
			}
		}
		System.out.println("Le parcours du graphe est fini");
			
		
	}
	
	// Constructeur de Parcours spécial pour Grid
	public Parcours (int n, double tim, Integer dep) throws Exception {
		this.time=tim*3600*1000;  // conversion heures en ms
		grid = new Grid(n);
		this.depart=dep;
		nbPoints=0;
		visited= new HashMap<Integer, Integer>();
		parents = new HashMap<Integer, Integer>();
		visited.put(depart,0);
		parents.put(depart, depart);
		PriorityQueue <Arc> q = new PriorityQueue<Arc>();
		LinkedList<Arc> list = grid.successors(depart);
		if (list ==null){
			System.out.println("Le point de départ n'est pas dans le graphe");
			return;
		}
		q.addAll(list);
		while (!q.isEmpty()){
			Arc arc = q.poll();
			int timeParcouru=visited.get(arc.origin);
			if ((timeParcouru)<time){
				Integer timePrecedent = visited.get(arc.destination);
				if (timePrecedent==null){ // première fois qu'on visite le sommet
					visited.put(arc.destination, timeParcouru+arc.time);
					LinkedList<Arc> succ = grid.successors(arc.destination);
					parents.put(arc.destination, arc.origin);
					q.addAll(succ);
					
				}
				else {
					if (timePrecedent>timeParcouru+arc.time){  // si ce trajet est plus intéressant on actualise
						visited.put(arc.destination, timeParcouru+arc.time);
						LinkedList<Arc> succ = grid.successors(arc.destination);
						parents.put(arc.destination, arc.origin);
						q.addAll(succ);
						
					}
				}
			}
		}
		System.out.println("Le parcours du graphe est fini");
			
		
	}
	
	
	
	
	// Calcul le nombre de points à distance time du point de départ et modifie le fichier points.js pour permettre la visualisation
	int resultatsVisualisation(double tim){
		tim=tim*3600*1000;
		File f = new File ("RoadNetworks/vis/points.js");
		try {
			FileWriter fw = new FileWriter (f);
			fw.write("var plottedPoints = [ \n ");
			for (Map.Entry<Integer,Integer> e : visited.entrySet()){
				
				if (e.getValue()>=tim){	
					int par = parents.get(e.getKey());
					if (visited.get(par)<=tim){
						nbPoints++;
						fw.write("\n");
						double Integeri = Double.valueOf(this.graph.sommets.get(e.getKey()).lat)/1000000;
						double lat = Double.valueOf(this.graph.sommets.get(e.getKey()).longi)/1000000;
						String str ="[ " + lat + ", " + Integeri + " ]";
						fw.write(str);
						fw.write(",");
					}	
				}
				
			}
			fw.write("\n];\n");
			fw.write("var centralMarker ="+ "\n");
			String str = "[ " + Double.valueOf(this.graph.sommets.get(depart).longi)/1000000+ ", " + Double.valueOf(this.graph.sommets.get(depart).lat)/1000000 + " ]";
			fw.write(str);
			fw.write(";\n");
			fw.close();
		}
		catch (IOException exception)
		{
		    System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
		}
		
		return nbPoints;
	}
	

	//  Nombres de points accessibles au temps tim
	int resultatsExact(double tim){
		nbPoints=0;
		for (Map.Entry<Integer,Integer> e : visited.entrySet()){
			
			if (e.getValue()>=tim){
				Integer par = parents.get(e.getKey());
				if ( visited.get(par)<=time){
					nbPoints++;
				}	
			}
		}
		return nbPoints;
	}
	
	
	// Permet de tracer le nombre de points atteignables en fonction du temps
	Dot[] resultatsNbPoints( int nbPoints){
		int intervalle= (int)this.time - 60*1000;
		double[] resultats = new double[nbPoints];
		double[] timeParcours = new double[nbPoints];
		Dot[] Points = new Dot[nbPoints];
		double pas = intervalle/nbPoints;
		for (int i =0; i<nbPoints ; i++) {
			timeParcours[i] = 60*1000+i*pas;
			resultats[i] = resultatsExact( timeParcours[i]);
			Points[i] = new Dot(timeParcours[i], resultats[i]);
		}
		
		return Points;
	}
	
	
	// Permet de récupérer le nombre de points à une durée timeInt sur un trajet de durée tim
	int ResultatsInter(double tim, double timeInt){
		if (tim<=10){
			tim=tim*3600000;
			timeInt = timeInt*3600000;
		}
		LinkedList<Integer> points1h=new LinkedList<Integer>();
		for (Map.Entry<Integer,Integer> e : visited.entrySet()){
			if (e.getValue()>=tim) {
				int par = parents.get(e.getKey());
				if ( visited.get(par)<=tim){
					nbPoints++;
					Integer pointCourrant=e.getKey();
					int pointPrécedent =0;
					pointPrécedent= par;
					while (visited.get(pointPrécedent)>timeInt){   // On récupère le point sur le trajet qui correspond au temps 1h 
						pointCourrant=pointPrécedent;
						pointPrécedent=parents.get(pointCourrant);
					}
					if (!points1h.contains(pointPrécedent)){
						points1h.add(pointPrécedent);
					}
				}	
			}
		}
		return points1h.size();
		
	}
	
	//Permet de tracer le nombre de points à une durée timeInter sur des trajets de longueurs allant de 1.1h à this.time h
	Dot[] resultatsNbPointsInter(int timeInter, int nbPoints){
		int intervalle= (int)this.time - 11*360*1000;
		
		double[] resultats = new double[nbPoints];
		double[] timeParcours = new double[nbPoints];
		Dot[] Points = new Dot[nbPoints];
		double pas = intervalle/nbPoints;
		for (int i =0; i<nbPoints ; i++) {
			timeParcours[i] = 1.1*3600*1000+i*pas;
			resultats[i] = ResultatsInter( timeParcours[i], timeInter*3600*1000);
			Points[i] = new Dot(timeParcours[i], resultats[i]);
		}
		
		return Points;
	} 
	
	
	
}
