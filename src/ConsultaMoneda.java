import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ConsultaMoneda {

    /*

    public Conversor buscaMoneda(int numeroDePelicula){
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/c3040ea8742f96ce1f3243a1/latest/USD"+numeroDePelicula);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();


        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Conversor.class);
        } catch (Exception e) {
            throw new RuntimeException("No encontré esa moneda.");
        }


    }*/
    public Conversor buscaMoneda() {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/c3040ea8742f96ce1f3243a1/latest/USD");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Conversor.class);
        } catch (Exception e) {
            throw new RuntimeException("Unable to fetch currency rates.");
        }
    }

    public double convertirMoneda(Conversor conversor, String fromCurrency, String toCurrency, double amount) {
        Map<String, Double> rates = conversor.conversion_rates();
        if (!rates.containsKey(fromCurrency) || !rates.containsKey(toCurrency)) {
            throw new IllegalArgumentException("Invalid currency code.");
        }
        double fromRate = rates.get(fromCurrency);
        double toRate = rates.get(toCurrency);
        return amount * (toRate / fromRate);
    }
}
