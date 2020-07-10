package com.articulo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.articulo.api.entity.Articulo;

public interface ArticuloRepository extends JpaRepository<Articulo, Long>{

}
