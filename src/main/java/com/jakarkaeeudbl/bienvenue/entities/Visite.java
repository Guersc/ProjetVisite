/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jakarkaeeudbl.bienvenue.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 *
 * @author HS
 */
@Entity
@Table(name = "visite")
public class Visite {

    @Id
    @Column(name = "idUtilisateur")
    private int idUtilisateur;

    @Id
    @Column(name = "idLieu")
    private int idLieu;

    @Id
    @Column(name = "dateVisite")
    private LocalDateTime dateVisite;

    @Column(name = "tempsPasse")
    private Integer tempsPasse;

    @Column(name = "observations", length = 255)
    private String observations;

    @Column(name = "depenses")
    private Double depenses;

    @ManyToOne
    @JoinColumn(name = "idUtilisateur", insertable = false, updatable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "idLieu", insertable = false, updatable = false)
    private Lieu lieu;

    // Getters et Setters

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(int idLieu) {
        this.idLieu = idLieu;
    }

    public LocalDateTime getDateVisite() {
        return dateVisite;
    }

    public void setDateVisite(LocalDateTime dateVisite) {
        this.dateVisite = dateVisite;
    }

    public Integer getTempsPasse() {
        return tempsPasse;
    }

    public void setTempsPasse(Integer tempsPasse) {
        this.tempsPasse = tempsPasse;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Double getDepenses() {
        return depenses;
    }

    public void setDepenses(Double depenses) {
        this.depenses = depenses;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }
}
