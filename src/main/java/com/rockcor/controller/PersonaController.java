package com.rockcor.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rockcor.model.Persona;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

	private static final Logger Log = LoggerFactory.getLogger(PersonaController.class);

	/**
	 * Mostrar una persona
	 * 
	 * @return
	 */
	@GetMapping("/mostrar")
	public Mono<Persona> showPerson() {

		return Mono.just(new Persona(1, "Ricardo"));
	}

	/**
	 * Obtener una persona
	 * 
	 * @return
	 */
	@GetMapping("/get")
	public Mono<ResponseEntity<Persona>> showPersons() {

		return Mono.just(new ResponseEntity<Persona>(new Persona(1, "Ricardo"), HttpStatus.OK));
	}

	/**
	 * Obtener todas las personas
	 * 
	 * @return
	 */
	@GetMapping("/getAll")
	public Flux<Persona> getAll() {

		List<Persona> personas = new ArrayList<Persona>();

		personas.add(new Persona(1, "Ricardo"));
		personas.add(new Persona(2, "Lilly"));

		return Flux.fromIterable(personas);
	}

	/**
	 * Obtener todas las personas con responseEntity
	 * 
	 * @return
	 */
	@PostMapping()
	public Mono<ResponseEntity<Flux<Persona>>> getAllPersons() {
		List<Persona> personas = new ArrayList<Persona>();

		personas.add(new Persona(1, "Ricardo"));
		personas.add(new Persona(2, "Lilly"));

		Flux<Persona> personasFlux = Flux.fromIterable(personas);

//		return Mono.just(new ResponseEntity<Flux<Persona>>(personasFlux, HttpStatus.OK));
		return Mono.just(ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(personasFlux));
	}
	
	@DeleteMapping("/{modo}")
	public Mono<ResponseEntity<Void>> deletePerson(@PathVariable("modo") Integer modo){
		return buscarPersona(modo)
				.flatMap(p -> {
					return eliminar(p)
							.then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
				})
				.defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND))
				.onErrorReturn(new ResponseEntity<Void>(HttpStatus.BAD_REQUEST));
	}
	
	/**
	 * Eliminar una persona
	 * @param p
	 * @return
	 */
	private Mono<Void> eliminar(Persona p){
		
		Log.info("Eliminando " + p.getIdPersona() + p.getNombre());
		
		return Mono.empty();
	}
	
	/**
	 * Buscar una persona
	 * @param modo
	 * @return
	 */
	private Mono<Persona> buscarPersona(Integer modo){
		
		return modo == 1 ? Mono.just(new Persona(1, "Ricardo D")) : Mono.empty();
	}

	
	@PostMapping("/numbers")
	public Flux<?> numbers() {

		int[] numbers = { 1, 2, 3, 4, 5 };

		return null;
	}

}
