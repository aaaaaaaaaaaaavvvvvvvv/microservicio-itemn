package com.formaciondbi.springboot.app.items.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.formaciondbi.springboot.app.items.models.Item;
import com.formaciondbi.springboot.app.items.models.Producto;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService{

	@Autowired
	private RestTemplate clienteRest;
	
	@Override
	public List<Item> findAll() {
		List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://servicio-productos/listar", Producto[].class ));
		return productos.stream().map( m -> new Item(m,1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Map<String,Object> pathVariables = new HashMap<>();
		pathVariables.put("id",id.toString());
		Producto producto = clienteRest.getForObject("http://servicio-productos:8001/ver/{id}", Producto.class, pathVariables);
		return new Item(producto, cantidad);
	}

}
