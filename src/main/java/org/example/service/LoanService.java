package org.example.service;

import org.example.Enum.DocumentType;
import org.example.persistance.*;
import org.example.persistance.interfaces.DocumentDAO;

import java.sql.SQLException;
import java.util.EnumMap;
import java.util.Map;

public class LoanService {
    private final Map<DocumentType, DocumentDAO<?>> documentDAOs;
    private final ReservationDAO reservationDAO=new ReservationDAO();
    public LoanService() throws SQLException {
        documentDAOs = new EnumMap<>(DocumentType.class);
        try {

            documentDAOs.put(DocumentType.LIVRE, new LivreDAOImp());
            documentDAOs.put(DocumentType.MAGAZINE, new MagazineDAOImp());
            documentDAOs.put(DocumentType.JOURNAL_SCIENTIFIQUE, new JournalScientifiqueDAOImp());
            documentDAOs.put(DocumentType.THESE_UNIVERSITAIRE, new TheseUniversitaireDAOImp());

        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize ReservationService", e);
        }

    }
    public  void emprunter(int id,DocumentType type) {
        DocumentDAO<?> dao = documentDAOs.get(type);
         dao.empruntDocument(id);
    }

    public  void retourner(int id,DocumentType type) {
        DocumentDAO<?> dao = documentDAOs.get(type);
        dao.annulerEmpruntDocument(id);
    }

}
