package it.edu.majoranapa.led;

public class Led {
	/*
	*	Con questa classe, descrivo il funzionamento del singolo led (rosso, giallo o blu che sia)
	*/
	private int int_intensity = 0; //Intensità del led in valori interi (0-255)
	private String hex_intensity = "00"; //Intensità del led in valori esadecimali (00-ff)
	
	public Led(int intensity) 
	{
		/*
		*	Questa funzione speciale, chiamata costruttore, è la funzione che viene richiamata all'inizializzazione dell'oggetto,
		*	e serve ad inizializzare gli attributi della classe
		*/
		if(intensity > 255 || intensity < 0)
			return;
		this.int_intensity = intensity;	
		this.hex_intensity = Integer.toHexString(intensity);
	}
	
	//Metodo richiamato per impostare l'intensità del led tramite un valore intero
	public boolean setIntensity(int intensity)	
	{
		this.int_intensity = intensity;
		this.hex_intensity = Integer.toHexString(intensity);
		return false;	//Dichiaro il corretto funzionamento del metodo (funzione)
	}

	//Metodo richiamato per impostare l'intensità del led tramite un valore di stringa (esadecimale)
	public boolean setIntensity(String intensity)	
	{
		this.hex_intensity = intensity;
		/*
		*	Se l'utente non inserisce un valore esadecimale, la funzione genera un eccezione (crash).
		*	Col questo costrutto intercetto l'eccezione per non far fermare il programma (try-catch).
		*/
		try {	
			this.int_intensity = Integer.parseInt(intensity, 16);
		}
		catch(Exception exc)
		{
			System.out.println("Value not in hex format.");	//System.out.println serve per stampare a schermo una Stringa.
		}
		return false;	//Dichiaro il corretto funzionamento del metodo
	}
	
	public int getIntIntensity()
	{
		/*
		*	Restituisco il valore intero dell'attuale intensità.
		*	Essendo l'attributo privato, cercare di accedere all'attributo usando obj.int_intensity non avrebbe alcun effetto
		*	e genererebbe un errore di compilazione.
		*/
		return this.int_intensity;
	}
	
	public String getHexIntensity()
	{
		//	Restituisco il valore esadecimale dell'attuale intensità.
		return this.hex_intensity;
	}
}
