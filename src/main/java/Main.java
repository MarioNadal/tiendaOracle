import database.EmfSingleton;
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
                listarClientesNumArticulos();
            } else if (menu==2) {
                listarProductosNumUnidades();
            } else if (menu==3) {
                insertarVenta();
            } else if (menu==4) {
                listadoVentas();
            } else if (menu==0) {
                salir = true;
            }else{
                System.out.println("Introduce un número válido");
            }
        }while(!salir);
        EmfSingleton.getInstance().getEmf().close();
    }

    public static void listarClientesNumArticulos(){
        List<ClientesEntity> listaClientes = em.createQuery("from ClientesEntity ", ClientesEntity.class).getResultList();
        int i = 0;
        System.out.println("Lista clientes: ");
        for(ClientesEntity clientes: listaClientes){
            int numArticulosClienteTotal = 0;
            //Número de cliente en modo de contador
            i++;
            System.out.print(i+". " + clientes.getNombre());
            List<VentaprodEntity> numArticulosClinete = em.createQuery("from VentaprodEntity where idCliente = ?1 ", VentaprodEntity.class).setParameter(1,clientes.getId()).getResultList();
            for(VentaprodEntity numArticuloCliente: numArticulosClinete){
                numArticulosClienteTotal += numArticuloCliente.getUnidades();
            }
            System.out.println("     Núm. Articulos Comprados: " + numArticulosClienteTotal);
        }
    }

    public static void listarProductosNumUnidades(){
        List<ProductosEntity> listaProductos = em.createQuery("from ProductosEntity ", ProductosEntity.class).getResultList();
        int i = 0;
        System.out.println("Lista Productos: ");
        for(ProductosEntity productos: listaProductos){
            int numProductosVendidosTotal = 0;
            //Número de productos en modo de contador
            i++;
            System.out.print(i+". "+ productos.getDescripcion());
            List<VentaprodEntity> numProductosVendidos = em.createQuery("from VentaprodEntity where idProducto = ?1 ", VentaprodEntity.class).setParameter(1,productos.getId()).getResultList();
            for(VentaprodEntity numProductoVendido: numProductosVendidos){
                numProductosVendidosTotal += numProductoVendido.getUnidades();
            }
            System.out.println("     Núm. Productos Vendidos: " + numProductosVendidosTotal);
        }
    }

    public static void insertarVenta(){
        int clienteIntroducido = 0, productoIntroducido = 0, unidadesIntroducidas;
        Date fechaIntroducida;
        boolean validarCliente=false, validarProducto=false;
        //Listo los clientes para saber cúal ha hecho la compra
        while(!validarCliente) {
            List<ClientesEntity> listaClientes = em.createQuery("from ClientesEntity ", ClientesEntity.class).getResultList();
            int i = 0;
            System.out.println("Lista clientes: ");
            for (ClientesEntity clientes : listaClientes) {
                //Número de cliente en modo de contador
                i++;
                System.out.println(i + ". " + clientes.getNombre() + " ID: " + clientes.getId());
            }
            clienteIntroducido = Leer.introduceEntero("Introduce el id del cliente que ha hecho la compra");
            for(ClientesEntity clientes : listaClientes){
                if(clientes.getId() == (short) clienteIntroducido){
                    validarCliente=true;
                }
            }
            if(!validarCliente){
                System.out.println("Introduce un id válido de la lista anterior");
            }
        }
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
        unidadesIntroducidas = Leer.introduceEntero("Inroduce el número de unidades que se han comprado");
        fechaIntroducida = Leer.introduceDateSQL("Introduce la fecha de la venta (yyyy-mm-dd)");
        transaction.begin();
        VentaprodEntity insertVenta = new VentaprodEntity();
        insertVenta.setIdCliente((short) clienteIntroducido);
        insertVenta.setIdProducto((short) productoIntroducido);
        insertVenta.setUnidades((short) unidadesIntroducidas);
        insertVenta.setFecha(fechaIntroducida);
        em.persist(insertVenta);
        transaction.commit();
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Venta introducida correctamente");
        System.out.println("-------------------------------------------------------------------------------");
    }

    private static void listadoVentas() {
        List<ClientesEntity> listaClientes = em.createQuery("from ClientesEntity ", ClientesEntity.class).getResultList();
        int i = 0, idClienteSeleccionado = 0;
        Double importeTotal = 0.0;
        String cliente, nombreClienteSeleccionado = "";
        boolean validacionCliente = false;
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
        List<VentaprodEntity> listaVentasCliente = em.createQuery("from VentaprodEntity where idCliente = ?1", VentaprodEntity.class).setParameter(1,idClienteSeleccionado).getResultList();
        System.out.println("---------------------------------------");
        System.out.println("Ventas del cliente: " + nombreClienteSeleccionado);
        System.out.println("---------------------------------------");
        for(VentaprodEntity ventaCliente: listaVentasCliente){
            System.out.println("Venta: " + ventaCliente.getId()+", Fecha venta: " + ventaCliente.getFecha());
            List<ProductosEntity> listaProductos = em.createQuery("from ProductosEntity where id = ?1 ", ProductosEntity.class).setParameter(1,ventaCliente.getIdProducto()).getResultList();
            for(ProductosEntity producto: listaProductos) {
                System.out.println("    Producto: " + producto.getDescripcion());
                System.out.println("    Cantidad: " + ventaCliente.getUnidades() + " PVP: " + producto.getPrecio());
                System.out.println("    Importe: " + ventaCliente.getUnidades()*producto.getPrecio());
                importeTotal = importeTotal + ventaCliente.getUnidades()*producto.getPrecio();
            }
        }
        System.out.println("---------------------------------------");
        System.out.println("Número total de ventas: " + listaVentasCliente.size());
        System.out.println("Importe Total: " + importeTotal);
        System.out.println("---------------------------------------");
    }
}
