/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magasin.entity;

import javax.persistence.Embeddable;

/**
 *
 * @author admin
 */
@Embeddable
public class Adresse {
    private Integer num;
    private String rue;
    private Integer codePostal;
    private String ville;
    private String pays;
}
