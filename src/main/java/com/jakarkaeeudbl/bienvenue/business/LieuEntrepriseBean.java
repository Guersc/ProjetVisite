package com.jakarkaeeudbl.bienvenue.business;

import com.jakarkaeeudbl.bienvenue.entities.Lieu;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Stateless
public class LieuEntrepriseBean {

    @PersistenceContext
    private EntityManager em;

    // Méthode pour ajouter un lieu
    @Transactional
    public void ajouterLieuEntreprise(String nom, String description, double latitude, double longitude) {
        // L'ID n'est pas précisé ici, il sera généré automatiquement par la base de données
        Lieu lieu = new Lieu(nom, description, latitude, longitude);
        em.persist(lieu);  // Persister le lieu dans la base de données
    }

    // Méthode pour modifier un lieu
    @Transactional
    public void modifierLieuEntreprise(int id, String nom, String description, double latitude, double longitude) {
        Lieu lieu = em.find(Lieu.class, id);  // Trouver le lieu par son ID
        if (lieu != null) {
            // Mettre à jour les propriétés du lieu
            lieu.setNom(nom);
            lieu.setDescription(description);
            lieu.setLatitude(latitude);
            lieu.setLongitude(longitude);
            em.merge(lieu);  // Appliquer les modifications dans la base de données
        }
    }

    // Méthode pour lister tous les lieux
    public List<Lieu> listerTousLesLieux() {
        return em.createQuery("SELECT L FROM Lieu L", Lieu.class).getResultList();  // Récupérer tous les lieux
    }

    // Méthode pour supprimer un lieu
    @Transactional
    public void supprimerLieu(int id) {
        Lieu lieu = em.find(Lieu.class, id);  // Trouver le lieu par son ID
        if (lieu != null) {
            em.remove(lieu);  // Supprimer le lieu de la base de données
        }
    }

    // Méthode pour trouver un lieu par son ID
    public Lieu trouverLieuParId(int id) {
        return em.find(Lieu.class, id);  // Trouver le lieu par son ID
    }
}
