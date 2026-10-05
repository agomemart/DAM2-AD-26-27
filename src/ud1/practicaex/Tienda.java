package ud1.practicaex;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="tienda")
@XmlAccessorType (XmlAccessType.FIELD)
public class Tienda {
    private String tienda;
    @XmlElementWrapper(name="pedidos")
    @XmlElement(name = "Pedido")
    private List<Pedido> pedidos;
    
    public Tienda(String tienda, List<Pedido> pedidos) {
        this.tienda = tienda;
        this.pedidos = pedidos;
    }

    public Tienda() {
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String nombre) {
        this.tienda = nombre;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
