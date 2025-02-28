package com.jakarkaeeudbl.bienvenue.business;


import com.jakarkaeeudbl.bienvenue.entities.Visite;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Stateless
@LocalBean
public class VisiteEntrepriseBean {
    
    @PersistenceContext
    private EntityManager entityManager;

    // Méthode pour ajouter une nouvelle visite
    public void ajouterVisite(Visite visite) {
        try {
            System.out.println("Ajout de la visite : " + visite);
            entityManager.persist(visite);
            System.out.println("Visite ajoutée avec succès");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'ajout de la visite : " + e.getMessage());
            e.printStackTrace();
            throw e;  // Propager l'exception pour que l'appelant puisse gérer l'erreur
        }
    }

    // Méthode pour obtenir toutes les visites
    public List<Visite> obtenirVisites() {
        return entityManager.createQuery("SELECT v FROM Visite v", Visite.class).getResultList();
    }

    // Méthode pour obtenir les visites par utilisateur
    public List<Visite> getVisitesParUtilisateur(Long idUtilisateur) {
        TypedQuery<Visite> query = entityManager.createQuery(
            "SELECT v FROM Visite v WHERE v.utilisateur.id = :idUtilisateur", Visite.class);
        query.setParameter("idUtilisateur", idUtilisateur);
        return query.getResultList();
    }
}
