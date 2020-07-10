package com.articulo.api.service;

import java.util.List;

import com.articulo.api.entity.Articulo;

public interface ArticuloService {

	public List<Articulo> listarArticulos();
	
	public Articulo save(Articulo articulo);

}
