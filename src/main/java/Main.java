import database.EmfSingleton;
import database.consultas.InsertarVentas;
import database.consultas.ListarClientes;
import database.consultas.ListarProductos;
import database.consultas.ListarVentas;
import entities.ClientesEntity;
import entities.ProductosEntity;
import entities.VentaprodEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import libs.Leer;

import java.sql.Date;
import java.util.List;

public class Main {
    static EntityManager em = EmfSingleton.getInstance().getEmf().createEntityManager();
    static EntityTransaction transaction = em.getTransaction();
    public static void main(String[] args){
        boolean salir = false;
        do{
            String guiones = "-".repeat(20);
            int menu;
            System.out.println(guiones);
            System.out.println("1. Listar Clientes y el número de articulos que ha comprado");
            System.out.println("2. Listar los diferentes productos y el número de unidades vendidas");
            System.out.println("3. Insertar una venta");
            //Obtener un listado de ventas de un cliente (usa una consulta HQL para obtener la información).
            // El listado se realizará en un método que recibirá como parámetro el id del cliente y mostrará
            // la información como se muestra en la imagen adjunta
            System.out.println("4. Obtener un listado de ventas de un cliente");
            System.out.println("0. Salir");
            System.out.println(guiones);
            menu = Leer.introduceEntero("Introduce el número que desea ejecutar");
            if(menu==1){
                ListarClientes.listarClientesNumArticulos(em);
            } else if (menu==2) {
                ListarProductos.listarProductosNumUnidades(em);
            } else if (menu==3) {
                InsertarVentas.insertarVenta(em, transaction);
            } else if (menu==4) {
                ListarVentas.listadoVentas(em);
            } else if (menu==0) {
                salir = true;
            }else{
                System.out.println("Introduce un número válido");
            }
        }while(!salir);
        EmfSingleton.getInstance().getEmf().close();
    }
}
