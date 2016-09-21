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
public enum Statut {
    EN_COURS,
    TERMINE,
    PAYE,
    LIVRE
}
