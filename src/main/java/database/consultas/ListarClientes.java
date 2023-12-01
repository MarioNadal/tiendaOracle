package database.consultas;

import entities.ClientesEntity;
import entities.VentaprodEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ListarClientes {
    public static void listarClientesNumArticulos(EntityManager em){
        List<ClientesEntity> listaClientes = em.createQuery("from ClientesEntity ", ClientesEntity.class).getResultList();
        int i = 0;
        //Listo todos los clientes que exitsten con el numero de Articulos del cliente
        System.out.println("Lista clientes: ");
        for(ClientesEntity clientes: listaClientes){
            int numArticulosClienteTotal = 0;
            //Número de cliente en modo de contador
            i++;
            System.out.print(i+". " + clientes.getNombre());
            List<VentaprodEntity> numArticulosClinete = em.createQuery("from VentaprodEntity where idCliente = ?1 ", VentaprodEntity.class).setParameter(1,clientes.getId()).getResultList();
            //Sumo todas las unidades de todas las ventas del cliente para saber cuantos articulos ha comprado en total
            for(VentaprodEntity numArticuloCliente: numArticulosClinete){
                numArticulosClienteTotal += numArticuloCliente.getUnidades();
            }
            System.out.println("     Núm. Articulos Comprados: " + numArticulosClienteTotal);
        }
    }
}
