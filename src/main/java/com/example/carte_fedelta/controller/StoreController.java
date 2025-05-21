package com.example.carte_fedelta.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.carte_fedelta.model.Card;
import com.example.carte_fedelta.model.Store;
import com.example.carte_fedelta.repository.StoreRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/store")
@CrossOrigin(origins = {})
public class StoreController {

	@Autowired
	private StoreRepository storeRepository;

	/* 
	 * metodo che restituisce tutti gli store
	 * 
	 * @return list con tutti gli store
	 */
	@GetMapping
	public List<Store> getAllStore() {
		return storeRepository.findAll();
	}
	
	/*
	 * metodo per la ricerca di un solo solo store (tramite id)
	 * 
	 * @param id -> id dello store
	 * @param response -> oggetto per inviare il codice di risposta al client
	 * 
	 * @return oggetto con le informazioni dello store ricercato, se presente
	 */
	@GetMapping("/{id}")
	public Object getStoreById(@PathVariable Long id, HttpServletResponse response) {
		Optional<Store> store = storeRepository.findById(id);

		if (!store.isPresent()) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return Collections.singletonMap("message", "Store non trovato");
		}

		return store.get();
	}
	
	/* 
	 * metodo per ottenere uno store dal parametro storeName
	 * 
	 * @param storeName	-> stringa contente il nome dello store
	 * @param response
	 * 
	 * @return oggetto cone le informazioni dello store
	 */
	@GetMapping("/byName/{storeName}")
	public Object getStoreByStoreName(@PathVariable("storeName") String storeName, HttpServletResponse response) {
		Optional<Store> store = storeRepository.findByStoreName(storeName);

		if (!store.isPresent()) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return Collections.singletonMap("message", "Store non trovato");
		}

		return store.get();
	}

	/*
	 * metodo per creare uno store
	 * 
	 * @param logo -> di tipo multipartfile per la gestione delle immagini
	 * @param name -> stringa contenente il nome dello store
	 * 
	 * @return messaggio di conferma
	 */
	@PostMapping
	public Object createStore(@RequestParam("logo") MultipartFile logo, @RequestParam("store_name") String name)
			throws IOException {
		Store store = new Store();
		store.setId(null);
		store.setStoreName(name);
		store.setLogo(logo.getBytes());
		store.setLogoName(logo.getOriginalFilename());
		storeRepository.save(store);
		return Collections.singletonMap("message", "Store aggiunto con successo");

	}
	

	/*
	 * metodo per modificare uno store
	 * 
	 * @param id dello store da modificare (fine endpoint)
	 * @param name -> nuovo nome dello store
	 * @param file -> nuova immagine dello store
	 * @param response
	 * 
	 * @return messaggio di conferma della modifica
	 */
	@PutMapping("/{idStore}")
	public Object editCard(@PathVariable("idStore") Long id, @RequestParam("store_name") String name, @RequestParam("logo") MultipartFile logo, HttpServletResponse response)throws IOException {
			Optional<Store> editStore = storeRepository.findById(id);
			if (!editStore.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(Collections.singletonMap("message", "Carta non trovata"));
			}
			Store newStore = editStore.get();
			newStore.setStoreName(name);
			newStore.setLogo(logo.getBytes());
			newStore.setLogoName(logo.getOriginalFilename());
			storeRepository.save(newStore);
			return Collections.singletonMap("message", "Store modificato con successo");

	}

	/*
	 * metodo per cancellare uno store
	 * 
	 * @param id dello store da eliminare
	 * @param response
	 * 
	 * @return messaggio di conferma (o errore se lo store non viene trovato)
	 */
	@DeleteMapping("/{idStore}")
	public Object deleteStore(@PathVariable("idStore") Long id, HttpServletResponse response) {
		Optional<Store> deleteStore = storeRepository.findById(id);

		if(!deleteStore.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(Collections.singletonMap("message", "Store non trovato"));
		}
		
		storeRepository.delete(deleteStore.get());
		return ResponseEntity.status(HttpStatus.OK)
				.body(Collections.singletonMap("message", "Store cancellato"));
	}

}
