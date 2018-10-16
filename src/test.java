

public class test {
	
	// Décommentez pour lancer le test de chaque question

	public static void main(String[] args) throws Exception {
		
		
		question1();
		//question2();
		//question3();
		//questionA1Lozere();
		//questionA1Auverge();
		//questionA2();
		//questionA3();
		
		//questionA4();
		//questionA4Bis();
		
	}
	
	static void question1() throws Exception{  

		Parcours parcours = new Parcours ("france.in", 1, 15643100);
		System.out.println("Il y a un "+parcours.resultatsVisualisation(1)+" points à 1h de Paris");
		
	}
	
	static void question2() throws Exception{
		Parcours parcours = new Parcours ("france.in", 2, 15643100);
		System.out.println("Il y a un "+parcours.resultatsVisualisation(2)+" points à 2h de Paris");
		
	}
	
	static void question3() throws Exception{
		Parcours parcours = new Parcours ("france.in", 2, 15643100);
		System.out.println("Il y a "+parcours.ResultatsInter(2,1)+" points à 1h de Paris sur des trajets de 2h");
		
	}
	
	static void questionA1Lozere() throws Exception{
		Parcours parcours= new Parcours("france.in", 2, 122980);
		System.out.println("Il y a "+parcours.resultatsVisualisation(1)+" points à 1h de Lozere");
		System.out.println("Il y a un "+parcours.resultatsVisualisation(2)+" points à 2h de Lozere");
		System.out.println("Il y a un "+parcours.ResultatsInter(2,1)+" points à 1h de Lozere sur des trajets de 2h");
	}
	static void questionA1Auvergne() throws Exception{
		Parcours parcours= new Parcours("france.in", 2, 281923702);
		System.out.println("Il y a un "+parcours.resultatsVisualisation(1)+" points à 1h de Clermont Ferrand");
		System.out.println("Il y a un "+parcours.resultatsVisualisation(2)+" points à 2h de Clermont Ferrand");
		System.out.println("Il y a un "+parcours.ResultatsInter(2,1)+" points à 1h de Clermont Ferrand sur des trajets de 2h");
	}
	
	static void questionA2() throws Exception{
		int depart =122980;
		Parcours parcours = new Parcours ("france.in", 8 , depart);
		Plot panel = new Plot("Nombre de Points atteints en fonction du temps de parcours", parcours.resultatsNbPoints(30));
		
	}
	
	static void questionA3() throws Exception{
		int depart=122980;
		Parcours parcours=new Parcours("france.in", 2, depart);
		Plot panel = new Plot("Nombre de Points à 1h sur un trajet compris entre 1.1h et 4h", parcours.resultatsNbPointsInter(1, 30));
	}
	
	// Le graph semblre incohérent pour cette fonction
	static void questionA4() throws Exception{
		Parcours parcours = new Parcours(1000, 8, 500*1000+500);
		Plot panel = new Plot("Nombre de Points atteints en fonction du temps pour un graphe aléatoire", parcours.resultatsNbPoints(30));
	}
	
	static void questionA4Bis() throws Exception{
		Parcours parcours = new Parcours(1000, 4, 500*1000+500);
		Plot panel = new Plot("Nombre de Points à 1h sur un trajet compris entre 1.1h et 4h pour un graphe aléatoire", parcours.resultatsNbPointsInter(1,25));
	}
	

}
