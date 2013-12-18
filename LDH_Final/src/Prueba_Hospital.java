import static org.junit.Assert.*;

import org.junit.Test;


public class Prueba_Hospital {

	public void test(ModeloHospital hospital) {
		assertEquals(0, hospital.getCamas());
		hospital.liberarCama();
		assertEquals(1, hospital.getCamas());
		hospital.tomarCama();
		assertEquals(0, hospital.getCamas());
	}

}
