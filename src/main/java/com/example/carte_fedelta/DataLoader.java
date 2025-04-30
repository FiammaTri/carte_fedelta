package com.example.carte_fedelta;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.carte_fedelta.model.Card;
import com.example.carte_fedelta.model.Store;
import com.example.carte_fedelta.repository.CardRepository;
import com.example.carte_fedelta.repository.StoreRepository;

@Component
public class DataLoader implements CommandLineRunner {

	private final CardRepository cardRepository;
	private final StoreRepository storeRepository;

	private static final List<Map<String, String>> STORE = 
			List.of(Map.of("storeName", "Ikea", "logoName", "Ikea", "link", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/Ikea_logo.svg/1280px-Ikea_logo.svg.png"),
			Map.of("storeName", "Coop", "logoName", "Coop", "link", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Coop_Italia_logo.svg/2560px-Coop_Italia_logo.svg.png"),
			Map.of("storeName", "Esselunga", "logoName", "Esselunga", "link", "https://upload.wikimedia.org/wikipedia/it/thumb/a/a4/Esselunga_logo.svg/2560px-Esselunga_logo.svg.png"),
			Map.of("storeName", "Bar", "logoName", "Bar", "link", "https://static.vecteezy.com/system/resources/previews/023/617/247/non_2x/coffee-shop-logo-free-png.png"),
			Map.of("storeName", "Moby Dick", "logoName", "Moby Dick", "link", "https://www.assirm.it/wp-content/uploads/2023/03/moby_color-scaled.jpg"));

	private static final List<Map<String, Long>> CARD = List.of(Map.of("number", 123456123456L), Map.of("number", 654321654321L),
			Map.of("number", 112233112233L), Map.of("number", 445566445566L), Map.of("number", 111111111111L));

	@Autowired
	public DataLoader(CardRepository cardRepository, StoreRepository storeRepository) {
		this.cardRepository = cardRepository;
		this.storeRepository = storeRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		if (storeRepository.count() == 0) {

			// Creazione STORE
			List<Store> stores = STORE.stream()
					.map(store -> new Store(null, store.get("storeName"), store.get("logoName"), store.get("link")))
					.collect(Collectors.toList());
			List<Store> savedStore = storeRepository.saveAll(stores);
		
			
			Random random = new Random();
			List<Card> cards = CARD.stream().map(card -> new Card(null, card.get("number"), getRandomStore(random, savedStore)))
					.collect(Collectors.toList());
			List<Card> savedCard = cardRepository.saveAll(cards);
		}

	}

	public static Store getRandomStore(Random random, List<Store> savedStore) {
		int index = random.nextInt(savedStore.size());
		return savedStore.get(index);
	}

}

/*
 * package com.example.GestionaleTicketing;
 * 
 * import java.time.LocalDate; import java.util.Iterator; import java.util.List;
 * import java.util.Map; import java.util.Random; import
 * java.util.stream.Collectors; import java.util.stream.Stream;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.CommandLineRunner; import
 * org.springframework.stereotype.Component;
 * 
 * import com.example.GestionaleTicketing.model.CategoriaTicket; import
 * com.example.GestionaleTicketing.model.Messaggio; import
 * com.example.GestionaleTicketing.model.Ticket; import
 * com.example.GestionaleTicketing.model.Utente; import
 * com.example.GestionaleTicketing.repository.CategoriaTicketRepository; import
 * com.example.GestionaleTicketing.repository.MessaggioRepository; import
 * com.example.GestionaleTicketing.repository.TicketRepository; import
 * com.example.GestionaleTicketing.repository.UtenteRepository;
 * 
 * @Component public class DataLoader implements CommandLineRunner {
 * 
 * private final UtenteRepository utenteRepository; private final
 * CategoriaTicketRepository categoriaTicketRepository; private final
 * MessaggioRepository messaggioRepository; private final TicketRepository
 * ticketRepository;
 * 
 * private static final String[] CATEGORIA = { "Ordine e spedizioni",
 * "Account accesso", "Pagamento e fatturazione", "Rimborso" };
 * 
 * private static final List<Map<String, String>> ADMINS = List.of(
 * Map.of("nome", "Hazem", "cognome", "Rafat", "email", "Haze@gmail.com",
 * "password", "12345"),
 * 
 * private static final List<Map<String, String>> OPERATORI = List.of(
 * Map.of("nome", "Alice", "cognome", "Rossi", "email", "alice.rossi@gmail.com",
 * "password", "12345"),
 * 
 * 
 * private static final List<Map<String, String>> UTENTI = List.of(
 * Map.of("nome", "Antonio", "cognome", "Gallo", "email",
 * "antonio.gallo@gmail.com", "password", "12345"),
 * 
 * 
 * private static final String[] OGGETTO = { "Richiesta di assistenza tecnica",
 * "Problema con il mio account", "Errore nel sistema",
 * "Problemi con il pagamento", "Richiesta di supporto urgente",
 * "Domanda sul mio ordine", "Richiesta di informazioni",
 * "Errore di sistema durante la navigazione", "Richiesta di modifica dati",
 * "Problema con la connessione", "Ticket di supporto urgente",
 * "Impossibile completare la transazione",
 * "Errore nella configurazione dell'account",
 * "Richiesta di restituzione prodotto",
 * "Assistenza per il reset della password" }; private static final String[]
 * MESSAGGI_UTENTE = {
 * "Non riesco ad accedere al mio account. Mi dice che la password è errata, ma sono sicuro che sia corretta."
 * , o." }; private static final String[] MESSAGGI_OPERATORE = {
 * "Grazie per averci contattato. Siamo spiacenti per il problema riscontrato e provvederemo a risolverlo il prima possibile."
 * ,
 * "Abbiamo ricevuto la tua richiesta di rimborso. Ti informeremo non appena il processo sarà completato."
 * ,
 * 
 * "Ti invitiamo a fornire maggiori dettagli sul problema per poterti aiutare al meglio."
 * };
 * 
 * @Autowired public DataLoader(UtenteRepository utenteRepository,
 * CategoriaTicketRepository categoriaTicketRepository, MessaggioRepository
 * messaggioRepository, TicketRepository ticketRepository) {
 * this.utenteRepository = utenteRepository; this.categoriaTicketRepository =
 * categoriaTicketRepository; this.messaggioRepository = messaggioRepository;
 * this.ticketRepository = ticketRepository; }
 * 
 * @Override public void run(String... args) throws Exception { if
 * (utenteRepository.count() == 0) {
 * 
 * // Creazione ADMINS List<Utente> admins = ADMINS.stream().map(admin -> new
 * Utente(null, admin.get("nome"), admin.get("cognome"), admin.get("email"),
 * admin.get("password"), Utente.Ruolo.Admin)).collect(Collectors.toList());
 * utenteRepository.saveAll(admins);
 * 
 * // Creazione Utenti List<Utente> utenti = UTENTI .stream().map(utente -> new
 * Utente(null, utente.get("nome"), utente.get("cognome"), utente.get("email"),
 * utente.get("password"), Utente.Ruolo.Utente)) .collect(Collectors.toList());
 * List<Utente> savedUtenti = utenteRepository.saveAll(utenti);
 * 
 * // Creazione Categorie List<CategoriaTicket> categorie = Stream.of(CATEGORIA)
 * .map(categoria -> new CategoriaTicket(null,
 * categoria)).collect(Collectors.toList()); List<CategoriaTicket>
 * savedCategoria = categoriaTicketRepository.saveAll(categorie);
 * 
 * // Creazione Operatori Iterator<Map<String, String>> operatoriIterator =
 * OPERATORI.iterator(); Random random = new Random(); Ticket.Status[] status =
 * Ticket.Status.values(); savedCategoria.forEach(categoria -> {
 * 
 * for (int i = 0; i < 2 && operatoriIterator.hasNext(); i++) { Map<String,
 * String> utente = operatoriIterator.next(); Utente savedOperatore =
 * utenteRepository .save(new Utente(null, utente.get("nome"),
 * utente.get("cognome"), utente.get("email"), utente.get("password"),
 * Utente.Ruolo.Operatore, categoria));
 * 
 * for (int j = 0; j < 10; j++) { // Creazione Ticket + Messaggio Ticket.Status
 * ticketStatus = getRandomTicketStatus(random, status); LocalDate dataApertura
 * = getRandomDate(random, LocalDate.now().minusYears(1), LocalDate.now());
 * Ticket ticket = new Ticket(null, getRandomOggetto(random), ticketStatus,
 * dataApertura, categoria, getRandomUtente(random, savedUtenti),
 * savedOperatore);
 * 
 * Messaggio messaggio = new Messaggio(); if (ticketStatus ==
 * Ticket.Status.CHIUSO) {
 * messaggio.setCorpoOperatore(getRandomMessaggioOperatore(random));
 * ticket.setDataChiusura(getRandomDate(random, dataApertura, LocalDate.now()));
 * } Ticket savedTicket = ticketRepository.save(ticket);
 * messaggio.setCorpoUtente(getRandomMessaggioUtente(random));
 * messaggio.setTicket(savedTicket); Messaggio savedMessaggio =
 * messaggioRepository.save(messaggio); } }
 * 
 * });
 * 
 * }
 * 
 * }
 * 
 * public static Utente getRandomUtente(Random random, List<Utente> savedUtenti)
 * { int index = random.nextInt(savedUtenti.size()); return
 * savedUtenti.get(index); }
 * 
 * public static String getRandomOggetto(Random random) { int index =
 * random.nextInt(OGGETTO.length); return OGGETTO[index]; }
 * 
 * public static String getRandomMessaggioUtente(Random random) { int index =
 * random.nextInt(MESSAGGI_UTENTE.length); return MESSAGGI_UTENTE[index]; }
 * 
 * public static String getRandomMessaggioOperatore(Random random) { int index =
 * random.nextInt(MESSAGGI_OPERATORE.length); return MESSAGGI_OPERATORE[index];
 * }
 * 
 * public static Ticket.Status getRandomTicketStatus(Random random,
 * Ticket.Status[] status) { int index = random.nextInt(status.length); return
 * status[index]; }
 * 
 * public static LocalDate getRandomDate(Random random, LocalDate start,
 * LocalDate end) { long startEpoch = start.toEpochDay(); long endEpoch =
 * end.toEpochDay(); long randomEpochDay = startEpoch + random.nextLong(endEpoch
 * - startEpoch); return LocalDate.ofEpochDay(randomEpochDay); } }
 */
