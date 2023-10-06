
/*
 Diseñar un programa que permita al usuario comprar en una cafeteria online especificando 
 las caracteristicas de cada tipo de café.
 El programa debe permitirme conocer la disponibilidad de un producto
 */



import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

public class Cafeteria {
  
  private final String Americano = "americano";
  private final String Cappuccino = "cappuccino";
  private final String Carajillo = "carajillo";
  private final String UTF_8 = "UTF-8";


  private List<TiposCafe> TCafe;

  public Cafeteria() {
    TCafe = new LinkedList<TiposCafe>();

    cargarArchivo("americano.txt", Americano);
    cargarArchivo("cappuccino.txt", Cappuccino);
    cargarArchivo("carajillo.txt", Carajillo);
    
  }

  private void cargarArchivo(String archivo, String tipo) {
    try {
      FileReader reader = new FileReader("./archivos/" + archivo, Charset.forName("UTF-8"));
      BufferedReader bufferedReader = new BufferedReader(reader);

      String linea;

      while ((linea = bufferedReader.readLine()) != null) {
        String[] partes = linea.split("\\|");

        String codigo = partes[0];
        String calidad = partes[1];
        double precio = Double.parseDouble(partes[2]);
        String sabor = partes[3];

        switch (tipo) {
          case Americano:
            int cantidadml = Integer.parseInt(partes[4]);
            TCafe.add(new Americano(codigo, calidad, precio, sabor, cantidadml));
            break;

          case Cappuccino:
            TCafe.add(new Cappuccino(codigo, calidad, precio, sabor));
            break;

          case Carajillo:
            TCafe.add(new Carajillo(codigo, calidad, precio, sabor));
            break;

        }
      }

      bufferedReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("Error: No se encontró el archivo " + archivo);
    } catch (IOException e) {
      System.out.println("Error desconocido al cargar el archivo " + archivo);
    }
  }

  public boolean venta(String codigo) {
    for (TiposCafe tiposcafe : TCafe) {
      if (tiposcafe.getCodigo().equals(codigo)) {
        TCafe.remove(tiposcafe);
        return true;
      }
    }

    return false;
  }

  public void reporteProductos() {
    int americano = 0;
    int carajillo = 0;
    int cappuccino = 0;


    for (TiposCafe tiposcafe : TCafe) {
     if (tiposcafe instanceof Americano) {
        americano++;
      } else if (tiposcafe instanceof Carajillo) {
        carajillo++; 
      } else {
        cappuccino++;
      }

    }

    try {
      FileWriter writer = new FileWriter("./archivos/", Charset.forName(UTF_8));
      BufferedWriter bufferedWriter = new BufferedWriter(writer);

    
      bufferedWriter.write("Reporte de productos disponibles");
      bufferedWriter.newLine();
      bufferedWriter.write("americano: " + americano);
      bufferedWriter.newLine();
      bufferedWriter.write("carajillo: " + carajillo);
      bufferedWriter.newLine();
      bufferedWriter.write("cappuccino: " + cappuccino);

      System.out.println("\n Reporte generado :)");

      bufferedWriter.close();
    } catch (IOException e) {
      System.out.println("Error generando reporte de productos");
    }
  }


    
    public static void main(String...  args){
    Cafeteria maquina = new Cafeteria();
    try (Scanner sc = new Scanner(System.in)) {
      boolean continuar = true;

      do {
        System.out.println("\nElige una opcion del menu:"
            + "\n1. Quiero un cafe"
            + "\n2. Reporte de cafes disponibles"
            + "\n3. Salir");

        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
          case 1:
            System.out.println("\nIngresa el codigo del cafe:");
            String codigo = sc.nextLine();
            boolean hizoVenta = maquina.venta(codigo);

            if (!hizoVenta) {
              System.out.println("\n Cafe no encontrado...");
            } else {
              System.out.println("\n Cafe vendido :)");
            }
            break;

          case 2:
            maquina.reporteProductos();
            break;

          default:
            continuar = false;
        }
      
      } while (continuar);
    }
  }
     
}