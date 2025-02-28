package com.jakarkaeeudbl.bienvenue.beans;  // Vérifie que le package correspond bien

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import java.io.IOException;

@Named("navigationController")  // Equivalent de @ManagedBean
@RequestScoped  // Scope de la requête
public class NavigationController {

    public String ajouter() {
        return "pages/lieu";  // Assure-toi que lieu.xhtml existe bien
    }

    public String visiter() {
        return "pages/guide";
    }

    public String voirApropos() {
        return "pages/a_propos";
    }
   
}
