package fsr.iao.web;

import java.awt.print.Pageable;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fsr.iao.dao.ProduitRepository;
import fsr.iao.entities.Produit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
@Controller
public class ProduitController  {
	@Autowired
	ProduitRepository produitRepository;
	@GetMapping(value="/produits")
	public String produits(Model model,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size,
			@RequestParam(name="keyword",defaultValue="")String keyword){	
		Page<Produit> produits=produitRepository.findByDesignationContains(keyword, PageRequest.of(page, size));
		model.addAttribute("produits", produits.getContent());
		model.addAttribute("pages", new int[produits.getTotalPages()]);
		model.addAttribute("currentPage",page);
		model.addAttribute("size", size);
		model.addAttribute("keyword", keyword);
		return  "produits";
	}
	
	@GetMapping(value="/deleteProduit")
	public String deleteProduit(@Param(value = "id") Long id,int page,int size,String keyword){
		produitRepository.deleteById(id);
	return "redirect:/produits?keyword="+keyword+"&page="+page+"&size="+size;	
	}

	@GetMapping(value="/formProduit")
	public String formProduit(Model model){
		model.addAttribute("produit", new Produit());
		return "formProduit";
	}
	@PostMapping(path="/addProduit")
	public String addProduit(Model model,@Valid Produit p,BindingResult bindingResult){
		if(bindingResult.hasErrors()) return "formProduit";
		Produit produit=produitRepository.save(p);
		model.addAttribute("produit", produit);
		return "formProduit";
	}
	@GetMapping(path="/editProduit")
	public String editProduit(Model model,Long id){
		Produit produit=produitRepository.findById(id).get();
		model.addAttribute("produit", produit);
		
	return "formProduit";	
	}
	@GetMapping(path="/index")
	public String index(){
		return "produits";
	}
	
	
	
}
