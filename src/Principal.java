import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;

public class Principal {
    /*
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultaMoneda consulta = new ConsultaMoneda();
        System.out.println("Escriba la moneda a convertir que quiere consultar:");
        try {
            var numeroDePelicula = Integer.valueOf(lectura.nextLine());
            Conversor conversor = consulta.buscaMoneda(numeroDePelicula);
            System.out.println(conversor);
            GeneradorDeArchivo generador = new GeneradorDeArchivo();
            generador.guardarJson(conversor);
        } catch (NumberFormatException e){
            System.out.println("Moneda no encontrada"+e.getMessage());
        }
        catch (RuntimeException | IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Finalizando la aplicación.");

        }
    }*/

    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultaMoneda consulta = new ConsultaMoneda();

        try {
            Conversor conversor = consulta.buscaMoneda();
            System.out.println("Enter the currency code to convert from (e.g., USD): ");
            String fromCurrency = lectura.nextLine().toUpperCase();
            System.out.println("Enter the currency code to convert to (e.g., EUR): ");
            String toCurrency = lectura.nextLine().toUpperCase();
            System.out.println("Enter the amount to convert: ");
            double amount = Double.parseDouble(lectura.nextLine());

            double convertedAmount = consulta.convertirMoneda(conversor, fromCurrency, toCurrency, amount);
            System.out.printf("Converted amount: %.2f %s%n", convertedAmount, toCurrency);

            GeneradorDeArchivo generador = new GeneradorDeArchivo();
            generador.guardarJson(conversor);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount: " + e.getMessage());
        } catch (RuntimeException | IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Finalizing the application.");
        }
    }
}
