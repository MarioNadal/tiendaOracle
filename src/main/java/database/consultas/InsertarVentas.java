package database.consultas;

import database.consultas.Validaciones.ValidacionesPorID;
import entities.ClientesEntity;
import entities.ProductosEntity;
import entities.VentaprodEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import libs.Leer;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class InsertarVentas {
    public static void insertarVenta(EntityManager em, EntityTransaction transaction){
        int clienteIntroducido = 0, productoIntroducido = 0, unidadesIntroducidas;
        Date fechaIntroducida;
        boolean validarCliente=false, validarProducto=false;
        ValidacionesPorID.validarCliente(em,validarCliente,clienteIntroducido);
        ValidacionesPorID.validarProducto(em,validarProducto,productoIntroducido);
        //introduce las unidades que ha vendido
        unidadesIntroducidas = Leer.introduceEntero("Inroduce el número de unidades que se han comprado");
        //introduce la fecha o se introduce automáticamente la actual
        fechaIntroducida = Leer.introduceDateSQLOAutomaticamente();
        //Empiezo a coger los datos que se tendrán que actualizar en la base de datos
        try {
            transaction.begin();
            //Creo una Venta nuevo donde voy a añadir todos los datos que ha introducido el usuario
            VentaprodEntity insertVenta = new VentaprodEntity();
            //Introduzco todos los datos
            insertVenta.setIdCliente((short) clienteIntroducido);
            insertVenta.setIdProducto((short) productoIntroducido);
            insertVenta.setUnidades((short) unidadesIntroducidas);
            insertVenta.setFecha(fechaIntroducida);
            //Una vez metidos todos los datos inserto la venta en la base de datos
            em.persist(insertVenta);
            //hago que se actualize desde el commit begin en la base de datos
            transaction.commit();
        }catch (Exception ex){
            System.out.println("Error a la hora de hacer el transaction");
        }
        //Conmfirmo al usuario que se ha podido introducir la venta ya que nada se ve en consola
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Venta introducida correctamente");
        System.out.println("-------------------------------------------------------------------------------");
    }
}
