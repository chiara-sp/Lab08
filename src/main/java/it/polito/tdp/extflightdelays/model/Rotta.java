package it.polito.tdp.extflightdelays.model;

public class Rotta {
	
	private Airport partenza;
	private Airport destinazione;
	private double peso;
	
	public Rotta(Airport partenza, Airport destinazione, double peso) {
		super();
		this.partenza = partenza;
		this.destinazione = destinazione;
		this.peso = peso;
	}

	public Airport getPartenza() {
		return partenza;
	}

	public void setPartenza(Airport partenza) {
		this.partenza = partenza;
	}

	public Airport getDestinazione() {
		return destinazione;
	}

	public void setDestinazione(Airport destinazione) {
		this.destinazione = destinazione;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "Rotta [partenza=" + partenza + ", destinazione=" + destinazione + ", peso=" + peso + "]" + "\n";
	}
	
	
	
	

}
