package com.formaciondbi.springboot.app.items.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.formaciondbi.springboot.app.items.clientes.ProductoClienteRest;
import com.formaciondbi.springboot.app.items.models.Item;

@Service("serviceFeign")
public class ItemServiceFeign implements ItemService{

	@Autowired
	private ProductoClienteRest clienteFeign;
	
	@Override
	public List<Item> findAll() {
		
		return clienteFeign.listar().stream().map(m -> new Item(m,1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		
		return new Item(clienteFeign.detalle(id), cantidad);
	}

}
