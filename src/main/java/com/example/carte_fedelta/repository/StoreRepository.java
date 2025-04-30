package com.example.carte_fedelta.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.carte_fedelta.model.Store;

@Repository
public interface StoreRepository extends JpaRepository <Store, Long>{
	
}
