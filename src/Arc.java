
public class Arc implements Comparable{
	int origin; // origine de l'arc
	int destination; // destination de l'arc
	int time; // temps de trajet
	
	// constructeur
	public Arc(int o, int d, int t) {
		this.origin = o;
		this.destination = d;
		this.time=t;
	}

	// redefinition de la fonction equals
	public boolean equals(Object o) {
		Arc a=(Arc)o;
		return this.origin==a.origin && this.destination==a.destination;
	}
	
	// redefinition du hashCode
	public int hashCode() {  // à réécrire 
		return Graph.c*origin+destination;
	}
	
	public int compareTo(Object o){
		Arc c = (Arc)o;
		if (this.time>=c.time)
			return 1;
		if (this.time==c.time)
			return 0;
		else 
			return -1;
	}
}
