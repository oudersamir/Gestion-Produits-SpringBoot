package fsr.iao;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import fsr.iao.dao.ProduitRepository;
import fsr.iao.entities.Produit;
@SpringBootApplication
public class MyProduitsApplication implements CommandLineRunner {
	@Autowired
    ProduitRepository produitRepository;
	public static void main(String[] args)  {
		ApplicationContext ctx =SpringApplication.run(MyProduitsApplication.class, args);
	
      /*  System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }*/
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/*produitRepository.save(new Produit(null,"pc lenovo 1233",3.4,1));
		produitRepository.save(new Produit(null,"pc hp ",3.4,1));
		produitRepository.save(new Produit(null,"pc asus",3.4,1));
		produitRepository.save(new Produit(null,"pc macbook",3.4,1));*/
		
		

	}

}
