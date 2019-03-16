package metier;

public class Individuel extends Chambre {
private int nbrLits;

public Individuel(int num, int salleDeBain, int nbrLits) {
	super(num, salleDeBain);
	this.nbrLits= nbrLits;
	// TODO Auto-generated constructor stub
}

public int getNbrLits() {
	return nbrLits;
}

public void setNbrLits(int nbrLits) {
	this.nbrLits = nbrLits;
}

}
