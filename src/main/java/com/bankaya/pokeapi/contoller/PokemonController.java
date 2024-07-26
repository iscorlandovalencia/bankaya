package com.bankaya.pokeapi.contoller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankaya.pokeapi.model.Pokemon;
import com.bankaya.pokeapi.service.PokemonService;
import com.bankaya.pokeapi.service.TransactionService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PokemonController.class);

	@Autowired
	PokemonService pokemonService;

	@Autowired
	TransactionService transactionService;

	@GetMapping("/pokeapi")
	public List<Pokemon> getPokeapi(HttpServletRequest request, HttpServletResponse response) {
		List<Pokemon> pokemonList = pokemonService.getPokemonList();

		transactionService.createTransaction(request, response);
		transactionService.getAllTransactions().stream().forEach(System.out::println);

		LOGGER.info("get list of all pokemon");
		return pokemonList;
	}

	@GetMapping("/ability/{name}")
	public List<String> abilities(@PathVariable(value = "name") String pokemonName, HttpServletRequest request,
			HttpServletResponse response) {
		transactionService.createTransaction(request, response);
		transactionService.getAllTransactions().stream().forEach(System.out::println);

		LOGGER.info("get list of all abilities from pokemon : " + pokemonName);

		return pokemonService.getListOfAbilitiesFrom(pokemonName);
	}

	@GetMapping("/experience/{name}")
	public int experience(@PathVariable(value = "name") String pokemonName, HttpServletRequest request,
			HttpServletResponse response) {
		transactionService.createTransaction(request, response);
		transactionService.getAllTransactions().stream().forEach(System.out::println);

		LOGGER.info("get experience from pokemon : " + pokemonName);

		return pokemonService.getExperienceFrom(pokemonName);
	}

	@GetMapping("/items/{name}")
	public List<String> heldItems(@PathVariable(value = "name") String pokemonName, HttpServletRequest request,
			HttpServletResponse response) {
		transactionService.createTransaction(request, response);
		transactionService.getAllTransactions().stream().forEach(System.out::println);

		LOGGER.info("get list of all held items from pokemon : " + pokemonName);

		return pokemonService.getListOfItemsFrom(pokemonName);
	}

	@GetMapping("/id/{name}")
	public int getPokemonId(@PathVariable(value = "name") String pokemonName, HttpServletRequest request,
			HttpServletResponse response) {
		transactionService.createTransaction(request, response);
		transactionService.getAllTransactions().stream().forEach(System.out::println);

		LOGGER.info("get id from pokemon name : " + pokemonName);

		return pokemonService.getIdFromName(pokemonName);
	}

	@GetMapping("/{id}")
	public String getPokemonName(@PathVariable(value = "id") int pokemonId, HttpServletRequest request,
			HttpServletResponse response) {
		transactionService.createTransaction(request, response);
		transactionService.getAllTransactions().stream().forEach(System.out::println);

		LOGGER.info("get name from pokemon id : " + pokemonId);

		return pokemonService.getNameFromId(pokemonId);
	}

	@GetMapping("/location/{name}")
	public List<String> getLocationAreaEncounters(@PathVariable(value = "name") String pokemonName,
			HttpServletRequest request, HttpServletResponse response) {
		transactionService.createTransaction(request, response);
		transactionService.getAllTransactions().stream().forEach(System.out::println);

		LOGGER.info("get location area encounters from pokemon : " + pokemonName);

		return pokemonService.getListOfLocationsFrom(pokemonName);
	}

}
