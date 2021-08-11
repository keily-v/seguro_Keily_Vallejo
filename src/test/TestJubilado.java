package test;

import dominio.JubiladoDiscapacidad;
import dominio.JubiladoVejez;
import dominio.Jubilado;
import dominio.JubiladoPatron;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class TestJubilado {

    static Scanner entrada = new Scanner(System.in);
    static ArrayList<Jubilado> jubilados = new ArrayList<>();

    public static void main(String[] args) {
        int op = 0;
        String cedula, nombres;
        int anios;
        do {
            op = menu(op);
            if (op != 5 && op != 4) {
                entrada.nextLine();
                System.out.print("Cedula: ");
                cedula = entrada.nextLine();
                System.out.print("Nombres: ");
                nombres = entrada.nextLine();
                System.out.print("Anios Aporte:  ");
                anios = entrada.nextInt();
                datos(op, cedula, nombres, anios);

            }
            if (op == 4) {

                int contador = 0;
                for (Jubilado jub : jubilados) {
                    jub.calculaPensioni();
                    jub.calcularPension();
                    if (jub instanceof JubiladoPatron) {
                        ((JubiladoPatron) jub).bonoSueldo();
                    }
                    contador++;
                }
                System.out.println("El numero de jubilados es:" + contador);

                ArrayList<Jubilado> jubiladosVejez = getJubiladosVejez(jubilados);
                System.out.println("-----------Las personas Jubiladas por vejez son:-----------");
                impresion(jubiladosVejez);

                ArrayList<Jubilado> jubiladosDiscapacidad = getJubiladosDiscapacidad(jubilados);
                System.out.println("-----------Las personas Jubiladas por discapacidad son:----------- ");
                impresion(jubiladosDiscapacidad);

                ArrayList<Jubilado> jubiladosPatronal = getJubiladosPatronal(jubilados);
                System.out.println("-----------Las personas Jubiladas patronal son:----------- ");
                impresion(jubiladosPatronal);
//               
            }
        } while (op != 5);
    }

    private static void impresion(ArrayList<Jubilado> jubilados) {
        for (Jubilado jub : jubilados) {
            System.out.println(jub);
        }
    }

    public static ArrayList<Jubilado> getJubiladosVejez(ArrayList<Jubilado> jub) {

        ArrayList<Jubilado> jubiladosVejez = new ArrayList<>();
        for (Jubilado j : jub) {
            if (j instanceof JubiladoVejez) {
                jubiladosVejez.add(j);
            }
        }
        return jubiladosVejez;
    }

    private static ArrayList<Jubilado> getJubiladosDiscapacidad(ArrayList<Jubilado> jub) {
        ArrayList<Jubilado> jubiladosDiscapacidad = new ArrayList<>();
        for (Jubilado j : jub) {
            if (j instanceof JubiladoDiscapacidad) {
                jubiladosDiscapacidad.add(j);
            }
        }
        return jubiladosDiscapacidad;
    }

    private static ArrayList<Jubilado> getJubiladosPatronal(ArrayList<Jubilado> jub) {
        ArrayList<Jubilado> jubiladosPatronal = new ArrayList<>();
        for (Jubilado j : jub) {
            if (j instanceof JubiladoPatron) {
                jubiladosPatronal.add(j);
            }
        }
        return jubiladosPatronal;
    }

    public static int menu(int op) {
        System.out.println("Menu Jubilado");
        System.out.print("1. Vejez\n2. Invalidez\n3. Patronal\n4. Reporte\n5. Salir\nEscoja una Opcion. ");
        op = entrada.nextInt();
        return op;
    }

    public static void datos(int op, String cedula, String nombres, int anios) {
        double porcentaje;
        int tipo;
        switch (op) {
            case 1:
                jubilados.add(new JubiladoVejez(cedula, nombres, anios));
                break;
            case 2:
                System.out.print("Porcentaje de discapacidad: ");
                porcentaje = entrada.nextDouble();
                jubilados.add(new JubiladoDiscapacidad(cedula, nombres, anios, porcentaje));
                break;
            case 3:
                System.out.print("Porcentaje de Inflacion: ");
                porcentaje = entrada.nextDouble();
                System.out.print("Tipo Empresa <<1>> publica <<2>> privada ");
                tipo = entrada.nextInt();
                jubilados.add(new JubiladoPatron(cedula, nombres, anios, porcentaje, tipo));
        }
    }

}
