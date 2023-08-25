package com.alura.jdbc.controller;
import java.util.List;


import com.alura.jdbc.dao.ProductoDAO;
import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Producto;

public class ProductoController {
	
	private ProductoDAO productoDAO;
	
	public ProductoController() {
		this.productoDAO = new ProductoDAO(new ConnectionFactory().recuperaConexion());
	}


	public List<Producto> listar() {
		return productoDAO.listar();			
	}


    public void guardar(Producto producto, Object object) {
    	producto.setCategoriaId(object);
    	productoDAO.guardar(producto);
    }
    
    public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) {
        return productoDAO.modificar(nombre, descripcion, cantidad, id);
    }

    public int eliminar(Integer id) {
        return productoDAO.eliminar(id);
    }

	
}
