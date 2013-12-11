import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimProcess;
import desmoj.core.simulator.TimeSpan;


public class Paciente extends SimProcess {
	private ModeloHospital myModel;
	private int id;

	public Paciente(Model owner, String name, int id, boolean showInTrace) {
		super(owner, name, showInTrace);
		this.id = id;
		myModel = (ModeloHospital)owner;
	}
	
	@Override
	public void lifeCycle() {
		// TODO Auto-generated method stub
		sendTraceNote("El paciente " + id + "llego al hospital.");
		if (!myModel.hayCamasLibres()) {
			myModel.pacientesEsperando.insert(this);
			passivate();
		}
		myModel.tomarCama();
		sendTraceNote("El paciente " + id + "tomó una cama.");
		hold(new TimeSpan(myModel.estancias.sample()));
		myModel.liberarCama();
		if (myModel.pacientesEsperando.length() > 0) {
			Paciente paciente = (Paciente) myModel.pacientesEsperando.first();
			myModel.pacientesEsperando.remove(paciente);
			paciente.activateAfter(this);
		}
		sendTraceNote("El paciente" + id + "se retira.");
	}
	
	
}
