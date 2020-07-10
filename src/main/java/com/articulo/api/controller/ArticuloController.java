package com.articulo.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.articulo.api.entity.Articulo;
import com.articulo.api.service.ArticuloService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api/articulo")
public class ArticuloController {
	
	 private static final Logger LOG = LoggerFactory.getLogger(ArticuloController.class);
	 
	@Autowired
	private ArticuloService articuloService;
	
	@Autowired
	private Gson gson;

	@GetMapping("/listar")
	public List<Articulo> listarArticulos(){
		return articuloService.listarArticulos();
		
	}
	
	@KafkaListener(topics = { "articulo" })
	public void guardarArticulo(@RequestBody String articulo) {
		
		Articulo articuloNew = null;
		System.out.println("Kafka event consumed is antes: " + articulo);
		Articulo model = gson.fromJson(articulo, Articulo.class);
		System.out.println("Model converted value despues: " + model.toString());
		
			try {
			articuloNew = articuloService.save(model);
			LOG.info("Se creo el Articulo::"+articuloNew.toString());
		} catch(DataAccessException e) {
			LOG.error(e.getMessage());
		}
		
	}
	
//	@PostMapping("/adicionar")
//	public ResponseEntity<?> create( @RequestBody Articulo articulo, BindingResult result) {
//		
//		Articulo articuloNew = null;
//		Map<String, Object> response = new HashMap<>();
//		
//		if(result.hasErrors()) {
//
//			List<String> errors = result.getFieldErrors()
//					.stream()
//					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
//					.collect(Collectors.toList());
//			
//			response.put("errors", errors);
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
//		}
//		
//		try {
//			articuloNew = articuloService.save(articulo);
//		} catch(DataAccessException e) {
//			response.put("mensaje", "Error al realizar el insert en la base de datos");
//			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		
//		response.put("mensaje", "El articulo ha sido creado con éxito!");
//		response.put("cliente", articuloNew);
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
//	}
	
}
