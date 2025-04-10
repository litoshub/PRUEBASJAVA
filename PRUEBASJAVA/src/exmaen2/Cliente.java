package exmaen2;

public class Cliente {
    private String nombre;
    private int antiguedad;

    // Constructor
    public Cliente(String nombre, int antiguedad) {
        this.nombre = nombre;
        this.antiguedad = antiguedad;
    }

    // Método de fábrica
    public static Cliente of(String nombre, int antiguedad) {
        return new Cliente(nombre, antiguedad);
    }

    // Métodos de consulta
    public String getNombre() {
        return nombre;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    // Método toString
    @Override
    public String toString() {
        return "Cliente[nombre=" + nombre + ", antigüedad=" + antiguedad + "]";
    }

    // Métodos equals y hashCode
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente cliente = (Cliente) obj;
        return nombre.equals(cliente.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode();
    }
}
