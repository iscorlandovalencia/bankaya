package com.bankaya.pokeapi.model;

import java.util.List;

import lombok.Data;

@Data
public class PokemonBase {

	private List<Abilities> abilities;
	private int base_experience;
	private List<HeldItem> held_items;
	private int Id;
	private String name;
	private String location_area_encounters;
	
}
