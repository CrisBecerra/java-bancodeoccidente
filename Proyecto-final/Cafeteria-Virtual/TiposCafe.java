
public class TiposCafe {
  private String codigo;
  private String calidad;
  private double precio;
  
   public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getCalidad() {
    return calidad;
  }

  public void setCalidad(String calidad) {
    this.calidad = calidad;
  }

  public double getPrecio() {
    return precio;
  }

  public void setPrecio(double precio) {
    this.precio = precio;
  }

  public TiposCafe(String codigo, String calidad, double precio) {
    this.codigo = codigo;
    this.calidad = calidad;
    this.precio = precio;
    
  }
  
 
}
