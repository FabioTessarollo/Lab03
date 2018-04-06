package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	
	private LinkedList<String> ita = new LinkedList<String>();
	private LinkedList<String> eng = new LinkedList<String>();
	private boolean english; //true -> eng, false -> ita
	private int numErrori = 0;
	
	public void caricaEng() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("rsc/English.txt"));
			String word;
			while((word = br.readLine()) != null) {
				eng.add(word);
			}
			english = true;
			br.close();	
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
	
	public void caricaIta() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("rsc/Italian.txt"));
			String word;
			while((word = br.readLine()) != null) {
				ita.add(word);
			}	
			english = false;
			br.close();	
			} catch (IOException e) {
				e.printStackTrace();
			}	
	}
	
	
	public List<RichWord> spellCheckText(String[] inputTextList) {

		LinkedList<RichWord> output = new LinkedList<RichWord>();
		for(String s : inputTextList) {
			RichWord word = new RichWord(s);
			
			if(!english && ita.contains(s)) {
				word.setCorretta();			
			}
			
			if(english && eng.contains(s)) {
				word.setCorretta();			
			}
			
			if(!word.isCorretta())
				numErrori++;
			
			output.add(word);
		}
		return output;
	}

	public void resetNumErrori() {
		this.numErrori = 0;
	}

	public int getNumErrori() {
		return numErrori;
	}
	
	
	
}
	

