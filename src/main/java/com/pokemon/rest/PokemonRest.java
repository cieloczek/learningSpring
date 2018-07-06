package com.pokemon.rest;


import com.pokemon.cache.PokemonCache;
import com.pokemon.dto.PokemonDto;
import com.pokemon.service.PokemonJdbcService;
import com.pokemon.service.PokemonService;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
public class PokemonRest {

    private PokemonService pokemonService;
    private PokemonJdbcService pokemonJdbcService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public PokemonRest(PokemonService pokemonService, PokemonJdbcService pokemonJdbcService) {
        this.pokemonService = pokemonService;
        this.pokemonJdbcService = pokemonJdbcService;
    }

    @RequestMapping("/pokemon")
    public PokemonDto getPokemon(@RequestParam(value = "id") String id) throws IOException {
        return pokemonService.getPokemonDto(id);
    }
    @PostMapping
    @RequestMapping(value = "/addPokemon",method = RequestMethod.POST)
    public ResponseEntity<String> addPokemon(@RequestBody PokemonDto pokemonDto) {
        pokemonJdbcService.add(pokemonDto);
       return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping
    @RequestMapping("/all")
    public List<PokemonDto>showAll() throws IOException{
    return pokemonJdbcService.showAll();
    }

    @RequestMapping(value = "/find")
    public PokemonDto findPokemon(@RequestBody PokemonDto pokemonDto){
        return pokemonJdbcService.findPokemon(pokemonDto);
    }


}
