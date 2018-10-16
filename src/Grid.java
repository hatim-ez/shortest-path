import java.util.HashMap;
import java.util.LinkedList;

public class Grid {
	final static int c = 8; // constante utilisee dans la fonction de hachage

	final HashMap<Integer, LinkedList<Arc>> graph; // graph, à un sommet identifié par son id on associe tous les arcs sortant
	final HashMap<Integer, Sommet>sommets;   // A un id on associé un sommet
	
	
	// On construit une grid en mettant les arcs aléatoirement 
	public Grid(int n){
		graph=new HashMap<Integer, LinkedList<Arc>>();
		sommets= new HashMap<Integer, Sommet>();
		for (int i=0; i<n; i++){
			for (int j=0;j<n ;j++){
				sommets.put(i*n+j, new Sommet(i*n+j, i, j));
				graph.put(i*n+j, new LinkedList<Arc>());
			}
		}
		for (int i=0; i<n; i++){
			for (int j=0; j<n; j++){
				if (j!=n-1 && Math.random()>0.5){
					graph.get( i*n+j).add(new Arc( i*n+j,  i*n+j+1, 4*60*1000));
				}
				if (j!=0 && Math.random()>0.5){
					graph.get( i*n+j).add(new Arc( i*n+j,  i*n+j-1, 4*60*1000));
				}
				if (i!=n-1 &&Math.random()>0.5){
					graph.get( i*n+j).add(new Arc( i*n+j,  i*n+j+n, 4*60*1000));
				}
				if (i!=0 && Math.random()>0.5){
					graph.get( i*n+j).add(new Arc( i*n+j,  i*n+j-n, 20*60*1000));
				}
				
			}
		}
		System.out.println("Le graph est construit");
		
	}
	
	
	public LinkedList<Arc> successors(Integer i) {
		
		return  graph.get(i);
		
	}
	

}
