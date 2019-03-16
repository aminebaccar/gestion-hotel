package metier;

public class Chambre {
	
	private int num;
	//private int nbPersonne;
	private int salleDeBain; // 0 si douche 1 si bain
	public Chambre(int num, int salleDeBain) {
		this.num = num;
		this.salleDeBain = salleDeBain;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getSalleDeBain() {
		return salleDeBain;
	}
	public void setSalleDeBain(int salleDeBain) {
		this.salleDeBain = salleDeBain;
	}
	@Override
	public String toString() {
		return "Chambre [num=" + num + ", salleDeBain=" + salleDeBain + "]";
	}
	
	
	
	

}
