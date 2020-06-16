package fsr.iao.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Produit implements Serializable{
@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
@NotNull
@Size(min=5,max=30)
private String designation;
@DecimalMin("1")
private double prix;
@DecimalMin("1")
private int quantite;

}