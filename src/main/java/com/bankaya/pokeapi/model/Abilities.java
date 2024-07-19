package com.bankaya.pokeapi.model;

import java.util.List;

import lombok.Data;

@Data
public class Abilities {
	
	private List<Ability> ability;
	private boolean isHidden;
	private int slot;
	
}
