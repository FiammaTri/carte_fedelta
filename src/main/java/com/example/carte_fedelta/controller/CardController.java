package com.example.carte_fedelta.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	
	@GetMapping("/store/{storeName}")
	public List<Card> getCardByStoreName (@PathVariable String storeName) {
		return cardRepository.findByStore_StoreNameContaining(storeName);
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
	newCard.setStore(store.get());
	return cardRepository.save(newCard);
	}
	
	// metodo per modificare una card
	@PutMapping("/{idCard}")
	public Object editCard(@PathVariable("idCard") Long id, @RequestBody Card card, HttpServletResponse response) {
		Optional<Card> editCard = cardRepository.findById(id);
		if (!editCard.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Carta non trovata"));
		}
		Card newCard = editCard.get();
		newCard.setNumber(card.getNumber());
		return cardRepository.save(newCard);
	}
	
	// metodo per cancellare una card
	@DeleteMapping("/{idCard}")
	public void deleteCard(@PathVariable("idCard") Long id, HttpServletResponse response) {
		Optional<Card> deleteCard = cardRepository.findById(id);
		
		cardRepository.delete(deleteCard.get());
		}
	
	
}
