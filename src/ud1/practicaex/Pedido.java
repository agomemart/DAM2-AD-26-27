package ud1.practicaex;

import java.util.List;
import java.util.Locale;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"fecha", "cliente", "lineas", "totalXml"})
public class Pedido {
    @XmlAttribute private String id;
    @XmlAttribute private boolean pagado;
    private String fecha;
    private Cliente cliente;
    @XmlElementWrapper(name="lineas")
    @XmlElement(name = "Linea")
    private List<Linea> lineas;

    public Pedido(String id, String fecha, Cliente cliente, boolean pagado, List<Linea> lineas) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.pagado = pagado;
        this.lineas = lineas;
    }

    public Pedido() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public List<Linea> getLineas() {
        return lineas;
    }

    public void setLineas(List<Linea> lineas) {
        this.lineas = lineas;
    }

    public boolean isPagado() {
        return pagado;
    }

    public double getTotal() {
        double total = 0;
        for (Linea l : lineas) {
            total += l.getPrecio() * l.getUnidades();
        }
        return total;
    }

    @XmlElement(name = "total")
    public String getTotalXml() {
        return String.format(Locale.US, "%.2f", getTotal());
    }

    public void setTotalXml(String ignorado) { }
}

