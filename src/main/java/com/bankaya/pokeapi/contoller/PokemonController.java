package com.bankaya.pokeapi.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankaya.pokeapi.model.Pokemon;
import com.bankaya.pokeapi.service.PokemonService;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {
	
	@Autowired
	PokemonService pokemonService;
	
	@GetMapping("/pokeapi")
	public List<Pokemon> getPokeapi() {
		List<Pokemon> pokemonList = pokemonService.getPokemonList();
		return pokemonList;
	}
	
	@GetMapping("/ability/{name}")
	public List<String> abilities(@PathVariable(value = "name") String pokemonName){
		return pokemonService.getListOfAbilitiesFrom(pokemonName);
	}
	
	@GetMapping("/experience/{name}")
	public int experience(@PathVariable(value = "name") String pokemonName){
		return pokemonService.getExperienceFrom(pokemonName);
	}
	
	@GetMapping("/items/{name}")
	public List<String> heldItems(@PathVariable(value = "name") String pokemonName){
		return pokemonService.getListOfItemsFrom(pokemonName);
	}
	
	@GetMapping("/id/{name}")
	public int getPokemonId(@PathVariable(value = "name") String pokemonName){
		return pokemonService.getIdFromName(pokemonName);
	}
	
	@GetMapping("/{id}")
	public String getPokemonName(@PathVariable(value = "id") int pokemonId) {
		return pokemonService.getNameFromId(pokemonId);
	}
	
	@GetMapping("/location/{name}")
	public List<String> getLocationAreaEncounters(@PathVariable(value = "name") String pokemonName){
		return pokemonService.getListOfLocationsFrom(pokemonName);
	}
	
}
