package ort.arqsoft.obl.xml;

import java.io.*;

public class XmlSave {

    private static PrintWriter myOutWriter = null;
    //variable que define si se debe escribir o no
    private static boolean XML = true;

    /**
     * Setea un file para realizar la salida
     *
     * @param pNameFile nombre del file.
     */
    public static void setOut(String pPathFile) {
        try {                    
            myOutWriter = new PrintWriter(new FileWriter(pPathFile, true));
        } catch (IOException io) {
            System.out.println("Error al intentar abrir el archivo XML");
            System.exit(0); // @j@ cuando se usa esto!!!!!
        }
    }

    /**
     * Setea un OutputStream para realizar la salida
     * que puede ser la consola por ejemplo.
     *
     * @param pOut
     */
    public static void setOut(OutputStream pOut) {
        myOutWriter = new PrintWriter(pOut);
    }

    /**
     * Logea el string que se pasa como parametro,
     * este proceso lo realiza si la variable LOG
     * esta en true, se le puede anadir el flush
     * en este metodo o utilizar a mano, seg�n se prefiera
     *
     * @param pLine
     */
    public static void write(String pLine) {
        if (XML) {
            myOutWriter.println(pLine);
//seg�n si se necesetia puede ir esta linea
//flush();
        }
    }

    public static void flush() {
        myOutWriter.flush();
    }

    /**
     * Setea la variable LOG, que indica si se debe escribir o no.
     *
     */
    public static void close() {
        XML = false;
    }

    public static void open() {
        XML = true;
    }

    /**
     * Seteo la salida a un archivo primero, luego escribo dos lineas en este,
     * cambio la salida a la consola y escribo dos lineas en este.
     * Observar que si no utilizamos el flush automatico yo debo indicar a la
     * clase cuando quiero que vacie el buffer.
     *
     * @param args the command line arguments
     */

    /*public static void main(String[] args) {
        WriterLog.setOut("c:\\prueba.txt");
        WriterLog.write("Linea uno1");
        WriterLog.write("Linea dos1");
        WriterLog.flush();
        WriterLog.setOut(System.out);
        WriterLog.write("Linea tres");
        WriterLog.write("Linea cuatro");
        WriterLog.flush();
    }*/
}
