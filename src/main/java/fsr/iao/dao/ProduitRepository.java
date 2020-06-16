package fsr.iao.dao;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import fsr.iao.entities.Produit;

public interface ProduitRepository  extends JpaRepository<Produit,Long>{
	public Page<Produit> findByDesignationContains(String keyword,Pageable pageable);

}
