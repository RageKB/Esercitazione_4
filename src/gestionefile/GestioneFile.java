package gestionefile;

import java.util.Scanner;

/**
 *
 * @author MC
 * @version 12/01/23
 */
public class GestioneFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //1)LETTURA
        Lettore lettore = new Lettore("user.json");
        lettore.start();
        try {
            lettore.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //2)ELABORAZIONE
        System.out.println("inserisci il tuo username:");
        String username = scanner.nextLine();

        System.out.println("inserisci la tua password personale:");
        String password = scanner.nextLine();

        String testo = username + ";" + password;
        
        //3) SCRITTURA
        Scrittore scrittore = new Scrittore("output.csv", testo);
        Thread threadScrittore = new Thread(scrittore);
        threadScrittore.start();
        try {
            threadScrittore.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // copia
        Lettore copia = new Lettore("output.csv","copia.csv");
        copia.start();
        try {
            copia.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
