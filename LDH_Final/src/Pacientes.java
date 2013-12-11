import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;


public class Pacientes extends ExternalEvent {

	private int cuantos;
	private int cuantosEmergencia;
	@Override
	
	public void eventRoutine() {
		// TODO Auto-generated method stub
		ModeloHospital Model = (ModeloHospital)getModel();
		cuantos++;
		Paciente paciente = new Paciente(Model, "Paciente", cuantos, true);
		paciente.activate(new TimeSpan(0.0));
		schedule(new TimeSpan(Model.arribos.sample()));
		
		cuantosEmergencia++;
		PacienteEmergencia pacienteEmergencia = new PacienteEmergencia(Model, "Paciente Emergencia", cuantosEmergencia, true);
		pacienteEmergencia.activate();
		schedule(new TimeSpan(Model.arribosEmergencia.sample()));
	}
	
	public Pacientes(Model owner, boolean showInTrace){
		super(owner, "dadorPacientes", showInTrace);
		cuantos = 0;
	}
}
