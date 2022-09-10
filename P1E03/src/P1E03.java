
public class P1E03 {

	public static void main(String[] args) {
		// P1E03 - EXERCIO 03 DA PARTE 01 DA APOSTILA PAG 47
		
		// Declarando variaveis 
		
		double C = 26.0;
		double K;
		double F;
		double Re;
		double Ra;
		
		// Realizando calculos
		
		F = C * 1.8 + 32;
		K = C + 273.15;
		Ra = C * 1.8 + 32 + 459.67;
		Re = C * 0.8;
		
		// Mostrando resultados 
		
		System.out.println(" Temperatura em Celsius " + C);
		System.out.println(" Temperatura em Fahrenheit " + F);
		System.out.println(" Temperatura em Kelvin " + K);
		System.out.println(" Temperatura em Rankine " + Ra);
		System.out.println(" Temperatura em Reamur " + Re);

	}

}
