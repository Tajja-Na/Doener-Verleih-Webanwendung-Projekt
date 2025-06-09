package de.hsrm.mi.web.projekt.doener.services;

import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class DoenerNamingServiceImpl implements DoenerNamingService{
    private RestClient restClient = RestClient.create();
    private Random random = new Random();

    record PokemonResponse(String name){}

    @Override
    public String getName() {
        int id = random.nextInt(1000) + 1;
        String url = "https://pokeapi.co/api/v2/pokemon/" + id;

        try{
            PokemonResponse response = restClient.get()
                .uri(url)
                .retrieve()
                .body(PokemonResponse.class);

            String name = response.name();
            if(name != null){
                String pokemonDoenerName = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
                pokemonDoenerName = pokemonDoenerName + "dön";
                return pokemonDoenerName;
            }
        }catch(HttpClientErrorException e){
            if(e.getStatusCode() != HttpStatus.NOT_FOUND){
                throw new RuntimeException("Fehler beim Abrufen des Pokemon", e);
            }
        }
        return "FehlerDön - " + id;
    }
}
