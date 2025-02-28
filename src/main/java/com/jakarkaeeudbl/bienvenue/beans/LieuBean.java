package com.jakarkaeeudbl.bienvenue.beans;

import com.jakarkaeeudbl.bienvenue.business.LieuEntrepriseBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;

import com.jakarkaeeudbl.bienvenue.entities.Lieu;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("lieuBean")
@SessionScoped
public class LieuBean implements Serializable {
    private int idLieu = 0; // Ajout d'un ID pour la modification
    private String nom;
    private String description;
    private double latitude;
    private double longitude;
    private List<Lieu> listeLieux = new ArrayList<>();    
    private String weatherMessage;
    private int selectedLieu;
    
    @Inject
    private LieuEntrepriseBean lieuEntrepriseBean;
    
    private Lieu lieu;
    
     // Getters et Setters
    public int getIdLieu() { return idLieu; }
    public void setIdLieu(int idLieu) { this.idLieu = idLieu; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public List<Lieu> getListeLieux() { return listeLieux; }

    public String getWeatherMessage() {
        return weatherMessage;
    }

    public void setWeatherMessage(String weatherMessage) {
        this.weatherMessage = weatherMessage;
    }
    
    public List<Lieu> getLieux() { return lieuEntrepriseBean.listerTousLesLieux(); }


    public int getSelectedLieu() {
        return selectedLieu;
    }

    public void setListeLieux(List<Lieu> listeLieux) {
        this.listeLieux = listeLieux;
    }

    public Lieu getLieu() {
        return lieu;
    }

    // Ajouter ou Modifier un lieu
    public void enregistrerLieu() {
        if (idLieu == 0) {
            // Ajout d'un nouveau lieu
            Lieu lieu = new Lieu(nom, description, latitude, longitude);
            listeLieux.add(lieu);
        } else {
            // Modification d'un lieu existant
            for (Lieu lieu : listeLieux) {
                if (lieu.getId() == idLieu) {
                    lieu.setNom(nom);
                    lieu.setDescription(description);
                    lieu.setLatitude(latitude);
                    lieu.setLongitude(longitude);
                    break;
                }
            }
        }
        resetForm();
    }

    // Supprimer un lieu
    public void supprimerLieu(int id) {
        listeLieux.removeIf(lieu -> lieu.getId() == id);
    }

    // Préparer la modification d'un lieu
    public void preparerModification(Lieu lieu) {
        this.idLieu = lieu.getId();
        this.nom = lieu.getNom();
        this.description = lieu.getDescription();
        this.latitude = lieu.getLatitude();
        this.longitude = lieu.getLongitude();
    }

    // Réinitialiser le formulaire
    private void resetForm() {
        this.idLieu = 0;
        this.nom = "";
        this.description = "";
        this.latitude = 0;
        this.longitude = 0;
    }

   
    public void ajouterLieu() {
        if (nom != null && !nom.isEmpty() && description != null && !description.isEmpty()) {
            lieuEntrepriseBean.ajouterLieuEntreprise(nom, description, latitude, longitude);
        }
    }
    
    public void supprimerLieu(Lieu lieu) {
        if (lieu != null) {
            lieuEntrepriseBean.supprimerLieu(getIdLieu());
        }
    }
    
    
    public void selectLieu(Lieu lieu) {
        this.lieu = lieu;
        this.nom = lieu.getNom();
        this.description = lieu.getDescription();
        this.latitude = lieu.getLatitude();
        this.longitude = lieu.getLongitude();
    }
    
    
    public void fetchWeatherMessage(Lieu l) {

        if (l != null) {
        // Appel au service web pour obtenir les données météorologiques

        String serviceURL = "http://192.168.180.203:8080/j-weather/webapi/JarkartaWeather?latitude="+ l.getLatitude() + "&longitude=" + l.getLongitude();

        Client client = ClientBuilder.newClient();
        String response = client.target(serviceURL)
        .request(MediaType.TEXT_PLAIN)
        .get(String.class);

        // Enregistrement du message météo dans la variable weatherMessage
        this.weatherMessage =response;
        }

    }

   public void updateWeatherMessage(AjaxBehaviorEvent event) {

    Lieu lieu=lieuEntrepriseBean.trouverLieuParId(selectedLieu);
    this.fetchWeatherMessage(lieu);
    }
    
}