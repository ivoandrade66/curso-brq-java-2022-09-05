public class P1E05 {

	public static void main(String[] args) {
		// P1E05 - EXERCIO 05 DA PARTE 01 DA APOSTILA PAG 49
		
		double DP = 3.0, DG = 10.0, PP = 1.0, PM = 1.3, PG = 2.0, QTDLITROS;
		
		PM=(PP+PG)/2;
		QTDLITROS = DG*DP*PM*785;
		
		System.out.println("Profundidade Media = " + PM);
		System.out.println("Quantidade de Litros = " + QTDLITROS);
		
		

	}

}
