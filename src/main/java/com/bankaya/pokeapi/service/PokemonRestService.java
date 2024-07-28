package com.bankaya.pokeapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bankaya.pokeapi.model.Encounters;
import com.bankaya.pokeapi.model.Pokemon;
import com.bankaya.pokeapi.model.PokemonBase;
import com.bankaya.pokeapi.model.PokemonLimit;

@Service
public class PokemonRestService {

	private static final String POKEMON_BASE_URI = "https://pokeapi.co/api/v2/";
	private static final String POKEMON = "pokemon";
	private static final String SLASH = "/";
	private static final String LIMIT = "?limit=1302";
	
	private static final Logger LOGGER = Logger.getLogger(PokemonRestService.class.getName());
	
	@Autowired
	RestTemplate restTemplate;
	
	public List<Pokemon> getPokemonList() {
		PokemonLimit response = restTemplate.getForObject(POKEMON_BASE_URI + POKEMON + LIMIT, PokemonLimit.class);
		return getPokemonIdFromUrl(response.getResults());
	}
	
	public PokemonBase getPokemonBase(int pokemonId) {
		
		PokemonBase response = restTemplate.getForObject(POKEMON_BASE_URI + POKEMON + SLASH + pokemonId, PokemonBase.class);
		PokemonBase pokemonBase = new PokemonBase();
		
		pokemonBase.setAbilities(response.getAbilities());
		pokemonBase.setBase_experience(response.getBase_experience());
		pokemonBase.setHeld_items(response.getHeld_items());
		pokemonBase.setId(response.getId());
		pokemonBase.setName(response.getName());
		pokemonBase.setLocation_area_encounters(response.getLocation_area_encounters());
				
		return pokemonBase;
	}
	
	public List<String> getEncounters(int pokemonId){
		String encountersUrl = POKEMON_BASE_URI + POKEMON + SLASH + pokemonId + SLASH + "encounters";
		
		Encounters[] response = restTemplate.getForObject(encountersUrl, Encounters[].class);
		List<Encounters> encounters = List.of(response);
		List<String> locationAreas = new ArrayList<>();
		
		encounters.stream().forEach(encounter -> {
			locationAreas.add(encounter.getLocation_area().getName());
		});
		
		return locationAreas;
	}
	
	private List<Pokemon> getPokemonIdFromUrl(List<Pokemon> pokemonList) {
		List<Pokemon> pokemonListResult = new ArrayList<>();

		try {
			pokemonList.stream().forEach(pokemon -> {
				String[] args = pokemon.getUrl().split(SLASH);
				String getId = args[args.length - 1];
				pokemon.setId(Integer.valueOf(getId));
				pokemonListResult.add(pokemon);
			});
		} catch (NumberFormatException nfe) {
			LOGGER.log(Level.SEVERE, "ERROR al convertir el Id del pokemon desde la url : \\n" + nfe.toString(), nfe);
		}
		return pokemonListResult;
	}
	
	public String getNameFromId(int pokemonId) {
		return getPokemonList()
				.stream()
				.filter(pokemon -> pokemonId == pokemon.getId())
				.findAny()
				.orElse(null)
				.getName();
	}
	
	public int getIdFromName(String name) {
		return getPokemonList()
				.stream()
				.filter(pokemon -> name.equals(pokemon.getName()))
				.findAny()
				.orElse(null)
				.getId();
	}
}
