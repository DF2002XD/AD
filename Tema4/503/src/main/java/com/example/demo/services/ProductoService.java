package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entitys.Producto;

@Service
public class ProductoService{

    private List<Producto> productos = new ArrayList<>();

    public List<Producto> obtenerProductos() {
        return productos;
    }

    public void guardarProducto(Producto producto) {
        producto.setId(productos.size() + 1);
        productos.add(producto);
    }

    public Producto buscarProductoPorId(long id) {
        return productos.stream()
                .filter(productos -> productos.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void eliminarProducto(long id) {
        productos.removeIf(productos -> productos.getId() == id);
    }

}
