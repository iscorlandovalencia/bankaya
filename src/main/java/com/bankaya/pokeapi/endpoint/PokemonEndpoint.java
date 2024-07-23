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

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAbilitiesRequest")
	@ResponsePayload
	public GetAbilitiesResponse getAbilities(@RequestPayload GetAbilitiesRequest request) {
		GetAbilitiesResponse response = new GetAbilitiesResponse();
		response.getAbilities().addAll( pokemonService.getListOfAbilitiesFrom(request.getName()) );
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getExperienceRequest")
	@ResponsePayload
	public GetExperienceResponse getExperience(@RequestPayload GetExperienceRequest request) {
		GetExperienceResponse response = new GetExperienceResponse();
		response.setExperience( BigInteger.valueOf( pokemonService.getExperienceFrom(request.getName())) );
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getItemsRequest")
	@ResponsePayload
	public GetItemsResponse getHeldItems(@RequestPayload GetItemsRequest request) {
		GetItemsResponse response = new GetItemsResponse();
		response.getItems().addAll( pokemonService.getListOfItemsFrom(request.getName()) );
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getIdRequest")
	@ResponsePayload
	public GetIdResponse getPokemonId(@RequestPayload GetIdRequest request) {
		GetIdResponse response = new GetIdResponse();
		response.setId( BigInteger.valueOf( pokemonService.getIdFromName(request.getName())) );
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getNameRequest")
	@ResponsePayload
	public GetNameResponse getPokemonName(@RequestPayload GetNameRequest request) {
		int pokemonId = request.getId().intValue();
		GetNameResponse response = new GetNameResponse();
		response.setName( pokemonService.getNameFromId( pokemonId ));
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLocationAreaRequest")
	@ResponsePayload
	public GetLocationAreaResponse getLocationAreaEncounters(@RequestPayload GetLocationAreaRequest request) {
		GetLocationAreaResponse response = new GetLocationAreaResponse();
		response.getLocationArea().addAll( pokemonService.getListOfLocationsFrom(request.getName()) );
		return response;
	}

}
