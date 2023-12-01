package database.consultas.Validaciones;

import entities.ClientesEntity;
import entities.ProductosEntity;
import jakarta.persistence.EntityManager;
import libs.Leer;

import java.util.List;

public class ValidacionesPorID {
    public static void validarCliente(EntityManager em, boolean validarCliente, int clienteIntroducido){
        //Hasta que no se  introduzca un cliente válido se seguirá pidiendo el cliente
        while(!validarCliente) {
            List<ClientesEntity> listaClientes = em.createQuery("from ClientesEntity ", ClientesEntity.class).getResultList();
            int i = 0;
            //Listo todos los clientes que hay
            System.out.println("Lista clientes: ");
            for (ClientesEntity clientes : listaClientes) {
                //Número de cliente en modo de contador
                i++;
                System.out.println(i + ". " + clientes.getNombre() + " ID: " + clientes.getId());
            }
            //Hago que introduzca el id del cliente que le aparece al lado del nombre del cliente en la lista anterior
            clienteIntroducido = Leer.introduceEntero("Introduce el id del cliente que ha hecho la compra");
            //Esta es la función que valida que el id sea de un cliente real
            for(ClientesEntity clientes : listaClientes){
                if(clientes.getId() == (short) clienteIntroducido){
                    validarCliente=true;
                }
            }
            if(!validarCliente){
                System.out.println("Introduce un id válido de la lista anterior");
            }
        }
    }
    public static void validarProducto(EntityManager em, boolean validarProducto, int productoIntroducido){
        //hago lo mismo pero con el producto a introducir
        while(!validarProducto){
            //Listo los productos para saber cúal ha comprado el cliente
            List<ProductosEntity> listaProductos = em.createQuery("from ProductosEntity ", ProductosEntity.class).getResultList();
            int y = 0;
            System.out.println("Lista Productos: ");
            for(ProductosEntity productos: listaProductos) {
                //Número de productos en modo de contador
                y++;
                System.out.println(y + ". " + productos.getDescripcion() + " ID: " + productos.getId());
            }
            productoIntroducido = Leer.introduceEntero("Introduce el producto que se ha comprado");
            for(ProductosEntity productos: listaProductos) {
                if(productos.getId()==productoIntroducido){
                    validarProducto=true;
                }
            }
            if(!validarProducto){
                System.out.println("Introduce un ID válido de la lista anterior");
            }
        }
    }
}
