/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magasin.entity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author admin
 */
public class MagasinDBTest {
    
    @Test
    public void test(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        
        em.getTransaction().begin();
        Categorie cat = new Categorie();
        cat.setNom("Basket");
        em.persist(cat);
        
        Categorie cat2 = new Categorie();
        cat2.setNom("Lunette de soleil");
        em.persist(cat2);
        
        Produit prod = new Produit();
        prod.setTitre("Ray-ban");
        em.persist(prod);
        em.getTransaction().commit();
    }
    
}
