package ud2.clientes;

import java.time.LocalDate;

public class CompraDTO {
    int id;
    int idCliente;
    double importe;
    LocalDate fecha;
    
    public CompraDTO(int id, int idCliente, double importe, LocalDate fecha) {
        this.id = id;
        this.idCliente = idCliente;
        this.importe = importe;
        this.fecha = fecha;
    }

    public CompraDTO(int id, int idCliente) {
        this.id = id;
        this.idCliente = idCliente;
    }

    public CompraDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "CompraDTO [id=" + id + ", idCliente=" + idCliente + ", importe=" + importe + ", fecha=" + fecha + "]";
    }
}
