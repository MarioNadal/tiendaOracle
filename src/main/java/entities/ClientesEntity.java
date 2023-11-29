package entities;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "CLIENTES", schema = "ROOT", catalog = "")
public class ClientesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    private short id;
    @Basic
    @Column(name = "NIF", nullable = false, length = 10)
    private String nif;
    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 14)
    private String nombre;
    @Basic
    @Column(name = "LOCALIDAD", nullable = true, length = 14)
    private String localidad;
    @OneToMany(mappedBy = "clientesByIdCliente")
    private Collection<VentaprodEntity> ventaprodsById;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientesEntity that = (ClientesEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(nif, that.nif) && Objects.equals(nombre, that.nombre) && Objects.equals(localidad, that.localidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nif, nombre, localidad);
    }

    public Collection<VentaprodEntity> getVentaprodsById() {
        return ventaprodsById;
    }

    public void setVentaprodsById(Collection<VentaprodEntity> ventaprodsById) {
        this.ventaprodsById = ventaprodsById;
    }
}
