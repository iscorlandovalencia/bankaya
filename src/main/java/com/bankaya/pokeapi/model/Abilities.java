package com.bankaya.pokeapi.model;

import lombok.Data;

@Data
public class Abilities {
	
	private Ability ability;
	private boolean is_hidden;
	private int slot;
	
}
