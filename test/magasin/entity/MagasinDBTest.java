/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magasin.entity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author admin
 */
public class MagasinDBTest {

    @BeforeClass
    public static void affectation() {
        //vide toutes les tables
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();

        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Produit p");
        query.executeUpdate();
        
        em.createQuery("DELETE FROM Categorie c").executeUpdate();
        em.createQuery("DELETE FROM Commande c").executeUpdate();
        em.createQuery("DELETE FROM Client c").executeUpdate();
        
        
        em.getTransaction().commit();
        
        //ajoute des données
        em.getTransaction().begin();
        Categorie cat = new Categorie();
        cat.setNom("Basket");
        cat.setId(1L);
        em.persist(cat);

        Categorie cat2 = new Categorie();
        cat2.setNom("Lunette de soleil");
        cat2.setId(2L);
        em.persist(cat2);

        Produit prod = new Produit();
        prod.setTitre("Ray-ban");
        prod.setCategorie(cat2);
        prod.setId(1L);
        em.persist(prod);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        Client cli = new Client();
        cli.setId(1L);
        cli.setLogin("Michel");
        em.persist(cli);

        Client cli2 = new Client();
        cli2.setLogin("Louis");
        cli2.setId(2L);
        em.persist(cli2);

        Commande com = new Commande();
        com.setClient(cli2);
        com.setId(1L);
        em.persist(com);
        em.getTransaction().commit();
    }

    @Test
    public void testListeProdCategorie() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();

        Categorie cat = em.find(Categorie.class, 1L);
        for (Produit p : cat.getProduits()) {
            if(!p.getTitre().equals("Basket")){
                Assert.fail("l'élèment Basket n'est pas trouvé!");
            }
        }
    }

    @Test
    public void testListeClientCommande() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();

        Client cl = em.find(Client.class, 1L);
        for (Commande c : cl.getCommandes()) {
            if (!c.getClient().getLogin().equals("Michel")){
                Assert.fail("On a pas le client Michel pour la commande");
            }
        }
    }
}
