package livetddcomwebflux.pokedex;

import livetddcomwebflux.pokedex.model.Pokemon;

import java.util.Collections;

public class PokemonMock {

    public static Pokemon pokemonMock(){
        return new Pokemon("1289b88d-733d-45b4-82e0-618f803f83d9",
                "Ralts",
                "Psychic",
                Collections.emptyList());
    }
}
