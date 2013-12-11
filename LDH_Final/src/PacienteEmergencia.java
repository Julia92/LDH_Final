import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimProcess;
import desmoj.core.simulator.TimeSpan;


public class PacienteEmergencia extends SimProcess {
	private ModeloHospital myModel;
	private int id;

	public PacienteEmergencia(Model owner, String name, int id, boolean showInTrace) {
		super(owner, name, showInTrace);
		this.id = id;
		myModel = (ModeloHospital)owner;
	}
		
	@Override
	public void lifeCycle() {
		// TODO Auto-generated method stub
		sendTraceNote("El paciente " + id + "llego al hospital a Emergencias.");
		if (!myModel.hayCamasLibresEmergencia()) {
			myModel.pacientesEmergencia.insert(this);
			passivate();
		}
		myModel.tomarCama();
		sendTraceNote("El paciente " + id + "tomo una cama en Emergencias.");
			hold(new TimeSpan(myModel.estancias.sample()));
			myModel.liberarCamaEmergencia();
			if (myModel.pacientesEsperando.length() > 0) {
				PacienteEmergencia pacienteEmergencia = (PacienteEmergencia) myModel.pacientesEsperando.first();
				myModel.pacientesEmergencia.remove(pacienteEmergencia);
				pacienteEmergencia.activateAfter(this);
			}
			sendTraceNote("El paciente" + id + "se retira de Emergencias.");
		}
}
