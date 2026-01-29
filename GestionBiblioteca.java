/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestionbiblioteca;

import java.util.ArrayList;
import java.util.Scanner;

// CLASE LIBRO 
class Libro {
    private String titulo;
    private String autor;
    private int cantidadDisponible;

    public Libro(String titulo, String autor, int cantidadDisponible) {
        this.titulo = titulo;
        this.autor = autor;
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getCantidad() {
        return cantidadDisponible;
    }

    public void prestar() {
        if (cantidadDisponible > 0) {
            cantidadDisponible--;
        }
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo +
               " | Autor: " + autor +
               " | Disponibles: " + cantidadDisponible;
    }
}


//  CLASE USUARIO 
class Usuario {
    private String nombre;
    private int librosPrestados;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.librosPrestados = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean puedePedirPrestado() {
        return librosPrestados < 2;
    }

    public void tomarLibro() {
        librosPrestados++;
    }
}

// CLASE PRINCIPAL 
public class GestionBiblioteca {

    static ArrayList<Libro> listaLibros = new ArrayList<>();
    static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Datos iniciales
        listaLibros.add(new Libro("El arte de ser nosotros", "Inma Rubiales", 3));
        listaLibros.add(new Libro("Donde todo brilla", "Alice Kellen", 1));
        listaLibros.add(new Libro("Don Quijote", "Miguel de Cervantes", 3));

        listaUsuarios.add(new Usuario("Arath"));

        int opcion;

        do {
            System.out.println("\n========== MENU BIBLIOTECA ==========");
            System.out.println("1. Gestion de libros");
            System.out.println("2. Gestion de usuarios");
            System.out.println("3. Solicitar prestamo de libro");
            System.out.println("4. Ver reporte general");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> menuLibros();
                case 2 -> menuUsuarios();
                case 3 -> realizarPrestamo();
                case 4 -> mostrarReporte();
                case 5 -> System.out.println("Cerrando sistema...");
                default -> System.out.println("Opcion no valida.");
            }

        } while (opcion != 5);
    }

    //  MENU LIBROS 
    public static void menuLibros() {
        int opcion;
        do {
            System.out.println("\n--- GESTION DE LIBROS ---");
            System.out.println("1. Registrar libro");
            System.out.println("2. Ver libros");
            System.out.println("3. Buscar libro");
            System.out.println("4. Volver");
            System.out.print("Opcion: ");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> registrarLibro();
                case 2 -> verLibros();
                case 3 -> buscarLibro();
                case 4 -> System.out.println("Volviendo...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 4);
    }

    //  MENU USUARIOS 
    public static void menuUsuarios() {
        int opcion;
        do {
            System.out.println("\n--- GESTION DE USUARIOS ---");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Ver usuarios");
            System.out.println("3. Buscar usuario");
            System.out.println("4. Volver");
            System.out.print("Opcion: ");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> registrarUsuario();
                case 2 -> verUsuarios();
                case 3 -> buscarUsuario();
                case 4 -> System.out.println("Volviendo...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 4);
    }

    //  METODOS LIBROS 
    public static void registrarLibro() {
        System.out.print("Titulo: ");
        String t = sc.nextLine();
        System.out.print("Autor: ");
        String a = sc.nextLine();
        System.out.print("Cantidad disponible: ");
        int c = Integer.parseInt(sc.nextLine());

        listaLibros.add(new Libro(t, a, c));
        System.out.println("Libro registrado correctamente.");
    }

    public static void verLibros() {
        System.out.println("\n--- LISTA DE LIBROS ---");
        for (Libro l : listaLibros) {
            System.out.println(l);
        }
    }

    public static void buscarLibro() {
        System.out.print("Titulo del libro: ");
        String titulo = sc.nextLine();

        for (Libro l : listaLibros) {
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                System.out.println("Libro encontrado:");
                System.out.println(l);
                return;
            }
        }
        System.out.println("Libro no encontrado.");
    }

    //  METODOS USUARIOS 
    public static void registrarUsuario() {
        System.out.print("Nombre del usuario: ");
        String n = sc.nextLine();
        listaUsuarios.add(new Usuario(n));
        System.out.println("Usuario registrado.");
    }

    public static void verUsuarios() {
        System.out.println("\n--- LISTA DE USUARIOS ---");
        for (Usuario u : listaUsuarios) {
            System.out.println("Nombre: " + u.getNombre());
        }
    }

    public static void buscarUsuario() {
        System.out.print("Nombre del usuario: ");
        String nombre = sc.nextLine();

        for (Usuario u : listaUsuarios) {
            if (u.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Usuario encontrado: " + u.getNombre());
                return;
            }
        }
        System.out.println("Usuario no encontrado.");
    }

    //  PRESTAMOS 
    public static void realizarPrestamo() {
        System.out.print("Nombre del usuario: ");
        String nombre = sc.nextLine();

        Usuario usuario = null;
        for (Usuario u : listaUsuarios) {
            if (u.getNombre().equalsIgnoreCase(nombre)) {
                usuario = u;
                break;
            }
        }

        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        if (!usuario.puedePedirPrestado()) {
            System.out.println("Limite de prestamos alcanzado.");
            return;
        }

        System.out.print("Titulo del libro: ");
        String titulo = sc.nextLine();

        for (Libro l : listaLibros) {
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                if (l.getCantidad() > 0) {
                    l.prestar();
                    usuario.tomarLibro();
                    System.out.println("Prestamo realizado con exito.");
                } else {
                    System.out.println("No hay ejemplares disponibles.");
                }
                return;
            }
        }
        System.out.println("Libro no encontrado.");
    }

    // REPORTE 
    public static void mostrarReporte() {
        System.out.println("\n--- REPORTE GENERAL ---");
        for (Libro l : listaLibros) {
            System.out.println(l);
        }
        System.out.println("Usuarios registrados: " + listaUsuarios.size());
    }
}
