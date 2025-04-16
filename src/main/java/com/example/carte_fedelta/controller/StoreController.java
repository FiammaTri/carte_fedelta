package com.example.carte_fedelta.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
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

	@PostMapping
	public Object createStore(@RequestParam("logo") MultipartFile logo, @RequestParam("store_name") String name)
			throws IOException {
		Store store = new Store();
		store.setId(null);
		store.setName(name);
		store.setLogo(logo.getBytes());
		store.setLogo_name(logo.getOriginalFilename());
		storeRepository.save(store);
		return Collections.singletonMap("message", "store aggiunto con successo");
		
	}

}
