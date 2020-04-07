package it.polito.tdp.totocalcio.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {
	
	private Pronostico pronostico;
	private int N;
	private List<Risultato> soluzione;

	public List<Risultato> cerca(Pronostico pronostico) {
		//INTERFACCIA VERSO L'ESTERNO
		
		//prepara la struttura che verrà utilizzata dalla ricorsiva
		this.pronostico = pronostico;
		this.N = pronostico.size();		
		
		List<RisultatoPartita> parziale = new ArrayList<>();
		int livello = 0;
		
		this.soluzione = new ArrayList<Risultato>();
		ricorsiva(parziale, livello);
		return this.soluzione;
	}

	private void ricorsiva(List<RisultatoPartita> parziale, int livello) { 
		//al livello 3, hai una soluzione parziale che contiene 3 elementi, devi mettere il 4°, 
		//cioè quello di indice 3 nella lista.
		
		if(livello==N) {  //se sono nel caso terminale
			//System.out.println(parziale); //questa soluzione parziale è una soluzione completa
			this.soluzione.add(new Risultato(parziale)); //restituire al chiamante
		}else { //caso generale
			
			//[parziale da 0 a livello -1] [livello] [livello +1 in poi]
			PronosticoPartita pp = this.pronostico.get(livello); //(pp ha 2 e x)
			//pp sono i sotto-problemi da provare
			
			for(RisultatoPartita ris : pp.getRisultati()) { //devo iterare su 2 o x
				//provo a mettere ris nella posizione "livello" della soluzione parziale(ha già livello elem da 0 a -1)
				
				//costruzione soluzione parziale (sotto-problema)
				parziale.add(ris);
				//chiamata ricorsiva
				ricorsiva(parziale, livello+1);
				//backtracking
				parziale.remove(parziale.size()-1); //l'ultimo che ho messo, lo tolgo.			
			}
		}
		
	}
}

/*Livello = numero di partite che sto considerando
 * le parite da 0 a livello -1 sono già state decise
 * la partita di indice livello la devo decidere io
 * le partite da livello +1 in poi le decideranno le procedure ricorsive sottostanti
 * 
 * Soluzione parziale: elenco di RisultatoPartita di lunghezza pari al livello
 * 
 * Soluzione totale: ho N risultati
 * 
 * Condizione di terminazione: livello == N
 * 
 * Generazione delle soluzioni del livello: provando tutti i pronostici per quel livello
 * 
 */

/* RAGIONAMENTO RICORSIVO
[ "2X", "1", "1X2", "12"] 
		
	? //(o 2 o x) faccio quindi 2 chiamate ricorsive per vedere cosa succede se metto 2 o x
	  // Quando poi ho già 4 chiamate ricorsive, salvo il valore.
["2X"] + ["1", "1X2", "12"] // al primo livello (livello 0) vedo solo le prime quadre, il resto no
	   ["1", "1X2", "12"]
	   ["1"] + ["1X2", "12"] // livello 1
*/			   