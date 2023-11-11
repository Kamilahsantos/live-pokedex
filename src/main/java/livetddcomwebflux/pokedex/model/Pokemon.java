package livetddcomwebflux.pokedex.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "pokemon")
public record Pokemon (@Id String pokemonId, String nome,
                       String tipo, List<String> evolucoes){

}
