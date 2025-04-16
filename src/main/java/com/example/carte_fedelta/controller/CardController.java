package com.example.carte_fedelta.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.carte_fedelta.model.Card;
import com.example.carte_fedelta.model.Store;
import com.example.carte_fedelta.repository.CardRepository;
import com.example.carte_fedelta.repository.StoreRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/card")
@CrossOrigin(origins = {})
public class CardController {
	
	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	// metodo che restituisce tutte le card
	@GetMapping
	public List<Card> getAllCards(){
		return cardRepository.findAll();
	}
	
	// metodo che restituisce una specifica card (indicata nell'URL via id)
	@GetMapping("/{id}")
	public Object getCardById(@PathVariable Long id, HttpServletResponse response) {
		
		Optional<Card> card = cardRepository.findById(id);
		
		if(!card.isPresent()) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return Collections.singletonMap("message", "Card non trovata");
		}
		
		return card.get();
	}
	
	// metodo per creare una card
	@PostMapping("/{idStore}")
	public Object createCard(@PathVariable("idStore") Long id, @RequestBody Card card, HttpServletResponse response) {
		Optional<Store> store = storeRepository.findById(id);
		if (!store.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Store non trovato"));
		}
	Card newCard = new Card();
	newCard.setId(null);
	newCard.setNumber(card.getNumber());
	newCard.setStore_name(store.get());
	return cardRepository.save(newCard);}
	}
