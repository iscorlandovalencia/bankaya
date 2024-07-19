package com.bankaya.pokeapi.model;

import java.util.List;

import lombok.Data;

@Data
public class PokemonDetails {

	private List<Abilities> abilities;
	private int baseExperience;
	private List<Item> heldItems;
	private Long Id;
	private String name;
	private String locationAreaEncounters;
	
}
