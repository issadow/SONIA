/*
 * SolicitarDatos.java
 *
 * Creado 23 de abril de 2007, 12:52
 *
 * Telefonica I+D Copyright 2006-2007
 */
package icaro.aplicaciones.agentes.AgenteAplicacionDialogoCitasCognitivo.tareas;

import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.comuSONIAChat.ItfUsoComuSONIAChat;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

/**
 *
 * @author F Garijo
 */
public class SolicitarInfoInicialCita extends TareaSincrona {
	private String identAgenteOrdenante;
	private Objetivo contextoEjecucionTarea = null;

	@Override
	public void ejecutar(Object... params) {
		// String identRecursoVisualizacionAcceso = "VisualizacionAcceso1";
		String identDeEstaTarea = this.getIdentTarea();
		String identAgenteOrdenante = this.getIdentAgente();
		String identRecursoComunicacionChat = (String) params[0];
		try {
			// // Se busca la interfaz del recurso en el repositorio de
			// interfaces
			
			
			ItfUsoComuSONIAChat recComunicacionChat = (ItfUsoComuSONIAChat) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfaz(
						NombresPredefinidos.ITF_USO + identRecursoComunicacionChat); 
			if (recComunicacionChat!=null){
                recComunicacionChat.comenzar(identAgenteOrdenante);
               recComunicacionChat.enviarMensagePrivado(VocabularioGestionCitas.SaludoInicial1);
           } else {
				identAgenteOrdenante = this.getAgente().getIdentAgente();
				this.generarInformeConCausaTerminacion(
						identDeEstaTarea,
						contextoEjecucionTarea,
						identAgenteOrdenante,
						"Error-AlObtener:Interfaz:"
								+ VocabularioGestionCitas.IdentRecursoComunicacionChat,
						CausaTerminacionTarea.ERROR);
			}
		} catch (Exception e) {
			this.generarInformeConCausaTerminacion(
					identDeEstaTarea,
					contextoEjecucionTarea,
					identAgenteOrdenante,
					"Error-Acceso:Interfaz:"
							+ VocabularioGestionCitas.IdentRecursoComunicacionChat,
					CausaTerminacionTarea.ERROR);
			e.printStackTrace();
		}
	}
}
