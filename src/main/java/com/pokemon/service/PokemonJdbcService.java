package com.pokemon.service;

import com.pokemon.dto.PokemonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class PokemonJdbcService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    PokemonService pokemonService;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Method adding new pokemon to database
     *
     * @param pokemonDto
     */
    public void add(PokemonDto pokemonDto) {
        jdbcTemplate.update("INSERT INTO pokemons VALUES(?,?,?,?,?)",
                pokemonDto.getId(),
                pokemonDto.getName(),
                pokemonDto.getWeight(),
                pokemonDto.getSpeciesUrl(),
                pokemonDto.getSpeciesName());

    }

    /**
     * Method showing all pokemons available in local database.
     *
     * @return json data
     */
    public List<PokemonDto> showAll() {
        class PokeMapper implements RowMapper<PokemonDto> {
            @Override
            public PokemonDto mapRow(ResultSet resultSet, int i) throws SQLException {
                PokemonDto pokemonDto = new PokemonDto();

                pokemonDto.setWeight(resultSet.getString("weight"));
                pokemonDto.setName(resultSet.getString("name"));
                pokemonDto.setSpeciesName(resultSet.getString("speciesName"));
                pokemonDto.setSpeciesUrl(resultSet.getString("speciesUrl"));

                return pokemonDto;
            }
        }

        String hj = "SELECT * FROM pokemons";
        return jdbcTemplate.query(hj, new PokeMapper());

    }

    /**
     * Method searching or pokemon in local database. If not available searching continues on
     * external source pokeapi.co/v2/pokemon/
     *
     * @param pokemonDto
     * @return
     */
    public PokemonDto findPokemon(PokemonDto pokemonDto) {
        class pokeMapper implements RowMapper<PokemonDto> {

            @Override
            public PokemonDto mapRow(ResultSet resultSet, int i) throws SQLException {
                PokemonDto pokemonDto1 = new PokemonDto();
                pokemonDto1.setId(resultSet.getInt("id"));
                pokemonDto1.setName(resultSet.getString("name"));
                pokemonDto1.setSpeciesUrl(resultSet.getString("speciesUrl"));
                pokemonDto1.setSpeciesName(resultSet.getString("speciesName"));
                pokemonDto1.setWeight(resultSet.getString("weight"));
                return pokemonDto1;
            }
        }

        String query = "SELECT * FROM pokemons where id=?";

        PokemonDto pokeFromDb;
        if(checkIfExist(pokemonDto.getId())>0) {
            return jdbcTemplate.queryForObject(query, new Object[]{pokemonDto.getId()}, new pokeMapper());
        }else{
            try{
               return pokemonService.getPokemonDto(String.valueOf(pokemonDto.getId()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    private int checkIfExist(int id){
        String query ="Select count(*) from pokemons where id=?";
        return jdbcTemplate.queryForObject(query,Integer.class,id);
    }
}

