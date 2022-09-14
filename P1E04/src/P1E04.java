
public class P1E04 {

	public static void main(String[] args) {
		// P1E04 - EXERCIO 04 DA PARTE 01 DA APOSTILA PAG 48
		
		int hora, minuto, segundo, passado, faltam;
		
		hora = 10;
	    minuto = 9;
	    segundo = 53;
	    passado = segundo + minuto*60 + hora*3600; // Um minuto tem 60 segundos, uma hora tem 3600 segundos
	    faltam = 86400 - passado;  // Um dia tem 86400 segundos

	    System.out.println("Ja se passaram " + passado + " segundos desde o inicio do dia");
	    System.out.println("Faltam " + faltam + " segundos para terminar o dia");
		

	}

}
