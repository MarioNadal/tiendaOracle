package database;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EmfSingleton {
    //Instancia del singleton de la factoría
    private static EmfSingleton emfSInstancia = new EmfSingleton();
    //Unidad de persistencia donde están las entidades
    static private final String PERSISTENCE_UNIT_NAME = "default";
    //La factoría se definde como privada
    private EntityManagerFactory emf = null;
    //Método que devuelve la instancia del singleton que permite acceder a la factoría
    public static EmfSingleton getInstance(){
        return emfSInstancia;
    }

    private EmfSingleton() {
    }
        //Los entity manager se crearán a partir de la factoría que devuelve este método
        public EntityManagerFactory getEmf() {
            //La creación dela factoría sólo se realiza la primera vez que se llama al método
        if(this.emf == null)
            this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        return this.emf;
    }
}
