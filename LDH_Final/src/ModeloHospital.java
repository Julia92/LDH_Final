import desmoj.core.dist.ContDistExponential;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.ProcessQueue;
import desmoj.core.simulator.TimeSpan;

public class ModeloHospital extends Model {

	//Parámetros de entrada del sistema
	public ProcessQueue pacientesEsperando;
	private int cantidadCamas;
	private double mediaLlegadas;
	private double mediaEstancia;
	public ContDistExponential arribos;
	public ContDistExponential estancias;
	private int camas;
	
	//Parametros de entrada Emergencia
	public ProcessQueue pacientesEmergencia;
	private int cantidadCamasEmergencia;
	private double mediaLlegadasEmergencia;
	private double mediaEstanciaEmergencia;
	public ContDistExponential arribosEmergencia;
	public ContDistExponential estanciaEmergencia;
	private int camasEmergencia;
	
	public ModeloHospital(Model owner, double mediaLlegadas[], double mediaEstancia[], int cantidadCamas[], boolean showInReport, boolean showInTrace) {
		super(owner, "HospitalModel", showInReport, showInTrace);
		// TODO Auto-generated constructor stub
		this.mediaLlegadas = mediaLlegadas[0];
		this.mediaEstancia = mediaEstancia[0];
		this.cantidadCamas = cantidadCamas[0];
		
		this.mediaLlegadasEmergencia = mediaLlegadas[1];
		this.mediaEstancia = mediaEstancia[1];
		this.cantidadCamasEmergencia = cantidadCamas[1];
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "Hospital Simple";
	}

	@Override
	public void doInitialSchedules() {
		// TODO Auto-generated method stub
		Pacientes dador = new Pacientes(this, true);
		dador.schedule(new TimeSpan(0.0));
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		pacientesEsperando = new ProcessQueue<>(this, "Pacientes Esperando", true, true);
		arribos =  new ContDistExponential(this, "Arribos", mediaLlegadas, true, true);
		estancias = new ContDistExponential(this, "Estancias", mediaEstancia, true, true);
		camas = cantidadCamas;
		
		pacientesEmergencia = new ProcessQueue(this, "Pacientes Emergencia", true, true);
		arribosEmergencia = new ContDistExponential(this, "Arribos a Emergencia", mediaLlegadasEmergencia, true, true);
		estanciaEmergencia = new ContDistExponential(this, "Estancia Emergencia", mediaLlegadasEmergencia, true, true);
		camasEmergencia = cantidadCamasEmergencia;
	}
	
	public boolean hayCamasLibres () {
		System.out.println("hay camas libres");
		return (camas > 0);
	}
	
	public void tomarCama() {
		System.out.println("Tomando una cama");
		camas--;
	}
	
	public void liberarCama() {
		System.out.println("Dejando una cama");
		camas++;
	}
	
	public boolean hayCamasLibresEmergencia() {
		System.out.println("Hay camas libres de emergencia");
		return (camasEmergencia > 0);
	}
	
	public void tomarCamaEmergencia() {
		System.out.println("Tomando una cama de emergencia");
		camasEmergencia--;
	}
	
	public void liberarCamaEmergencia() {
		System.out.println("Dejando una cama de emergencia");
		camasEmergencia++;
	}
}
