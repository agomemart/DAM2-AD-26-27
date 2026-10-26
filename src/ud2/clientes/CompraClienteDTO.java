package ud2.clientes;

public class CompraClienteDTO {
    private int compraId;
    private double importe;
    private int clienteId;
    private String nombreCliente;
    
    public CompraClienteDTO(int compraId, double importe, int clienteId, String nombreCliente) {
        this.compraId = compraId;
        this.importe = importe;
        this.clienteId = clienteId;
        this.nombreCliente = nombreCliente;
    }

    public int getCompraId() {
        return compraId;
    }

    public void setCompraId(int compraId) {
        this.compraId = compraId;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    @Override
    public String toString() {
        return "CompraClienteDTO [compraId=" + compraId + ", importe=" + importe + ", clienteId=" + clienteId
                + ", nombreCliente=" + nombreCliente + "]";
    }
}
