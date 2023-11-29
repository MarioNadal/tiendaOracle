package entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "VENTAPROD", schema = "ROOT")
public class VentaprodEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    private short id;
    @Basic
    @Column(name = "ID_CLIENTE", nullable = false, precision = 0)
    private short idCliente;
    @Basic
    @Column(name = "ID_PRODUCTO", nullable = false, precision = 0)
    private short idProducto;
    @Basic
    @Column(name = "UNIDADES", nullable = false, precision = 0)
    private short unidades;
    @Basic
    @Column(name = "FECHA", nullable = true)
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID", insertable = false, updatable = false)
    private ClientesEntity clientesByIdCliente;
    @ManyToOne
    @JoinColumn(name = "ID_PRODUCTO", referencedColumnName = "ID", insertable = false, updatable = false)
    private ProductosEntity productosByIdProducto;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public short getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(short idCliente) {
        this.idCliente = idCliente;
    }

    public short getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(short idProducto) {
        this.idProducto = idProducto;
    }

    public short getUnidades() {
        return unidades;
    }

    public void setUnidades(short unidades) {
        this.unidades = unidades;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VentaprodEntity that = (VentaprodEntity) o;
        return idCliente == that.idCliente && idProducto == that.idProducto && unidades == that.unidades && Objects.equals(id, that.id) && Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idCliente, idProducto, unidades, fecha);
    }

    public ClientesEntity getClientesByIdCliente() {
        return clientesByIdCliente;
    }

    public void setClientesByIdCliente(ClientesEntity clientesByIdCliente) {
        this.clientesByIdCliente = clientesByIdCliente;
    }

    public ProductosEntity getProductosByIdProducto() {
        return productosByIdProducto;
    }

    public void setProductosByIdProducto(ProductosEntity productosByIdProducto) {
        this.productosByIdProducto = productosByIdProducto;
    }
}
