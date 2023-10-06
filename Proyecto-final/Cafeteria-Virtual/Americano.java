
public class Americano extends TiposCafe {
  private String sabor;
  private int cantidadml;

  public Americano(String codigo, String calidad, double precio, String sabor, int cantidadml) {
    super(codigo, calidad, precio);
    this.sabor = sabor;
  
  }

  public String getSabor() {
    return sabor;
  }

  public void setSabor(String sabor) {
    this.sabor = sabor;
  }

  public int getCantidadml() {
    return cantidadml;
  }

  public void setCantidadml(int cantidadml) {
    this.cantidadml = cantidadml;
  }

}
