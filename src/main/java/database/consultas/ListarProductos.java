package database.consultas;

import entities.ProductosEntity;
import entities.VentaprodEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ListarProductos {
    public static void listarProductosNumUnidades(EntityManager em){
        List<ProductosEntity> listaProductos = em.createQuery("from ProductosEntity ", ProductosEntity.class).getResultList();
        int i = 0;
        //Liston todos los producto
        System.out.println("Lista Productos: ");
        for(ProductosEntity productos: listaProductos){
            int numProductosVendidosTotal = 0;
            //Número de productos en modo de contador
            i++;
            System.out.print(i+". "+ productos.getDescripcion());
            List<VentaprodEntity> numProductosVendidos = em.createQuery("from VentaprodEntity where idProducto = ?1 ", VentaprodEntity.class).setParameter(1,productos.getId()).getResultList();
            //Sumo todos los productos que ha vendido teniendo en cuenta las unidades
            for(VentaprodEntity numProductoVendido: numProductosVendidos){
                numProductosVendidosTotal += numProductoVendido.getUnidades();
            }
            System.out.println("     Núm. Productos Vendidos: " + numProductosVendidosTotal);
        }
    }
}
