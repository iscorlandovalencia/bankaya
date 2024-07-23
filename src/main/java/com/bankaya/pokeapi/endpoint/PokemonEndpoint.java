package com.bankaya.pokeapi.endpoint;

import java.math.BigInteger;

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

@Endpoint
public class PokemonEndpoint {

	private static final String NAMESPACE_URI = "http://www.baeldung.com/springsoap/gen";

	@Autowired
	private PokemonService pokemonService;

	/*
	 * @PayloadRoot(namespace = NAMESPACE_URI, localPart = "Abilities")
	 * 
	 * @ResponsePayload public List<> getCountry(@RequestPayload GetCountryRequest request) { 
	 * GetCountryResponse response = new GetCountryResponse();
	 * response.setCountry(countryRepository.findCountry(request.getName()));
	 * 
	 * return response; }
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "Abilities")
	@ResponsePayload
	public GetAbilitiesResponse abilities(@RequestPayload GetAbilitiesRequest pokemonName) {
		GetAbilitiesResponse response = new GetAbilitiesResponse();
		response.getAbilities().addAll( pokemonService.getListOfAbilitiesFrom(pokemonName.getName()) );
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "Experience")
	@ResponsePayload
	public GetExperienceResponse experience(@RequestPayload GetExperienceRequest pokemonName) {
		GetExperienceResponse response = new GetExperienceResponse();
		response.setExperience( BigInteger.valueOf( pokemonService.getExperienceFrom(pokemonName.getName())) );
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "HeldItems")
	@ResponsePayload
	public GetItemsResponse heldItems(@RequestPayload GetItemsRequest pokemonName) {
		GetItemsResponse response = new GetItemsResponse();
		response.getItems().addAll( pokemonService.getListOfItemsFrom(pokemonName.getName()) );
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "Id")
	@ResponsePayload
	public GetIdResponse getPokemonId(@RequestPayload GetIdRequest pokemonName) {
		GetIdResponse response = new GetIdResponse();
		response.setId( BigInteger.valueOf( pokemonService.getIdFromName(pokemonName.getName())) );
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "Name")
	@ResponsePayload
	public GetNameResponse getPokemonName(@RequestPayload GetNameRequest pokemonId) {
		GetNameResponse response = new GetNameResponse();
		response.setName( pokemonService.getNameFromId( pokemonId.getId().intValue() ));
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "LocationAreaEncounters")
	@ResponsePayload
	public GetLocationAreaResponse getLocationAreaEncounters(@RequestPayload GetLocationAreaRequest pokemonName) {
		GetLocationAreaResponse response = new GetLocationAreaResponse();
		response.getLocationArea().addAll( pokemonService.getListOfLocationsFrom(pokemonName.getName()) );
		return response;
	}

}
