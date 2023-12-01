package database.consultas;

import entities.ClientesEntity;
import entities.ProductosEntity;
import entities.VentaprodEntity;
import jakarta.persistence.EntityManager;
import libs.Leer;

import java.util.List;

public class ListarVentas {
    public static void listadoVentas(EntityManager em) {
        List<ClientesEntity> listaClientes = em.createQuery("from ClientesEntity ", ClientesEntity.class).getResultList();
        int i = 0, idClienteSeleccionado = 0;
        Double importeTotal = 0.0;
        String cliente, nombreClienteSeleccionado = "";
        boolean validacionCliente = false;
        //valido el cliente del que quiere ver las ventas
        while(!validacionCliente) {
            i=0;
            System.out.println("Lista clientes: ");
            for (ClientesEntity clientes : listaClientes) {
                //Número de cliente en modo de contador
                i++;
                System.out.println(i + ". " + clientes.getNombre());
            }
            cliente = Leer.introduceString("Introduce el nombre del cliente que quieres seleccionar");
            for (ClientesEntity clientes : listaClientes) {
                if (clientes.getNombre().equals(cliente)) {
                    validacionCliente = true;
                    idClienteSeleccionado = clientes.getId();
                    nombreClienteSeleccionado = clientes.getNombre();
                }
            }
        }
        //Hago el ticket bonito como se pide en la imagen
        List<VentaprodEntity> listaVentasCliente = em.createQuery("from VentaprodEntity where idCliente = ?1", VentaprodEntity.class).setParameter(1,idClienteSeleccionado).getResultList();
        System.out.println("---------------------------------------");
        System.out.println("Ventas del cliente: " + nombreClienteSeleccionado);
        System.out.println("---------------------------------------");
        //Primero recorro todas las ventas para poder hacer cada venta con sus datos
        for(VentaprodEntity ventaCliente: listaVentasCliente){
            System.out.println("Venta: " + ventaCliente.getId()+", Fecha venta: " + ventaCliente.getFecha());
            //Dentro de cada venta tengo que coger el producto que se ha vendido en esa venta exacta
            List<ProductosEntity> listaProductos = em.createQuery("from ProductosEntity where id = ?1 ", ProductosEntity.class).setParameter(1,ventaCliente.getIdProducto()).getResultList();
            for(ProductosEntity producto: listaProductos) {
                System.out.println("    Producto: " + producto.getDescripcion());
                System.out.println("    Cantidad: " + ventaCliente.getUnidades() + " PVP: " + producto.getPrecio());
                System.out.println("    Importe: " + ventaCliente.getUnidades()*producto.getPrecio());
                //sumo el importe de cada venta para tener el total para el final
                importeTotal = importeTotal + ventaCliente.getUnidades()*producto.getPrecio();
            }
        }
        //Acabo el ticket mostrando datos totales al usuario por consola
        System.out.println("---------------------------------------");
        System.out.println("Número total de ventas: " + listaVentasCliente.size());
        System.out.println("Importe Total: " + importeTotal);
        System.out.println("---------------------------------------");
    }
}
