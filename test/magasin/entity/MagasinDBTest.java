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
    
//    @Test
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
        prod.setCategorie(cat2);
        em.persist(prod);
        em.getTransaction().commit();
    }
    //@Test
    public void testListeProdCategorie(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        Categorie cat = em.find(Categorie.class, 2L);
        for (Produit p : cat.getProduits()) {
            System.out.println(p);
        }
    }
    
    //@Test
    public void testCommandeSet(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        em.getTransaction().begin();
        Client cli = new Client();
        cli.setLogin("Michel");
        em.persist(cli);
        
        Client cli2 = new Client();
        cli2.setLogin("Louis");
        em.persist(cli2);
        
        Commande com = new Commande();
        com.setClient(cli2);
        em.persist(com);
        em.getTransaction().commit();
        
        
          
    }
    
    @Test
    public void testListeClientCommande(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        Client cl = em.find(Client.class, 2L);
        for (Commande c : cl.getCommandes()) {
            System.out.println(c);
        }
    }
}
