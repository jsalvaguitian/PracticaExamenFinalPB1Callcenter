package ar.edu.unlam.pb1.practica.examen.unfinal;

public class Empresa {
	/*
	 * Se deben incorporar los atributos necesarios.
	 */
	
	private String nombre;
	private final int CANTIDAD_MAX_CONTACTOS;
	private Contacto[] contactos;
	private int[] zonasDeCobertura;
	
	public Empresa(String nombre) {
		this.nombre = nombre;
		this.CANTIDAD_MAX_CONTACTOS = 1000;
		this.contactos = new Contacto[this.CANTIDAD_MAX_CONTACTOS];
		this.zonasDeCobertura = new int[100];
	}
	
	
	/*
	 * Devuelve el nombre de la empresa
	 */
	public String getNombreEmpresa() {
		return this.nombre;
	}
	
	/*
	 * Incorpora un nuevo contacto a la empresa
	 */
	public boolean agregarNuevoContacto(Contacto nuevo) {
		for(int i=0; i<contactos.length; i++) {
			if(nuevo!=null && contactos[i]== null) {
				this.contactos[i]= nuevo;
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Incorpora una nueva zona de cobertura (Las zonas de cobertura se identifican por el codigo postal)
	 */
	public boolean agregarNuevaZonaDeCobertura(int codigoPostal) {
		for(int i=0; i<this.zonasDeCobertura.length; i++) {
			if(this.zonasDeCobertura[i]==0) {
				this.zonasDeCobertura[i] = codigoPostal;
				return true;
			}
		}
		
		return false;
	}	
	
	
	
	/*
	 * Determina si un código postal está dentro de la zona de cobertura
	 */
	private boolean elCodigoPostalEstaDentroDeLaZonaDeCobertura(int codigoPostal) {
		for(int i=0; i<this.zonasDeCobertura.length; i++) {
			if(this.zonasDeCobertura[i]!=0 && this.zonasDeCobertura[i]== codigoPostal)
				return true;
		}
		
		return false;
	}
	
	/*
	 * Para determinar qué contacto el sistema debe mostrar, se debe realizar la siguiente búsqueda:
	 * 1.	El contacto NO debe ser cliente aún.
	 * 2.	El contacto desea ser llamado o al menos no informó lo contrario.
	 * 3.	El código postal del contacto debe existir en las zonas de cobertura existente.
	 * 4.	Del CONJUNTO de contactos resultante se debe seleccionar aleatoriamente el próximo llamado.
	 */
	public Contacto buscarCandidato() {
		Contacto[] candidatos = new Contacto[this.contactos.length];
		int cantidadDeCandidatos = 0;
		
		for(int i=0; i<this.contactos.length; i++) {
			if(contactos[i]!=null && !contactos[i].isEsCliente() && 
					contactos[i].isEsDeseadoSerLlamado() && elCodigoPostalEstaDentroDeLaZonaDeCobertura(contactos[i].getCodigoPostal())) {
				candidatos[cantidadDeCandidatos] = contactos[i];
				cantidadDeCandidatos++;	
			}	
		}
		
		int nroCandidatoAleatorio = (int)Math.random()*(cantidadDeCandidatos);
		

		return candidatos[nroCandidatoAleatorio];
	}


	public Contacto buscarPorNombreCandidato(String nombreYApellido) {
		for(int i=0; i<this.contactos.length; i++) {
			if(this.contactos[i]!=null && (this.contactos[i].getNombreYApellido().equalsIgnoreCase(nombreYApellido)))
				return contactos[i];
		}
		
		return null;
	}

}
