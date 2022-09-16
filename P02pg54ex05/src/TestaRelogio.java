public class TestaRelogio {
	public static void main(String[] args) {
		Relogio cassio = new Relogio();
		Relogio rolex = new Relogio(10,30,0);
		
		cassio.setHora(12);
		cassio.setMinuto(20);
		cassio.setSegundo(30);
		
		cassio.exibeHorario();
		cassio.adiantar(5*24*3600);
		cassio.exibeHorario();

		System.out.println("-----------------------");
		
		rolex.exibeHorario();
		rolex.atrasar(50);
		rolex.exibeHorario();
	}
}