package com.jakarkaeeudbl.bienvenue.business;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter("/pages/*")
public class SessionControlFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialisation du filtre (si nécessaire)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Vérifie si l'utilisateur est connecté via la session
        String email = (String) httpRequest.getSession().getAttribute("user");

        if (email == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.xhtml"); // Redirection vers la page de connexion
        } else {
            chain.doFilter(request, response); // Laisser la requête continuer
        }
    }

    @Override
    public void destroy() {
        // Nettoyage du filtre (si nécessaire)
    }
}
