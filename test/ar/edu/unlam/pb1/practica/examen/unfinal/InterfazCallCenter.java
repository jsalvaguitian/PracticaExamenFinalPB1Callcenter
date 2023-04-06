package ar.edu.unlam.pb1.practica.examen.unfinal;

import java.util.Scanner;

public class InterfazCallCenter {
	private static final int INCORPORAR_ZONA_COBERTURA = 1, DAR_DE_ALTA_NUEVO_CONTACTO = 2, REALIZAR_NUEVA_LLAMADA = 3,
			VER_INFORMACION_DEL_CONTACTO = 4, SALIR = 9;

	public static void main(String args[]) {

		Scanner teclado = new Scanner(System.in);
		Empresa unaEmpresa = new Empresa("Oeste Cable Color");

		int opcion = 0;

		System.out.println("Bienvenido al callcenter");

		do {
			mostrarMenu();
			opcion = teclado.nextInt();

			switch (opcion) {

			case INCORPORAR_ZONA_COBERTURA:
				incorporarZonaDeCobertura(teclado, unaEmpresa);
				break;

			case DAR_DE_ALTA_NUEVO_CONTACTO:
				darDeAltaNuevoContacto(teclado, unaEmpresa);
				break;

			case REALIZAR_NUEVA_LLAMADA:
				realizarNuevaLlamada(teclado, unaEmpresa);
				break;

			case VER_INFORMACION_DEL_CONTACTO:
				verInformacionDelContacto(teclado, unaEmpresa);

				break;

			case SALIR:
				System.out.println("Hasta la proxima! :-) ");
				break;

			default:
				System.out.println("Opcion inválida. Intente de nuevo");
			}

		} while (opcion != SALIR);
	}

	private static void mostrarMenu() {
		System.out.println("************************");
		System.out.println("Menu de opciones\n");
		System.out.println("1 - Incorporar zona de cobertura");
		System.out.println("2 - Dar de alta un nuevo contacto");
		System.out.println("3 - Realizar nueva llamada");
		System.out.println("4 - Ver información del contacto");
		System.out.println("9 - Salir");
		System.out.println("************************");
		System.out.println("Ingrese su opción: ");
	}

	/*
	 * Se registra un nuevo código postal dentro de la zona de cobertura de la
	 * empresa
	 */
	private static void incorporarZonaDeCobertura(Scanner teclado, Empresa unaEmpresa) {

		System.out.print("Ingrese un codigo postal: ");
		int codigoPostal = teclado.nextInt();

		if (unaEmpresa.agregarNuevaZonaDeCobertura(codigoPostal))
			System.out.println("El codigo postal " + codigoPostal + " fue guardado correctamente.");
		else {
			System.out.println("Algo salió mal. Lo sentimos");
		}
	}

	/*
	 * Registra un nuevo contacto en la empresa String nombreYApellido, String
	 * celular, String email, String direccion, int codigoPostal, String localidad,
	 * Provincia provincia
	 */
	private static void darDeAltaNuevoContacto(Scanner teclado, Empresa unaEmpresa) {
		String nombreYApellido;
		String celular;
		String mail;
		String direccion;
		int codigoPostal;
		String localidad;
		int opcionProvincia;
		Provincia[] provincias = Provincia.values();
		Provincia provincia;
		Contacto nuevo;

		do {
			System.out.println("Ingrese su nombre y apellido: ");
			teclado.nextLine();
			nombreYApellido = teclado.nextLine();

			if (!Contacto.esNombreYApellidoOtraFormaDeValidar(nombreYApellido))
				System.out.println("El nombre y/o apellido no debe contener número.Intente de nuevo ");
		} while (!Contacto.esNombreYApellidoOtraFormaDeValidar(nombreYApellido));

		System.out.println("Ingrese su celular: ");
		celular = teclado.next();

		do {
			System.out.println("Ingrese su correo electrónico: ");
			mail = teclado.next();
			if (!Contacto.esEmailValido(mail))
				System.out.println("El email no contiene puntos y/o un solo @. Por favor, intente de nuevo");
		} while (!Contacto.esEmailValido(mail));

		System.out.println("Ingrese su direccion: ");
		teclado.nextLine();
		direccion = teclado.nextLine();

		System.out.println("Ingrese su codigo postal: ");
		codigoPostal = teclado.nextInt();

		System.out.println("Ingrese su localidad: ");
		localidad = teclado.next();

		do {
			mostrarMenuDeProvincias(provincias);
			opcionProvincia = teclado.nextInt();
			if(opcionProvincia <= 0 || opcionProvincia >= provincias.length)
				System.out.println("Opcion inválida. Intente de nuevo");
		} while (opcionProvincia <= 0 || opcionProvincia >= provincias.length);
		
		
		provincia = provincias[opcionProvincia - 1];

		nuevo = new Contacto(nombreYApellido, celular, mail, direccion, codigoPostal, localidad, provincia);

		if (unaEmpresa.agregarNuevoContacto(nuevo)) {
			System.out.println("Se agrego el nuevo contacto correctamente.");
		} else {
			System.out.println("Algo salió mal.");
		}

	}

	private static void mostrarMenuDeProvincias(Provincia[] provincias) {
		System.out.println("Provincias: ");
		for (int i = 0; i < provincias.length; i++) {
			System.out.println((i + 1) + ") -" + provincias[i].name());
		}
		System.out.println("Seleccione su provincia: ");
	}

	/*
	 * Busca un candidato, muestra los datos del mismo, y permite almacenar el
	 * resultado de la llamada.
	 * 
	 * a. Si la llamada fue exitosa (en ese caso el contacto pasa a ser cliente, y
	 * no se lo debe volver a llamar). b. Si el contacto no desea ser llamado
	 * nuevamente (la llamada pudo no haber sido exitosa, pero se haga un nuevo
	 * intento en el futuro). c. Observaciones: Texto libre donde el operador deja
	 * registro de lo conversado.
	 */
	private static void realizarNuevaLlamada(Scanner teclado, Empresa unaEmpresa) {
		Contacto candidato;
		Llamada llamada;
		boolean fueExitosa;
		boolean deseaSerLlamado;
		String pregunta;
		String mensajePorInconveniente;
		String observaciones;
		System.out.println("Buscando un contacto... ");

		if (unaEmpresa.buscarCandidato() != null) {
			candidato = unaEmpresa.buscarCandidato();
			System.out.println("Ya encontramos nuestro candidato a llamar.\n" + candidato);

			System.out.println("Registrando la llamada...");
			
			pregunta = "¿La llamada fue exitosa? (0:No | 1:Si)";
			mensajePorInconveniente = "Intente de nuevo. Si fue exitosa marque 1 (uno), si no fue exitosa marque 0 (cero).";
			
			fueExitosa = consultarSobreLaLlamada(teclado,pregunta,mensajePorInconveniente);
			
			if(fueExitosa) {
				candidato.setEsCliente(true);
				candidato.setEsDeseadoSerLlamado(false);
			}
			
			pregunta = "¿Desea ser llamado nuevamente?";	
			mensajePorInconveniente = "Intente de nuevo. Si desea ser llamado marque 1(uno), si no desea ser llamado marque 0(cero)";
			deseaSerLlamado = consultarSobreLaLlamada(teclado,pregunta,mensajePorInconveniente);
			candidato.setEsDeseadoSerLlamado(deseaSerLlamado);
			
			System.out.println("Ingrese sus observaciones: ");
			observaciones = teclado.nextLine();
		
			llamada = new Llamada(fueExitosa, observaciones);

			candidato.registrarNuevaLlamada(llamada);

		}else {
			System.out.println("Algo salio mal. ¯\\_(ツ)_/¯");
		}
	}

	private static boolean consultarSobreLaLlamada(Scanner teclado, String consulta, String mensajeErrorDeOpcion) {
		int fueExitosa;
		boolean exito = false;
		do {
			System.out.println(consulta);
			fueExitosa = teclado.nextInt();

			switch (fueExitosa) {
			case 0:
				exito = false;
				break;
			case 1:
				exito = true;
				break;
			default:
				System.out.println(mensajeErrorDeOpcion);
				break;
			}
		} while (fueExitosa < 0 && fueExitosa > 1);
		
		return exito;
	}

	
	/*
	 * Se visualiza la información del contacto, incluso el listado de las llamadas
	 * que se le hicieron
	 */
	private static void verInformacionDelContacto(Scanner teclado, Empresa unaEmpresa) {
		String nombreYApellido; 
		Contacto candidatoBuscado;
		
		System.out.println("Ingrese el nombre y el apellido: ");
		teclado.nextLine();
		nombreYApellido = teclado.nextLine();
		
		
		candidatoBuscado = unaEmpresa.buscarPorNombreCandidato(nombreYApellido);
		
		if(candidatoBuscado!=null) {
			System.out.println(candidatoBuscado);
		}else {
			System.out.println("El candidato no existe.");
		}
		
	}

}
