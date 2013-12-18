import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.SimTime;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double a[] = {6,6};
		double b[] = {60,60};
		int c[] = {20,20};
		ModeloHospital hospital = new ModeloHospital(null, a, b, c, true, true);
		Prueba_Hospital prueba = new Prueba_Hospital();
		prueba.test(hospital);
		//Crear un hospital
		Experiment experimento = new Experiment("HospitalSimple");
		
		//Conecta el modelo con el hospital
		hospital.connectToExperiment(experimento);
		
		//preparacion de la simulacion
		experimento.setShowProgressBar(true);
		experimento.stop(new SimTime(24*30));
		
		//Activar el registro de periodo
		experimento.tracePeriod(new SimTime(0.0) ,new SimTime(24*30));
		
		//Comienzo de la simulación
		experimento.start();
		
		//Se crea el reporte
		experimento.report();
		
		//Termina el experimento
		experimento.finish();
	}

}
