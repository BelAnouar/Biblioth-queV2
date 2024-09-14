package org.example.service;

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



    public static void ajouter(Documents documents, DocumentType type) {
        switch (type) {
            case LIVRE -> livreDAOImp.createDocument((Livre) documents);
            case MAGAZINE -> magazineDAOImp.createDocument((Magasine) documents);
            case JOURNAL_SCIENTIFIQUE -> journalScientifiqueDAOImp.createDocument((JournalScientifique) documents);
            case THESE_UNIVERSITAIRE -> theseUniversitaireDAOImp.createDocument((TheseUniversitaire) documents);
        }

    }
    public static void update(Documents documents, DocumentType type) {
        switch (type) {
            case LIVRE -> livreDAOImp.updateDocument((Livre) documents);
            case MAGAZINE -> magazineDAOImp.updateDocument((Magasine) documents);
            case JOURNAL_SCIENTIFIQUE -> journalScientifiqueDAOImp.updateDocument((JournalScientifique) documents);
            case THESE_UNIVERSITAIRE -> theseUniversitaireDAOImp.updateDocument((TheseUniversitaire) documents);
        }

    }
    public static void delete(int id, DocumentType type) {
        switch (type) {
            case LIVRE -> livreDAOImp.deleteDocument(id);
            case MAGAZINE -> magazineDAOImp.deleteDocument(id);
            case JOURNAL_SCIENTIFIQUE -> journalScientifiqueDAOImp.deleteDocument(id);
            case THESE_UNIVERSITAIRE -> theseUniversitaireDAOImp.deleteDocument(id);
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
    public static void afficherTous(DocumentType type) {

        switch (type) {
            case LIVRE -> livreDAOImp.displayAllDocuments().stream()
                    .forEach(livre -> System.out.println(livre.afficherDetails()));
            case MAGAZINE -> magazineDAOImp.displayAllDocuments().stream().forEach(magasine ->System.out.println(magasine.afficherDetails()) );
            case JOURNAL_SCIENTIFIQUE -> journalScientifiqueDAOImp.displayAllDocuments().stream().forEach(journalScientifique -> System.out.println(journalScientifique.afficherDetails()));
            case THESE_UNIVERSITAIRE -> theseUniversitaireDAOImp.displayAllDocuments().stream().forEach(theseUniversitaire -> System.out.println(theseUniversitaire.afficherDetails()));
        }
    }

    public static void search(int id, DocumentType type) {

        switch (type) {
            case LIVRE -> livreDAOImp.displayDocument(id);
            case MAGAZINE -> magazineDAOImp.displayDocument(id);
            case JOURNAL_SCIENTIFIQUE -> journalScientifiqueDAOImp.displayDocument(id);
            case THESE_UNIVERSITAIRE -> theseUniversitaireDAOImp.displayDocument(id);
        }
    }
}