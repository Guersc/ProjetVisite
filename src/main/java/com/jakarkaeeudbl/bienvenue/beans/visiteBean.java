/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jakarkaeeudbl.bienvenue.beans;

import com.jakarkaeeudbl.bienvenue.business.SessionManager;
import com.jakarkaeeudbl.bienvenue.business.UtilisateurEntrepriseBean;
import com.jakarkaeeudbl.bienvenue.business.VisiteEntrepriseBean;
import com.jakarkaeeudbl.bienvenue.entities.Utilisateur;
import com.jakarkaeeudbl.bienvenue.entities.Visite;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author HS
 */


@SessionScoped
@Named(value = "formulaireVisiteBean")
public class visiteBean implements Serializable {
    @EJB
    private VisiteEntrepriseBean visiteEntrepriseBean; // Injection de l'EJB pour gérer les visites
    @Inject
    private UtilisateurEntrepriseBean utilisateurEntrepriseBean;
     @Inject
     private SessionManager sessionManager;
        
    private Long idUtilisateur;
    private Long idLieu;
    private LocalDateTime dateVisite;
    private int tempsPasse;
    private String observations;
    private double depenses;
    private boolean afficherFormulaireVisite;

    @PostConstruct
    public void init() {
        dateVisite = LocalDateTime.now(); // Initialisation avec la date actuelle
    }

    
    public void afficherFormulaireVisite() {
        afficherFormulaireVisite = true;
    }

public void sauvegarderVisite() {
    try {
        Visite visite = new Visite();
        visite.setIdUtilisateur(idUtilisateur.intValue());// Conversion explicite
        visite.setIdLieu(1); // Conversion explicite
        visite.setDateVisite(dateVisite);
        visite.setTempsPasse(tempsPasse);
        visite.setObservations(observations);
        visite.setDepenses(depenses);

        // Ajout dans la base de données
        visiteEntrepriseBean.ajouterVisite(visite); 

        // Message de succès
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Visite enregistrée avec succès", null));

        // Réinitialisation du formulaire
        afficherFormulaireVisite = false;
        resetForm();
    } catch (Exception e) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de l'enregistrement de la visite : " + e.getMessage(), null));
        e.printStackTrace();  // Imprimer les détails de l'exception pour plus de détails
    }
}


    private void resetForm() {
        idLieu = null;
        dateVisite = LocalDateTime.now();
        tempsPasse = 0;
        observations = "";
        depenses = 0.0;
    }

    // Getters et Setters
    public LocalDateTime getDateVisite() { return dateVisite; }
    public void setDateVisite(LocalDateTime dateVisite) { this.dateVisite = dateVisite; }
    public double getDepenses() { return depenses; }
    public void setDepenses(double depenses) { this.depenses = depenses; }
    public Long getIdLieu() { return idLieu; }
    public void setIdLieu(Long idLieu) { this.idLieu = idLieu; }
    public int getTempsPasse() { return tempsPasse; }
    public void setTempsPasse(int tempsPasse) { this.tempsPasse = tempsPasse; }
    public Long getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(Long idUtilisateur) { this.idUtilisateur = idUtilisateur; }
    public void setAfficherFormulaireVisite(boolean afficherFormulaireVisite) { this.afficherFormulaireVisite = afficherFormulaireVisite; }
    public void setObservations(String observations) { this.observations = observations; }
    public String getObservations() { return observations; }

    public boolean isAfficherFormulaireVisite() {
        return afficherFormulaireVisite;
    }
    
    

    public VisiteEntrepriseBean getVisiteEntrepriseBean() {
        return visiteEntrepriseBean;
    }

    public void setVisiteEntrepriseBean(VisiteEntrepriseBean visiteEntrepriseBean) {
        this.visiteEntrepriseBean = visiteEntrepriseBean;
    }
    
    private List<Visite> visitesUtilisateur;
    //private Long idUtilisateur = 1L; // ID de l'utilisateur connecté (à remplacer dynamiquement)
    
    
    // Méthode pour charger les visites de l'utilisateur
    public void chargerVisites() {
        String mail = sessionManager.getValueFromSession("user");
        Utilisateur ut = utilisateurEntrepriseBean.trouverUtilisateurParEmail(mail);
        idUtilisateur = ut.getId();
        visitesUtilisateur = visiteEntrepriseBean.getVisitesParUtilisateur(idUtilisateur);

    }

    // Getter pour les visites  
    public List<Visite> getVisitesUtilisateur() {
        return visitesUtilisateur;
    }


    
} 