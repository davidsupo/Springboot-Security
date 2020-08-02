package com.example.demo.services;

import java.util.List;

import com.example.demo.models.entity.Producto;



public interface ProductoService {
	public List<Producto> findAll();
	public Producto findById(Long id);
	public Producto save(Producto producto);
	public void deleteById(Long id);
}
