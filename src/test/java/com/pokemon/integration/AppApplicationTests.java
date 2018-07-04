package com.pokemon.integration;

import com.pokemon.dto.AbilitiesDto;
import com.pokemon.dto.PokemonDto;
import com.pokemon.dto.StatsDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AppApplicationTests {

	@Autowired
	TestRestTemplate testRestTemplate;


	@Test
	public void shouldGetResponseBody() {
		PokemonDto body = this.testRestTemplate.getForObject("/pokemon?id=1",PokemonDto.class);
		assertThat(body.getName()).isEqualTo("bulbasaur");
	}
	@Test
	public void shouldGetAbilities(){
		AbilitiesDto body = this.testRestTemplate.getForObject("/pokemon?id=1",AbilitiesDto.class);
		assertThat(body.getAbilityName()).toString().equals("chlorophyll");
	}
	@Test
	public void shouldGetStats(){
		StatsDto body = this.testRestTemplate.getForObject("/pokemon?id=1",StatsDto.class);
		assertThat(body.getStatName()).toString().equals("speed");
	}
	@Test
	public void shouldGetStatusOk(){
		ResponseEntity<PokemonDto> response =
				testRestTemplate.getForEntity("/pokemon?id=1", PokemonDto.class);
		assertThat(response.getStatusCode().is2xxSuccessful());
	}
	@Test
	public void shouldGetStatus404(){
		ResponseEntity<PokemonDto> response =
				testRestTemplate.getForEntity("/pokemon", PokemonDto.class);
		System.out.println(response.getStatusCode().is4xxClientError());
		assertEquals(404, response.getStatusCodeValue());
//		assertThat(response.getStatusCode().is4xxClientError()==false);
	}


}
