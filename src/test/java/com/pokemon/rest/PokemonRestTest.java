package com.pokemon.rest;

import com.pokemon.dto.PokemonDto;
import com.pokemon.service.PokemonJdbcService;
import com.pokemon.service.PokemonServiceImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PokemonRestTest {
    Logger logger = LoggerFactory.getLogger(PokemonRestTest.class);
    PokemonRest pokemonRest = new PokemonRest(new PokemonServiceImpl(), new PokemonJdbcService());

    @Test
    public void addPokemon() throws IOException {
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setName("TestName");
        pokemonDto.setSpeciesName("SpeciesName");
        pokemonDto.setSpeciesUrl("SpeciesUrl");
        pokemonDto.setWeight("13");
        pokemonRest.addPokemon(pokemonDto);
    }

    @Test
    public void showAll() {
        try {

            PokemonDto pokemonDto = new PokemonDto();
            PokemonDto pokemonDto2 = new PokemonDto();
            PokemonDto pokemonDto23 = new PokemonDto();
            pokemonRest.addPokemon(pokemonDto2);
            pokemonRest.addPokemon(pokemonDto23);
            pokemonRest.addPokemon(pokemonDto);
            pokemonRest.showAll();

            System.out.println(pokemonRest.showAll());
            assertEquals(3, pokemonRest.showAll().size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}