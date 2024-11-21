import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Inventario {
    String ruta = "C:/Users/riosa/Documents/CursoProgramacion/ArchivosJava/";
    File archivo = new File(ruta + "productos.txt");
    Producto producto;

    public void agregarProducto(int id, String nombre, String categoria, double precio, int cantidad) {
        producto = new Producto(id, nombre, categoria, precio, cantidad);
        try (FileWriter fw = new FileWriter(archivo.getAbsoluteFile(), true)) {
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(producto.getId() + ",");
            bw.write(producto.getNombre() + ",");
            bw.write(producto.getCategoria() + ",");
            bw.write(producto.getPrecio() + "0,");
            bw.write(producto.getCantidad() + "");
            bw.close();
            System.out.println("--------------------------------------------");
            System.out.println("         Producto agregado con exito        ");
            System.out.println("--------------------------------------------");
        } catch (Exception e) {
        }
    }

    public void actualizarProducto(String prod, int id, String nombre, String categoria, double precio, int cantidad) {
        File temp = new File(ruta + "temporal.txt");
        File nuevo = new File(ruta + "productos.txt");
        archivo.renameTo(temp);
        try (FileReader fr = new FileReader(temp)) {
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(nuevo.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(new FileWriter(nuevo));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] cadena = linea.split(",");
                if (!cadena[1].equals(prod)) {
                    bw.write(cadena[0] + ",");
                    bw.write(cadena[1] + ",");
                    bw.write(cadena[2] + ",");
                    bw.write(cadena[3] + ",");
                    bw.write(cadena[4] + "\n");
                } else {
                    bw.write(id + ",");
                    bw.write(nombre + ",");
                    bw.write(categoria + ",");
                    bw.write(precio + ",");
                    bw.write(cantidad + "\n");
                }
            }
            br.close();
            bw.close();
            temp.delete();
        } catch (Exception e) {
        }
    }

    public void eliminar(String nombre) {
        File temp = new File(ruta + "temporal.txt");
        File nuevo = new File(ruta + "productos.txt");
        archivo.renameTo(temp);
        try (FileReader fr = new FileReader(temp)) {
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(nuevo.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(new FileWriter(nuevo));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] cadena = linea.split(",");
                if (!cadena[1].equals(nombre)) {
                    bw.write(cadena[0] + ",");
                    bw.write(cadena[1] + ",");
                    bw.write(cadena[2] + ",");
                    bw.write(cadena[3] + ",");
                    bw.write(cadena[4] + "\n");
                }
            }
            br.close();
            bw.close();
            temp.delete();
        } catch (Exception e) {
        }
    }

    public void buscarProductoCategoria(String categoria) {
        try (FileReader fr = new FileReader(archivo)) {
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] cadena = linea.split(",");
                if (cadena[2].equals(categoria)) {
                    System.out.println(linea);
                } else {
                    System.out.println("Lo sentimos, no encontramos su producto");
                }
            }
        } catch (Exception e) {
        }
    }

    public void buscarProductoNombre(String nombre) {
        try (FileReader fr = new FileReader(archivo)) {
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] cadena = linea.split(",");
                if (cadena[1].equals(nombre)) {
                    System.out.println(linea);
                } else {
                    System.out.println("Lo sentimos, no encontramos su producto");
                }
            }
        } catch (Exception e) {
        }
    }

    public void buscarProductoId(int id) {
        try (FileReader fr = new FileReader(archivo)) {
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] cadena = linea.split(",");
                if (cadena[0].equals(Integer.toString(id))) {
                    System.out.println(linea);
                } else {
                    System.out.println("Lo sentimos, no encontramos su producto");
                }
            }
        } catch (Exception e) {
        }
    }

    public void calcularPrecioMayor() {
        try (FileReader fr = new FileReader(archivo)) {
            BufferedReader br = new BufferedReader(fr);
            String linea;
            Double may = 0.0;
            String prod = "";
            while ((linea = br.readLine()) != null) {
                String[] cadena = linea.split(",");
                if (Double.parseDouble(cadena[3]) > may) {
                    may = Double.parseDouble(cadena[3]);
                }
                prod = cadena[1];
            }
            System.out.println("El producto mas caro es: " + prod + ", tiene un costo de: " + may);
        } catch (Exception e) {
        }
    }

    public void calcularCantidad(String categoria) {
        try (FileReader fr = new FileReader(archivo)) {
            BufferedReader br = new BufferedReader(fr);
            String linea;
            int con = 0;
            while ((linea = br.readLine()) != null) {
                String[] cadena = linea.split(",");
                if (cadena[2].equals(categoria)) {
                    con++;
                }
            }
            System.out.println("La cantidad de productos en la categoria " + categoria + ", es igual a: " + con);
        } catch (Exception e) {
        }
    }

    public void reporte() {
        File repor = new File(ruta + "reporte_inventario.txt");
        try (FileReader fr = new FileReader(archivo)) {
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(repor.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(new FileWriter(repor));
            String linea;
            Double total=0.0;
            while ((linea = br.readLine()) != null) {
                String[] cadena = linea.split(",");
                bw.write(cadena[0] + ",");
                bw.write(cadena[1] + ",");
                bw.write(cadena[2] + ",");
                bw.write(cadena[3] + ",");
                bw.write(cadena[4] + "\n");
                total+= Double.parseDouble(cadena[3]) * Integer.parseInt(cadena[4]);
            }
            bw.write("Valor total: "+ total);
            br.close();
            bw.close();
            System.out.println("--------------------------------------------");
            System.out.println("         Reporte generado con exito         ");
            System.out.println("--------------------------------------------");
        } catch (Exception e) {
        }
    }
}
