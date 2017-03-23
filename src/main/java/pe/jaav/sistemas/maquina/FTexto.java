package pe.jaav.sistemas.maquina;

/**

 *
 */

import java.io.*;

public class FTexto {

    private String nombre;
    private int lineaActual;
    private BufferedReader entrada;
    private PrintWriter salida;

    /**Constructor solo toma el nombre*/
    public FTexto(String nom) {
        nombre = nom;

    }

    /**inicia el flujo de entrada*/
    public void abrirIn() throws IOException {
        entrada = new BufferedReader(new FileReader(nombre));
        lineaActual = 0;

    }

    /**cierra el archivo leido*/
    public void cerrarIn() throws IOException {
        entrada.close();
    }

    /**inicia el flujo de salida*/
    public void abrirOut() throws IOException {
        salida = new PrintWriter(new BufferedWriter(new FileWriter(nombre)));
    }

    /**marca el archivo escrito*/
    public void cerrarOut() throws IOException {

        salida.close();
    }

    /**recube una cadena y la pone en el  archivo*/
    public void escribir(String cad) throws IOException {
        salida.println(cad);

    }

    /**recube un entero y lo pone en el  archivo como cadena*/
    public void escribir(int n) throws IOException {

        int dig;
        char c[] = new char[100];
        char car[] = new char[100];
        int i, j, k;

        i = 0;
        while (n > 0) {
            dig = n % 10;
            n = n / 10;
            c[i] = (char) (dig + 48);
            i++;
        }
        k = 0;
        for (j = i - 1; j >= 0; j--) {
            car[k] = c[j];
            k++;
        }
        String cad = new String(car, 0, k);
        salida.print(cad);
    }

    /**recibe un caracter y lo pone en el  archivo*/
    public void escribir(char c) throws IOException {

        char car[] = new char[100];
        car[0] = c;
        String cad = new String(car, 0, 1);
        salida.print(cad);
    }

    /**Toma del archivo un cadena y la devuelve*/
    public String leerlinea() throws IOException {
        //Rutina lectura de datos
        lineaActual++;
        String cad = entrada.readLine();
        return cad;
    }

    /**Retorna la cadena correspondiente a la linea (parametro) del archivo*/
    public String leerlinea(int linea) throws IOException {
        String cad;
        if (linea > lineaActual) {
            try {
                cad = leerlinea();
            } catch (Exception e) {
                abrirIn();
                cad = leerlinea();
            }
        } else {
            abrirIn();
            cad  = leerlinea();

        }
        while (cad != null && lineaActual != linea) {
            cad = leerlinea();
        }
        return cad;
    }

    /**busca y retorna true si encuentra 'palabra' en el archivo dado*/
    public boolean buscar(String palabra) {
        String cad;
        boolean hallado = false;
        int cantLineas = 0;
        try {
            abrirIn();
            cad = leerlinea();
            while (cad != null && !hallado) {
                if (cad.equals(palabra)) {
                    hallado = true;
                }
                cantLineas++;
                cad = leerlinea();
            }
            cerrarIn();
        } catch (Exception e) {
            return hallado;
        }
        return hallado;
    }

    /**Devuelve la cantidad de lineas de codigo que tiene el archivo*/
    public int cantLineas() {
        String cad;
        int cantLineas = 0;
        try {
            abrirIn();
            cad = leerlinea();
            while (cad != null) {
                cantLineas++;
                cad = leerlinea();
            }
            cerrarIn();
        } catch (Exception e) {
            return 0;
        }
        return cantLineas;
    }

    /***/
    public String getNombre() {
        return nombre;
    }
}
