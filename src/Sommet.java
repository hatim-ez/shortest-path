
public class Sommet {
	int id; 
	int lat;
	int longi;
	
	public Sommet(int i, int l, int lo){
		id=i;
		lat=l;
		longi=lo;
	}
	public Sommet(int i){
		id=i;
		lat=0;
		longi=0;
	}
	public boolean equals(Object o) {
		Sommet t=(Sommet)(o);
		return t.id==this.id;
	}
	
	public int hashCode(){
		return id;
	}

}
