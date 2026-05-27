import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;


public class Buscador {

    Scanner scann = new Scanner(System.in);
    Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public void buscarCep() {
        var busca = "";

        while (!busca.equalsIgnoreCase("sair")) {
            System.out.println("Digite o CEP para busca:");
            busca = scann.nextLine();

            if (busca.equalsIgnoreCase("sair")) {
                System.out.println("Encerrando programa");
                break;
            }

            try {
                HttpClient client = HttpClient.newHttpClient();

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://viacep.com.br/ws/" + busca + "/json/"))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() != 200) {
                    System.out.println("Não conseguimos buscar o CEP, verifique os números digitados!");
                    continue;
                }

                var json = response.body();

                if (json.contains("\"erro\"")) {
                    System.out.println("CEP não encontrado!");
                    continue;
                }

                CEPVIACEP cep = gson.fromJson(json, CEPVIACEP.class);
                CEP endereco = new CEP(cep);

                System.out.println(endereco);

            } catch (IOException | InterruptedException | JsonSyntaxException | IllegalArgumentException e) {
                System.out.println("Não conseguimos buscar o CEP, verifique os números digitados!");
            }
        }

    }
}

