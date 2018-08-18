package com.capgemini.storeserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.storeserver.beans.Merchant;


@Repository
public interface MerchantRepo extends JpaRepository<Merchant, Integer>{

//	@Query("select merchant from  Merchant merchant")
//	public List<Merchant> findAllMerchants();
	
}
