/**
 * 
 */
package com.rockcor.model;

/**
 * @author ricardodelgadocarreno
 *
 */
public class Persona {
	
	private Integer idPersona;
	private String nombre;
	
	/**
	 * @param idPersona
	 * @param nombre
	 */
	public Persona(Integer idPersona, String nombre) {
		super();
		this.idPersona = idPersona;
		this.nombre = nombre;
	}

	/**
	 * @return the idPersona
	 */
	public Integer getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona the idPersona to set
	 */
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	} 
	
	

}
