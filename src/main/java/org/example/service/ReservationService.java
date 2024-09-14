package org.example.service;

import org.example.Enum.DocumentType;
import org.example.metier.JournalScientifique;
import org.example.metier.Livre;
import org.example.metier.Magasine;
import org.example.metier.TheseUniversitaire;
import org.example.persistance.*;
import org.example.persistance.interfaces.DocumentDAO;

import java.sql.SQLException;
import java.util.EnumMap;
import java.util.Map;


public class ReservationService {
    private final Map<DocumentType, DocumentDAO<?>> documentDAOs;
   private final ReservationDAO reservationDAO=new ReservationDAO();
    public ReservationService() throws SQLException {
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

    public  boolean reserverDocument(int id,int userId, DocumentType documentType) {
        DocumentDAO<?> dao = documentDAOs.get(documentType);
        Object document= dao.displayDocument(id);
        if (document instanceof Livre) {
            Livre livre = (Livre) document;
            if(!livre.isEmprunt()){
                reservationDAO.reserveDocument(userId,id);
                return true;
            }
        }
        if (document instanceof Magasine) {
            Magasine magasine = (Magasine) document;
            if(!magasine.isEmprunt()){
                reservationDAO.reserveDocument(userId,id);
                return true;
            }
        }
        if (document instanceof JournalScientifique) {
            JournalScientifique journalScientifique = (JournalScientifique) document;
            if(!journalScientifique.isEmprunt()){
                reservationDAO.reserveDocument(userId,id);
                return true;
            }
        }
        if (document instanceof TheseUniversitaire) {
            TheseUniversitaire theseUniversitaire = (TheseUniversitaire) document;
            if(!theseUniversitaire.isEmprunt()){
                reservationDAO.reserveDocument(userId,id);
                return true;
            }
        }
        return false;
    }

    public void annulerReservation(int reservationId) {

        boolean isReserved = reservationDAO.getReservationStatus(reservationId);

        if (isReserved) {

            boolean isCancelled = reservationDAO.annulerReservation(reservationId);
            if (isCancelled) {
                System.out.println("Reservation cancelled successfully.");
            } else {
                System.out.println("Failed to cancel the reservation.");
            }
        } else {

            System.out.println("The reservation is already reserved or does not exist.");
        }
    }


}
