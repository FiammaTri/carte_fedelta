package com.example.carte_fedelta.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.carte_fedelta.model.Card;
import com.example.carte_fedelta.repository.CardRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/card")
@CrossOrigin(origins = {})
public class CardController {
	
	@Autowired
	private CardRepository cardRepository;
	
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
}