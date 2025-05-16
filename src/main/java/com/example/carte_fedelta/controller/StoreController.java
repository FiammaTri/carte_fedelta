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

	// metodo che restituisce tutti gli store
	@GetMapping
	public List<Store> getAllStore() {
		return storeRepository.findAll();
	}

	@GetMapping("/{id}")
	public Object getStoreById(@PathVariable Long id, HttpServletResponse response) {
		Optional<Store> store = storeRepository.findById(id);

		if (!store.isPresent()) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return Collections.singletonMap("message", "Store non trovato");
		}

		return store.get();
	}

	// metodo per creare uno store
	@PostMapping
	public Object createStore(@RequestParam("logo") MultipartFile logo, @RequestParam("store_name") String name)
			throws IOException {
		Store store = new Store();
		store.setId(null);
		store.setName(name);
		store.setLogo(logo.getBytes());
		store.setLogo_name(logo.getOriginalFilename());
		storeRepository.save(store);
		return Collections.singletonMap("message", "Store aggiunto con successo");

	}

	// metodo per cancellare uno store
	@DeleteMapping("/{idStore}")
	public void deleteStore(@PathVariable("idStore") Long id, HttpServletResponse response) {
		Optional<Store> deleteStore = storeRepository.findById(id);

		storeRepository.delete(deleteStore.get());
	}

	// metodo per modificare uno store
		@PutMapping("/{idStore}")
		public Object editCard(@PathVariable("idStore") Long id, @RequestParam("store_name") String name, @RequestParam("logo") MultipartFile logo, HttpServletResponse response)throws IOException {
			Optional<Store> editStore = storeRepository.findById(id);
			if (!editStore.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Carta non trovata"));
			}
			Store newStore = editStore.get();
			newStore.setName(name);
			newStore.setLogo(logo.getBytes());
			newStore.setLogo_name(logo.getOriginalFilename());
			storeRepository.save(newStore);
			return Collections.singletonMap("message", "Store modificato con successo");

		}
}
