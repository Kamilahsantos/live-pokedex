package livetddcomwebflux.pokedex;


import livetddcomwebflux.pokedex.model.Pokemon;
import livetddcomwebflux.pokedex.repository.PokemonRepository;
import livetddcomwebflux.pokedex.service.PokemonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
public class PokemonServiceTest {

    @InjectMocks
    private PokemonService pokemonService;

    @Mock
    private PokemonRepository pokemonRepository;


    @BeforeEach
    public void setUp() {

        BDDMockito.when(pokemonRepository.save(PokemonMock.pokemonMock()))
                .thenReturn(Mono.just(PokemonMock.pokemonMock()));

        BDDMockito.when(pokemonRepository.findAll())
                .thenReturn(Flux.just(PokemonMock.pokemonMock()));

        BDDMockito.when(pokemonRepository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(PokemonMock.pokemonMock()));

        BDDMockito.when(pokemonRepository.delete(ArgumentMatchers.any(Pokemon.class)))
                .thenReturn(Mono.empty());

    }

    @Test
    @DisplayName("deve criar um novo pokemon")
    public void deveCriarUmPokemonComSucesso() {
        StepVerifier.create(pokemonService.salvarPokemon(PokemonMock.pokemonMock()))
                .expectSubscription()
                .expectNext(PokemonMock.pokemonMock())
                .verifyComplete();
    }

    @Test
    @DisplayName("deve listar todos os pokemons cadastrados")
    public void deveListarPokemonsComSucesso() {
        StepVerifier.create(pokemonService.listarPokemons())
                .expectSubscription()
                .expectNext(PokemonMock.pokemonMock())
                .verifyComplete();

    }

    @Test
    @DisplayName("deve buscar um pokemon pelo id com sucesso")
    public void deveBuscarUmPokemonPeloIdComSucesso() {
        StepVerifier.create(pokemonService.buscarPokemonPeloId("1289b88d-733d-45b4-82e0-618f803f83d9"))
                .expectSubscription()
                .expectNext(PokemonMock.pokemonMock())
                .verifyComplete();

    }

    @Test
    @DisplayName("deve retornar 404 quando nao encontrar um pokemon")
    public void DeveRetornar404AoBuscarUmPokemon(){
        BDDMockito.when(pokemonRepository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.empty());
        StepVerifier.create(pokemonService.buscarPokemonPeloId("pokemon aleatorio"))
                .expectSubscription()
                .expectError(ResponseStatusException.class)
                .verify();

    }

    @Test
    @DisplayName("deve excluir um Pokemon com Sucesso")
    public void deveExcluirUmPokemonComSucesso(){

        StepVerifier.create(pokemonService.excluirPokemon("1289b88d-733d-45b4-82e0-618f803f83d9"))
                .expectSubscription()
                .verifyComplete();
    }

}
