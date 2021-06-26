package com.rockcor.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/mostrar")
	public Mono<Persona> showPerson() {

		return Mono.just(new Persona(1, "Ricardo"));

	}

	@GetMapping("/get")
	public Mono<ResponseEntity<Persona>> showPersons(){

		return Mono.just(new ResponseEntity<Persona>(new Persona(1, "Ricardo"), HttpStatus.OK));
	}

	@GetMapping("/getAll")
	public Flux<Persona> getAll(){

		List<Persona> personas = new ArrayList<Persona>();

		personas.add(new Persona(1, "Ricardo"));
		personas.add(new Persona(2, "Lilly"));

		return Flux.fromIterable(personas);
	}

	@PostMapping()
	public Mono<ResponseEntity<Flux<Persona>>> getAllPersons(){
		List<Persona> personas = new ArrayList<Persona>();

		personas.add(new Persona(1, "Ricardo"));
		personas.add(new Persona(2, "Lilly"));

		Flux<Persona> personasFlux = Flux.fromIterable(personas);
		
		return Mono.just(new ResponseEntity<Flux<Persona>>(personasFlux, HttpStatus.OK));
	}
}
