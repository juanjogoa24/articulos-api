package com.articulo.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.articulo.api.entity.Articulo;
import com.articulo.api.repository.ArticuloRepository;

@Service
public class ArticuloServiceImpl implements ArticuloService {

	@Autowired
	ArticuloRepository articuloRepository;

	@Override
	public List<Articulo> listarArticulos() {

		return articuloRepository.findAll();

	}
	
	@Transactional
	public Articulo save(Articulo articulo) {
		return articuloRepository.save(articulo);
	}

}
