package com.bankaya.pokeapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankaya.pokeapi.model.Pokemon;
import com.bankaya.pokeapi.model.PokemonBase;
import com.bankaya.pokeapi.model.PokemonDetails;

@Service
public class PokemonService {

	@Autowired
	PokemonRestService pokemonRestService;

	public PokemonDetails getPokemonDetails(String pokemonName) {
		int pokemonId = pokemonRestService.getIdFromName(pokemonName);
		PokemonBase pokemonBase = pokemonRestService.getPokemonBase(pokemonId);
		List<String> locationAreaList = pokemonRestService.getEncounters(pokemonId);

		PokemonDetails pokemonDetails = new PokemonDetails();

		pokemonDetails.setAbilities(pokemonBase.getAbilities());
		pokemonDetails.setBase_experience(pokemonBase.getBase_experience());
		pokemonDetails.setHeld_items(pokemonBase.getHeld_items());
		pokemonDetails.setId(pokemonBase.getId());
		pokemonDetails.setName(pokemonBase.getName());
		pokemonDetails.setLocation_area_encounters(locationAreaList);

		return pokemonDetails;
	}

	public List<String> getListOfAbilitiesFrom(String pokemonName) {
		return getPokemonDetails(pokemonName).getAbilities()
				.stream()
				.map(ability -> ability.getAbility().getName())
				.collect(Collectors.toList());
	}

	public List<Pokemon> getAllPokemon() {
		return pokemonRestService.getPokemonList();
	}

	public int getExperienceFrom(String pokemonName) {
		return getPokemonDetails(pokemonName).getBase_experience();
	}

	public List<String> getListOfItemsFrom(String pokemonName) {
		return getPokemonDetails(pokemonName).getHeld_items().stream()
				.map(heldItem -> heldItem.getItem().getName())
				.collect(Collectors.toList());
	}

	public int getIdFrom(String pokemonName) {
		return getPokemonDetails(pokemonName).getId();
	}

	public String getNameFrom(int pokemonId) {
		String pokemonName = pokemonRestService.getNameFromId(pokemonId);
		return getPokemonDetails(pokemonName).getName();
	}

	public List<String> getListOfLocationsFrom(String pokemonName) {
		return getPokemonDetails(pokemonName).getLocation_area_encounters();
	}

}
