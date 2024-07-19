package com.bankaya.pokeapi.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@NoArgsConstructor
@ToString
public class PokemonLimit {

	private int count;
	private String next;
	private String previous;
	private List<Pokemon> results;
	
}
