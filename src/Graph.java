
import java.util.*;
import java.io.*;

public class Graph {
	
	final static int c = 8; // constante utilisee dans la fonction de hachage
	
	final HashMap<Integer, LinkedList<Arc>> graph; // à un sommet identifié par son id on associe tous les arcs sortant
	final HashMap<Integer, Sommet>sommets; // permet de retrouver un Sommet avec l'id uniquement
	
	// constructeur
	
	
	public Graph(String file) throws Exception {
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String dataLine;

		sommets = new HashMap<Integer, Sommet>();
		graph = new HashMap<Integer, LinkedList<Arc>>();	
		
		while ((dataLine = br.readLine()) != null) {
			String[] tokens = dataLine.split(" ");
			
			if (tokens[0].equals("v")) {  // on ajoute tous les sommets avec une liste d'Arc nuls
				Long id = Long.valueOf(tokens[1]);
				id = Long.valueOf(Integer.MAX_VALUE) - id;
				int i = id.intValue();
				int lat = Integer.parseInt(tokens[2]);
				int lon = Integer.parseInt(tokens[3]);
				graph.put(i, new LinkedList<Arc>());
				sommets.put(i, new Sommet(i, lat, lon));

			}
			if (tokens[0].equals("a")){    // on ajoute les arcs
				Long orId = Long.valueOf(tokens[1]);
				orId = Long.valueOf(Integer.MAX_VALUE) - orId;
				int o = orId.intValue();
				Long dId = Long.valueOf(tokens[2]);
				dId = Long.valueOf(Integer.MAX_VALUE) - dId;
				int d = dId.intValue();
				
				int t= Integer.parseInt(tokens[3]);
				graph.get(o).add(new Arc(o,d,t));
			}
		}
		br.close();
		System.out.println("Le graphe est construit");
	}
	
	
	
	public LinkedList<Arc> successors(Integer i) {
		
		return  graph.get(i);
		
	}
	
}
