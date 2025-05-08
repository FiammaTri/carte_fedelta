package com.example.carte_fedelta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.example.carte_fedelta.model.Card;


@Repository
public interface CardRepository extends JpaRepository <Card, Long>{
	List<Card> findByStore_StoreNameContaining(String storeName);


}
