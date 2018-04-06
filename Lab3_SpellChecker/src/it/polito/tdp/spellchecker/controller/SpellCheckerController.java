package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.*;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class SpellCheckerController {
	
	private Dictionary model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> language;

    @FXML
    private TextArea txtCheck;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private TextArea txtCanc;

    @FXML
    private Text redText;

    @FXML
    private Button btnClearText;

    @FXML
    private Text blackText;

    @FXML
    void doClearText(ActionEvent event) {
    	txtCheck.clear();
    	txtCanc.clear();
    	redText.setText("");
    	blackText.setText("");
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	LinkedList<RichWord> correzione = new LinkedList<RichWord>();
    	String inserimento = txtCheck.getText().toLowerCase().replaceAll("[.,\\/#!?$%\\^&\\*;:{}=\\-_`~()\\[\\]]", "" );
    	String[] parole = inserimento.split(" ");
    	if(language.getValue().compareTo("Italian") == 0) {
    		model.caricaIta();
    	}
    	if(language.getValue().compareTo("English") == 0) {
    		model.caricaEng();
    	}
    	
    	if(parole.length == 0) {
    		txtCanc.appendText("Inserire testo\n");
    		return;
    	}
    	long l1 = System.nanoTime();
    	
    	correzione = (LinkedList<RichWord>) model.spellCheckText(parole);  	
    	for(RichWord r : correzione) {
    		if(!r.isCorretta()) {
    			txtCanc.appendText(r.getParola() + "\n");
    		}
    	}
    	
    	long l2 = System.nanoTime();
    	
    	redText.setText("The text contains " + model.getNumErrori() + " errors");
    	blackText.setText("Spell check completed in " + (l2-l1)/1E9 + " seconds");
    	model.resetNumErrori();
    }

    @FXML
    void initialize() {
        assert language != null : "fx:id=\"language\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtCheck != null : "fx:id=\"txtCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtCanc != null : "fx:id=\"txtCanc\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert redText != null : "fx:id=\"redText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert blackText != null : "fx:id=\"blackText\" was not injected: check your FXML file 'SpellChecker.fxml'.";

    }

	public void setModel(Dictionary model) {
		this.model = model;
    	this.language.getItems().add("English");
    	this.language.getItems().add("Italian");
	}
}