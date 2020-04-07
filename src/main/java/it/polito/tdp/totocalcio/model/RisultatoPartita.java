package it.polito.tdp.totocalcio.model;

public enum RisultatoPartita {
	UNO, DUE, ICS ;
	
	public static RisultatoPartita valueOf(char c) { //prende una stringa
		switch(c) {
		case '1': // se la stringa corrisponde a 1, restituisce UNO.
			return RisultatoPartita.UNO ;
		case '2':
			return RisultatoPartita.DUE ;
		case 'X':
		case 'x':
			return RisultatoPartita.ICS ;
		default:
			throw new IllegalArgumentException("Illegal character "+c+" in string") ;				
		}
	}
	
	public String toString() {
		switch(this) {
		case UNO: //quando la costante è 1, rappresentalo con una stringa "1".
			return "1" ;
		case DUE:
			return "2" ;
		case ICS:
			return "X" ;
		}
		return null ;
	}
	
	
}
