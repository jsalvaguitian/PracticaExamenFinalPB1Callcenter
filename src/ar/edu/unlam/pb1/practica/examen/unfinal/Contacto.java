package ar.edu.unlam.pb1.practica.examen.unfinal;

import java.util.Arrays;

/*
 * Se deben incorporar los atributos necesarios.
 * 
 *	Nombre y Apellido: No se aceptan números.
 *	Celular: Compuesto del código de país + código de área + número de celular.
 *	E-Mail: Debe contener al menos el símbolo @ y el caracter '.'.
 *	Dirección: Valor alfanumérico.
 *	Código Postal: Valor numérico.
 *	Localidad: Valor alfanumérico.
 *	Provincia. Enumerador que contenga las 23 provincias argentinas.
 *	Es cliente (Si o No): Inicialmente se carga en No.
 *	Desea ser llamado nuevamente (Si o No): Inicialmente se carga en Si.
 */
public class Contacto {
	private String nombreYApellido;
	private String celular;
	private String email;
	private String direccion;
	private int codigoPostal;
	private String localidad;
	private Provincia provincia;
	private boolean esCliente;
	private boolean esDeseadoSerLlamado;
	private Llamada[] llamadas;
	private final int CANTIDAD_MAX_LLAMADAS;

	public Contacto(String nombreYApellido, String celular, String email, String direccion, int codigoPostal,
			String localidad, Provincia provincia) {

		this.nombreYApellido = nombreYApellido;
		this.celular = celular;
		this.email = email;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.localidad = localidad;
		this.provincia = provincia;
		this.esCliente = false;
		this.esDeseadoSerLlamado = true;
		this.CANTIDAD_MAX_LLAMADAS = 100;
		this.llamadas = new Llamada[this.CANTIDAD_MAX_LLAMADAS];
	}

	/*-------------------------------------------------------------------------------------------------------------------------------
	 * Nombre y Apellido: No se aceptan números.
	 * Deberas aplicar contains()
	 * BONUS: no nos pidió este metodo pero igual lo haremos porque aparece en la consigna
	 * */
	public boolean esNombreYApellidoValido(String nombreYApellido) {
		if (nombreYApellido.contains("0") || nombreYApellido.contains("1") || nombreYApellido.contains("2")
				|| nombreYApellido.contains("3") || nombreYApellido.contains("4") || nombreYApellido.contains("5")
				|| nombreYApellido.contains("6") || nombreYApellido.contains("7") || nombreYApellido.contains("8")
				|| nombreYApellido.contains("9"))
			return false;
		else
			return true;

	}

	/*
	 * 2DA OPCION Recordatorio: los numeros en codigo ASCII va desde 48 a 57
	 */

	public static boolean esNombreYApellidoOtraFormaDeValidar(String nombreYApellido) {
		char[] vectorNombreYApellido = nombreYApellido.toCharArray();
		int numeroCharCero = 48;
		int numeroCharNuevo = 57;

		for (int i = 0; i < vectorNombreYApellido.length; i++) {
			for (int j = numeroCharCero; j <= numeroCharNuevo; j++) {
				if (vectorNombreYApellido[i] == (char) j)
					return false;
			}
		}

		return true;

	}
	// --------------------------------------------------------------------------------------------------------------------------------

	/*
	 * Evalúa si un String determinado puede ser almacenado como E-MAIL. E-Mail:
	 * Debe contener al menos el símbolo @ y el caracter '.'. tenia un void y yo lo
	 * hago boolean BONUS: DEBERA CONTENER UN SOLO @. Aplicaremos charAt
	 */
	public static boolean esEmailValido(String eMail) {
		char[] vectorMail = eMail.toCharArray();
		boolean tienePuntito = false;
		boolean tieneUnArroba = false;
		int contadorArroba = 0;

		for (int i = 0; i < vectorMail.length; i++) {
			if (vectorMail[i] == '.')
				tienePuntito = true;
			if (vectorMail[i] == '@') {
				contadorArroba++;
			}
		}
		if (contadorArroba == 1)
			tieneUnArroba = true;

		if (tienePuntito && tieneUnArroba)
			return true;
		else
			return false;
	}

	/*
	 * Registra una nueva llamada asociada al contacto actual.
	 */
	public boolean registrarNuevaLlamada(Llamada nueva) {
		for (int i = 0; i < llamadas.length; i++) {
			if (nueva != null && this.llamadas[i] == null) {
				this.llamadas[i] = nueva;
				return true;
			}
		}
		return false;
	}
	

	
	public String getNombreYApellido() {
		return nombreYApellido;
	}

	public String getCelular() {
		return celular;
	}

	public String getEmail() {
		return email;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public Llamada[] getLlamadas() {
		return llamadas;
	}

	public int getCANTIDAD_MAX_LLAMADAS() {
		return CANTIDAD_MAX_LLAMADAS;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public boolean isEsCliente() {
		return esCliente;
	}

	public boolean isEsDeseadoSerLlamado() {
		return esDeseadoSerLlamado;
	}
	
	

	public void setEsCliente(boolean esCliente) {
		this.esCliente = esCliente;
	}

	public void setEsDeseadoSerLlamado(boolean esDeseadoSerLlamado) {
		this.esDeseadoSerLlamado = esDeseadoSerLlamado;
	}

	/*
	 * Muestra los datos de un contacto, entre los que se debe incluir el registro
	 * de las llamadas realizadas.
	 */
	public String toString() {
		String informacion=  "Contacto:"
				+ "\n Nombre y apellido: " + nombreYApellido 
				+ "\n Celular: " + celular 
				+ "\n email: " + email
				+ "\n Direccion:" + direccion 
				+ "\n codigoPostal: " + codigoPostal 
				+ "\n localidad: " + localidad
				+ "\n provincia: " + provincia
				+ "\n llamadas: ";
		
		for(int i=0; i< llamadas.length; i++) {
			if(llamadas[i]!=null) {
				informacion = "\n\t"+ i +". "+ llamadas[i].getObservaciones();
			}
		}
		
		return informacion;
				
	}
	
	/*+ ", esCliente=" + esCliente 
				+ ", esDeseadoSerLlamado="
				+ esDeseadoSerLlamado + ", llamadas=" + Arrays.toString(llamadas) + "]";*/

}
