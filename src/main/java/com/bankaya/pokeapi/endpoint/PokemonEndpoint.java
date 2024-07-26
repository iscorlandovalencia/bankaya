package com.bankaya.pokeapi.endpoint;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.bankaya.pokeapi.model.gen.GetAbilitiesRequest;
import com.bankaya.pokeapi.model.gen.GetAbilitiesResponse;
import com.bankaya.pokeapi.model.gen.GetExperienceRequest;
import com.bankaya.pokeapi.model.gen.GetExperienceResponse;
import com.bankaya.pokeapi.model.gen.GetIdRequest;
import com.bankaya.pokeapi.model.gen.GetIdResponse;
import com.bankaya.pokeapi.model.gen.GetItemsRequest;
import com.bankaya.pokeapi.model.gen.GetItemsResponse;
import com.bankaya.pokeapi.model.gen.GetLocationAreaRequest;
import com.bankaya.pokeapi.model.gen.GetLocationAreaResponse;
import com.bankaya.pokeapi.model.gen.GetNameRequest;
import com.bankaya.pokeapi.model.gen.GetNameResponse;
import com.bankaya.pokeapi.service.PokemonService;
import com.bankaya.pokeapi.service.TransactionService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Endpoint
public class PokemonEndpoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(PokemonEndpoint.class);
	private static final String NAMESPACE_URI = "org/bankaya/pokeapi/model/gen";

	@Autowired
	private PokemonService pokemonService;

	@Autowired
	TransactionService transactionService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAbilitiesRequest")
	@ResponsePayload
	public GetAbilitiesResponse getAbilities(@RequestPayload GetAbilitiesRequest request,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		GetAbilitiesResponse response = new GetAbilitiesResponse();
		response.getAbilities().addAll(pokemonService.getListOfAbilitiesFrom(request.getName()));
		
		transactionService.createTransaction(httpRequest, httpResponse);
		transactionService.getAllTransactions().stream().forEach(System.out::println);

		LOGGER.info("get list of all abilities from pokemon : " + request.getName());
		
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getExperienceRequest")
	@ResponsePayload
	public GetExperienceResponse getExperience(@RequestPayload GetExperienceRequest request,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		GetExperienceResponse response = new GetExperienceResponse();
		response.setExperience(BigInteger.valueOf(pokemonService.getExperienceFrom(request.getName())));
		
		transactionService.createTransaction(httpRequest, httpResponse);
		transactionService.getAllTransactions().stream().forEach(System.out::println);
		
		LOGGER.info("get experience from pokemon : " + request.getName());
		
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getItemsRequest")
	@ResponsePayload
	public GetItemsResponse getHeldItems(@RequestPayload GetItemsRequest request, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		GetItemsResponse response = new GetItemsResponse();
		response.getItems().addAll(pokemonService.getListOfItemsFrom(request.getName()));
		
		transactionService.createTransaction(httpRequest, httpResponse);
		transactionService.getAllTransactions().stream().forEach(System.out::println);

		LOGGER.info("get list of all held items from pokemon : " + request.getName());
		
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getIdRequest")
	@ResponsePayload
	public GetIdResponse getPokemonId(@RequestPayload GetIdRequest request, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		GetIdResponse response = new GetIdResponse();
		response.setId(BigInteger.valueOf(pokemonService.getIdFromName(request.getName())));
		
		transactionService.createTransaction(httpRequest, httpResponse);
		transactionService.getAllTransactions().stream().forEach(System.out::println);

		LOGGER.info("get id from pokemon name : " + request.getName());
		
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getNameRequest")
	@ResponsePayload
	public GetNameResponse getPokemonName(@RequestPayload GetNameRequest request, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		int pokemonId = request.getId().intValue();
		GetNameResponse response = new GetNameResponse();
		response.setName(pokemonService.getNameFromId(pokemonId));
		
		transactionService.createTransaction(httpRequest, httpResponse);
		transactionService.getAllTransactions().stream().forEach(System.out::println);

		LOGGER.info("get name from pokemon id : " + pokemonId);
		
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLocationAreaRequest")
	@ResponsePayload
	public GetLocationAreaResponse getLocationAreaEncounters(@RequestPayload GetLocationAreaRequest request,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		GetLocationAreaResponse response = new GetLocationAreaResponse();
		response.getLocationArea().addAll(pokemonService.getListOfLocationsFrom(request.getName()));
		
		transactionService.createTransaction(httpRequest, httpResponse);
		transactionService.getAllTransactions().stream().forEach(System.out::println);

		LOGGER.info("get location area encounters from pokemon : " + request.getName());
		
		return response;
	}

}
