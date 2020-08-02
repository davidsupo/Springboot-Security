package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.ProductoService;

@RequestMapping("/producto")
@RestController
public class ProductoController {
	@Autowired
	private ProductoService productoService;
	
	@GetMapping()
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok(productoService.findAll());
	}
}
