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

        Client cli = new Client();
        cli.setId(1L);
        cli.setLogin("Riri");
        em.persist(cli);

        Client cli2 = new Client();
        cli2.setLogin("Fifi");
        cli2.setId(2L);
        em.persist(cli2);

        Client cli3 = new Client();
        cli3.setLogin("Loulou");
        cli3.setId(3L);
        em.persist(cli3);

        Commande com1 = new Commande();
        com1.setClient(cli);
        com1.setId(1L);
        com1.setPrixTotal(1000);
        em.persist(com1);

        Commande com2 = new Commande();
        com2.setClient(cli3);
        com2.setId(2L);
        com2.setPrixTotal(5);
        em.persist(com2);

        Commande com3 = new Commande();
        com3.setClient(cli3);
        com3.setId(3L);
        com3.setPrixTotal(2);
        em.persist(com3);

        em.getTransaction().commit();
    }

    @Test
    public void testProdBasket() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();

        Produit p = em.find(Produit.class, 1L);
        Assert.assertEquals("Le produit n'est pas Ray-ban mais" + p.getTitre(), "Ray-ban", p.getTitre());
    }

    @Test
    public void nBcommandeLoulou() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();

        Client cl = em.find(Client.class, 3L);
        Assert.assertEquals("Mauvais nombre de client, nombre trouvé " + cl.getCommandes().size(), 2, cl.getCommandes().size());
    }
    
    @Test
    public void commandeTroisPasseParLoulou(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        Commande com = em.find(Commande.class, 3L);
        Assert.assertEquals("Le client de la commande 3 n'est pas loulou mais par " + com.getClient().getLogin(), "Loulou", com.getClient().getLogin());
    }
    
    @Test
    public void commande2PasseParRiri() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        Commande com = em.find(Commande.class, 2L);
        Assert.assertNotEquals("Le client de la commande 2 n'est pas riri, mais pas "+ com.getClient().getLogin(),  "Riri", com.getClient().getLogin());
    }
}
