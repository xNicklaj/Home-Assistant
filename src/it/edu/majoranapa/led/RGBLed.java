package it.edu.majoranapa.led;

public class RGBLed {
	private Led red = new Led(0);		//Oggetto di tipo Led (Vedi Led.java) che viene utilizzato per i valori del led rosso
	private Led green = new Led(0);		//Oggetto di tipo Led che viene utilizzato per i valori del led verde
	private Led blue = new Led(0); 		//Oggetto di tipo Led che viene utilizzato per i valori del led blu.
	//Oggetto di tipo RGBController che viene utilizzato per aggiornare la GPIO.
	//private RGBController controller = new RGBController(red.getIntIntensity(), green.getIntIntensity(), blue.getIntIntensity());	
	/*
	*	Valore esadecimale dell'intensità della tripletta di led e.g. ff00ac.
	* 	ff rappresenta il led rosso, 00 il led verde, ac il led blu.
	*/
	private String hex_intensity = red.getHexIntensity() + green.getHexIntensity() + blue.getHexIntensity();	
	
	public RGBLed(String hex_intensity)	
	{
		/*
		*	Costruttore della classe RGBLed (vedi Led.java per la spiegazione) che imposta il valore predefinito in base ad una stringa esadecimale.
		*	Ha lo stesso funzionamento del metodo setSingularLedIntensity, ma è corretto uso non richiamare metodi esterni all'interno del costruttore,
		*	poiché per richiamare il metodo dovresti aver prima creato l'oggetto, che tuttavia viene creato al richiamo del costruttore. Sarebbe un paradosso.
		*	Questo ad eccezione fatta per i metodi statici (vedi Main.java)
		*/	
		this.hex_intensity = hex_intensity;
		red.setIntensity(this.hex_intensity.substring(0, 2));
		green.setIntensity(this.hex_intensity.substring(2, 4));
		blue.setIntensity(this.hex_intensity.substring(4));
		//controller.updateGPIO(red.getIntIntensity(), green.getIntIntensity(), blue.getIntIntensity());	//Aggiorno la GPIO per prendere i valori appena passati.
	}

	private boolean isHexStringValid(String hex_intensity)
	{
		String temp = hex_intensity.toLowerCase();
		
		if(hex_intensity.length() > 6)
			return false;
		
		for(short i = 0; i < 15; i++)
		{
			if(temp.contains(Integer.toString(i + 32)))
				return false;
		}
		for(short i = 0; i < 6; i++)
		{
			if(temp.contains(Integer.toString(i + 58)))
				return false;
		}
		for(short i = 0; i < 5; i++)
		{
			if(temp.contains(Integer.toString(i + 91)))
				return false;
		}
		for(short i = 0; i < 4; i++)
		{
			if(temp.contains(Integer.toString(i + 123)))
				return false;
		}
		
		return true;
	}
	
	//Metodo che viene utilizzato per impostare il valore dei singoli oggetti di tipo led.
	private void setSingularLedIntensity()	
	{
		red.setIntensity(this.hex_intensity.substring(0, 2));		//con questo metodo, mi riferisco soltanto ai primi due valori della Stringa hex_intensity.
		green.setIntensity(this.hex_intensity.substring(2, 4));		//Con questo metodo, mi riferisco al terzo ed al quarto valore
		blue.setIntensity(this.hex_intensity.substring(4));			//Con questo metodo, mi riferisco ai restanti valori (quinto e sesto)
	}
	
	public int setHexIntensity(String hex_intensity)
	{
		String hex_intensity_lowerCase = hex_intensity.toLowerCase();
		/*
		*	Creo uno switch di valori passati in maniera tale che all'utente basti scrivere un colore di default in modo da impostare il led
		*	a quel determinato valore. Altrimenti, nel caso in cui il valore passato non sia un valore esadecimale o di default, allerto l'utente.
		*	Se l'utente digita exit, esco dal programma, se digita un valore esadecimale valido, viene assegnato il valore esadecimale.
		*/
		switch(hex_intensity_lowerCase)
		{
		case "red":
			this.hex_intensity = "ff0000";
			break;
		case "green":
			this.hex_intensity = "00ff00";
			break;
		case "blue":
			this.hex_intensity = "0000ff";
			break;
		case "yellow":
			this.hex_intensity = "ffff00";
			break;
		case "cyan":
			this.hex_intensity = "00ffff";
			break;
		case "magenta":
			this.hex_intensity = "ff00ff";
			break;
		case "white":
			this.hex_intensity = "ffffff";
			break;
		case "exit":
			return -1;
		default:
			if(this.isHexStringValid(hex_intensity))
				this.hex_intensity = hex_intensity;
			else
			{
				System.out.println("Value not in hex format.");
				return 1;
			}
		}
		/*
		*	Una volta aver assegnato l'intensità totale nel parametro della classe, richiamo questo metodo per modificare i parametri
		*	contenuti nei singoli led.
		*/
		this.setSingularLedIntensity();		
		//controller.updateGPIO(red.getIntIntensity(), green.getIntIntensity(), blue.getIntIntensity());	//Aggiorno la GPIO.
		
		return 0;	//Dichiaro il corretto funzionamento del metodo.
	}

	public String getHexIntensity()
	{
		return this.hex_intensity;	//Restituisco l'attuale valore dell'intensità in formato xxyyzz e.g. f3ca4b
	}
	
	//Stessa funzione di getHexIntensity(), ma utilizza i valori contentui negli attributi dei singoli oggetti di tipo led.
	public String getHexIntensityFromSingularLed()
	{
		return this.red.getHexIntensity() + this.green.getHexIntensity() + this.blue.getHexIntensity();	
	}
	
}
