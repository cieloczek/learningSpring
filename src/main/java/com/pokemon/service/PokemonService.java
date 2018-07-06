package com.pokemon.service;

import com.pokemon.dto.PokemonDto;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface PokemonService {
    /**
     * Method gathers Pokemon from remote database.
     * @param id
     * @return  json data
     * @throws IOException
     */
    public PokemonDto getPokemonDto(String id) throws IOException;
}
