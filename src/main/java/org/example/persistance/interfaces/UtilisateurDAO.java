package org.example.persistance.interfaces;

import org.example.metier.abstracts.Utilisateur;

import java.util.List;

public interface UtilisateurDAO<T extends Utilisateur> {

    void createUtilisateur(T utilisateur);

    T displayUtilisateur(int id);

    List<T> displayAllUtilisateur();

    void updateUtilisateur(T utilisateur);

    void deleteUtilisateur(int id);
}
