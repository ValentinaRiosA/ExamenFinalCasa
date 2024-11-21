import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        Inventario inventario = new Inventario();

        int id, cantidad;
        String nombre, categoria;
        double precio;

        menu.opciones();
        int opc = scanner.nextInt();
        try {
            switch (opc) {
                case 1:
                    System.out.print("Ingrese el id: ");
                    id = scanner.nextInt();
                    System.out.print("Ingrese el nombre: ");
                    nombre = scanner.next();
                    System.out.print("Ingrese la categoria: ");
                    categoria = scanner.next();
                    System.out.print("Ingrese el precio: ");
                    precio = scanner.nextInt();
                    System.out.print("Ingrese la cantidad: ");
                    cantidad = scanner.nextInt();

                    inventario.agregarProducto(id, nombre, categoria, precio, cantidad);
                    menu.opciones();
                    break;
                case 2:
                    System.out.println("Digite el nombre del produto a actualizar");
                    String prod = scanner.next();
                    System.out.print("Ingrese el id: ");
                    id = scanner.nextInt();
                    System.out.print("Ingrese el nombre: ");
                    nombre = scanner.next();
                    System.out.print("Ingrese la categoria: ");
                    categoria = scanner.next();
                    System.out.print("Ingrese el precio: ");
                    precio = scanner.nextInt();
                    System.out.print("Ingrese la cantidad: ");
                    cantidad = scanner.nextInt();
                    inventario.actualizarProducto(prod, id, nombre, categoria, precio, cantidad);
                    break;
                case 3:
                    System.out.println("Digite el nombre del produto a eliminar");
                    nombre = scanner.next();
                    inventario.eliminar(nombre);
                    break;
                case 4:
                    menu.busqueda();
                    int param = scanner.nextInt();
                    switch (param) {
                        case 1:
                            System.out.print("Ingrese la categoria: ");
                            categoria = scanner.next();
                            inventario.buscarProductoCategoria(categoria);
                            break;
                        case 2:
                            System.out.print("Ingrese el nombre: ");
                            nombre = scanner.next();
                            inventario.buscarProductoNombre(nombre);
                            break;
                        case 3:
                            System.out.print("Ingrese el id: ");
                            id = scanner.nextInt();
                            inventario.buscarProductoId(id);
                            break;
                        default:
                            System.out.println("Lo sentimos, no encontramos el parámetro");
                    }
                    break;
                case 5:
                    inventario.reporte();
                    break;
                case 6:
                    System.out.print("Digite el nombre de la categoria: ");
                    categoria = scanner.next();
                    inventario.calcularCantidad(categoria);
                    break;
                case 7:
                    inventario.calcularPrecioMayor();
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Lo sentimos, no tenemos esa opcion en nuestra tienda");
            }
        } catch (Exception e) {
        }
    }
}