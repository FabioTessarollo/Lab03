package it.polito.tdp.spellchecker.model;

public class RichWord {
	
	private String parola;
	private boolean corretta;

	public RichWord(String parola) {
		this.parola = parola;
		this.corretta = false;
	}
	
	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public void setCorretta() {
		this.corretta = true;
	}

	public boolean isCorretta() {
		return corretta;
	}


}
