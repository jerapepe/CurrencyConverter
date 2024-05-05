package menu;

import API.ExChangeRateAPI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void showMenu() {
        boolean continuar = true;
        Scanner scanner = new Scanner(System.in);
        fecha();
        List<List<String>> data = ExChangeRateAPI.codeListString();

        while(continuar) {

            System.out.println("*** BIENVENID@ AL CONVERSOR DE MONEDAS ***\n");
            System.out.printf("%-5s %-20s %-10s %-5s %-20s %-10s\n", "N°", "Moneda", "Código", "N°", "Moneda", "Código");
            int sizeLista = data.size();
            for (int i = 0; i < sizeLista / 2; i++) {
                List<String> moneda1 = data.get(i);
                List<String> moneda2 = data.get(i + sizeLista / 2);
                System.out.printf("%-5d %-20s %-10s %-5d %-20s %-10s\n", i + 1, moneda1.get(1), "[" + moneda1.get(0) + "]", i + sizeLista / 2 + 1, moneda2.get(1), "[" + moneda2.get(0) + "]");
            }

            int codeFromCurrency;
            do {
                System.out.println("Indique el índice de la moneda a convertir: ");
                codeFromCurrency = scanner.nextInt();
                if (codeFromCurrency > 162 || codeFromCurrency < 1) {
                    System.out.println("Índice inválido, vuelva a intentarlo.");
                }
            } while (codeFromCurrency > 162 || codeFromCurrency < 1);

            int codeToCurrency;
            do {
                System.out.println("Indique el índice de la moneda requerida: ");
                codeToCurrency = scanner.nextInt();
                if (codeToCurrency > 162 || codeToCurrency < 1) {
                    System.out.println("Índice inválido, vuelva a intentarlo.");
                }
            } while (codeToCurrency > 162 || codeToCurrency < 1);

            System.out.println("Indique el monto a convertir: ");
            double mount = scanner.nextDouble();
            ExChangeRateAPI.convertCodes(codeFromCurrency, codeToCurrency, mount);

            System.out.println("¿Desea realizar otra conversión? (s/n)");
            String option = scanner.next();
            if (!option.equalsIgnoreCase("s")) {
                continuar = false;
            }
        }
        System.out.println("Bye");
        scanner.close();
    }

    public static void fecha(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d-MMM-uuuu | HH:mm:ss");
        String date = dateTime.format(dateTimeFormatter);
        System.out.println("Fecha: "  + date);
    }
}
