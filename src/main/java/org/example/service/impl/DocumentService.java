package org.example.service.impl;

import org.example.Enum.DocumentType;
import org.example.metier.JournalScientifique;
import org.example.metier.Livre;
import org.example.metier.Magasine;
import org.example.metier.TheseUniversitaire;
import org.example.metier.abstracts.Documents;
import org.example.persistance.JournalScientifiqueDAOImp;
import org.example.persistance.LivreDAOImp;
import org.example.persistance.MagazineDAOImp;
import org.example.persistance.TheseUniversitaireDAOImp;

import java.sql.SQLException;

public class DocumentService {
    private static LivreDAOImp livreDAOImp;
    private static MagazineDAOImp magazineDAOImp;
    private static JournalScientifiqueDAOImp journalScientifiqueDAOImp;
    private  static TheseUniversitaireDAOImp theseUniversitaireDAOImp;



    static {
        try {
            livreDAOImp = new LivreDAOImp();
            magazineDAOImp = new MagazineDAOImp();
            journalScientifiqueDAOImp = new JournalScientifiqueDAOImp();
            theseUniversitaireDAOImp= new TheseUniversitaireDAOImp();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    ;

    public static void ajouter(Documents documents, DocumentType type) {
        switch (type) {
            case LIVRE -> livreDAOImp.createDocument((Livre) documents);
            case MAGAZINE -> magazineDAOImp.createDocument((Magasine) documents);
            case JOURNAL_SCIENTIFIQUE -> journalScientifiqueDAOImp.createDocument((JournalScientifique) documents);
            case THESE_UNIVERSITAIRE -> theseUniversitaireDAOImp.createDocument((TheseUniversitaire) documents);
        }

    }

//    public static void emprunter(String id, DocumentType type) {
//
//        switch (type) {
//            case LIVRE -> livreDAOImp.emprunter(id);
//            case MAGAZINE -> MagasineImp.emprunter(id);
//            case JOURNAL_SCIENTIFIQUE -> JournalScientifiqueImp.emprunter(id);
//            case THESE_UNIVERSITAIRE -> TheseUniversitaireImp.emprunter(id);
//        }
//    }
//
//    public static void retourner(String id, DocumentType type) {
//
//        switch (type) {
//            case LIVRE -> LivreImp.retourner(id);
//            case MAGAZINE -> MagasineImp.retourner(id);
//            case JOURNAL_SCIENTIFIQUE -> JournalScientifiqueImp.retourner(id);
//            case THESE_UNIVERSITAIRE -> TheseUniversitaireImp.retourner(id);
//        }
//    }
//
//    public static void afficherTous(DocumentType type) {
//
//        switch (type) {
//            case LIVRE -> LivreImp.afficherTous();
//            case MAGAZINE -> MagasineImp.afficherTous();
//            case JOURNAL_SCIENTIFIQUE -> JournalScientifiqueImp.afficherTous();
//            case THESE_UNIVERSITAIRE -> TheseUniversitaireImp.afficherTous();
//        }
//    }
//
//    public static void rechercher(String id, DocumentType type) {
//
//        switch (type) {
//            case LIVRE -> LivreImp.rechercher(id);
//            case MAGAZINE -> MagasineImp.rechercher(id);
//            case JOURNAL_SCIENTIFIQUE -> JournalScientifiqueImp.rechercher(id);
//            case THESE_UNIVERSITAIRE -> TheseUniversitaireImp.rechercher(id);
//        }
//    }
}