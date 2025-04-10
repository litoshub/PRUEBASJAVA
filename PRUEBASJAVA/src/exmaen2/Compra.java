package exmaen2;


public class Compra {
    private Cliente cliente;
    private String descripcion;
    private double importe;

    // Constructor
    public Compra(Cliente cliente, String descripcion, double importe) {
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.importe = importe;
    }

    // Método de fábrica
    public static Compra of(Cliente cliente, String descripcion, double importe) {
        return new Compra(cliente, descripcion, importe);
    }

    // Métodos de consulta
    public Cliente getCliente() {
        return cliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getImporte() {
        return importe;
    }

    // Método toString
    @Override
    public String toString() {
        return "Compra[Nombre de cliente= " + cliente.getNombre() + ", descripción= " + descripcion + ", importe= " + importe + " €]";
    }
}
