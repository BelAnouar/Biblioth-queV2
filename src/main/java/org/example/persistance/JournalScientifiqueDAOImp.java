package org.example.persistance;

import org.example.Enum.UtilisateurType;
import org.example.metier.JournalScientifique;

import org.example.metier.Magasine;
import org.example.persistance.interfaces.DocumentDAO;
import org.example.utils.DataUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JournalScientifiqueDAOImp implements DocumentDAO<JournalScientifique> {

    private final Connection connection;
    private final Map<Integer, JournalScientifique> journalScientifiqueMap = new HashMap<>();
    private static boolean isInitialized = false;
    public JournalScientifiqueDAOImp() throws SQLException {
        connection = DataUtils.getInstance().getConnection();
        if (!isInitialized) {
            displayAllDocuments();
            isInitialized = true;
        }
    }

    @Override
    public void createDocument(JournalScientifique journalScientifique) {
        try {
            String sql = "INSERT INTO journal_scientifique (title, author, datePublication, nombrePage, access, isEprunter, domaine_recherche) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, journalScientifique.getTitle());
                statement.setString(2, journalScientifique.getAuthor());
                statement.setObject(3, journalScientifique.getDatePublication());
                statement.setInt(4, journalScientifique.getNombreDePages());
                statement.setObject(5, journalScientifique.getAcces().name(), Types.OTHER);
                statement.setBoolean(6, journalScientifique.isEmprunt());
                statement.setString(7, journalScientifique.getDomaineRecherche());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDocument(JournalScientifique journalScientifique) {
        try {
            String sql = "UPDATE journal_scientifique SET title = ?, author = ?, datePublication = ?, nombrePage = ?, access = ?::typeUser, isEprunter = ?, domaine_recherche = ? " +
                    "WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, journalScientifique.getTitle());
                statement.setString(2, journalScientifique.getAuthor());
                statement.setObject(3, journalScientifique.getDatePublication());
                statement.setInt(4, journalScientifique.getNombreDePages());
                statement.setObject(5, journalScientifique.getAcces().name(), Types.OTHER);
                statement.setBoolean(6, journalScientifique.isEmprunt());
                statement.setString(7, journalScientifique.getDomaineRecherche());
                statement.setInt(8, journalScientifique.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDocument(int documentId) {
        try {
            String sql = "DELETE FROM journal_scientifique WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, documentId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JournalScientifique displayDocument(int documentId) {
        try {

            JournalScientifique journalScientifique = journalScientifiqueMap.get(documentId);
            return journalScientifique;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<JournalScientifique> displayAllDocuments() {
        List<JournalScientifique> documents = new ArrayList<>();
        String sql = "SELECT id, title, author, datePublication, nombrePage, access, isEprunter, domaine_recherche FROM journal_scientifique";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                JournalScientifique journalScientifique = new JournalScientifique();
                journalScientifique.setId(resultSet.getInt("id"));
                journalScientifique.setTitle(resultSet.getString("title"));
                journalScientifique.setAuthor(resultSet.getString("author"));
                journalScientifique.setDatePublication(resultSet.getObject("datePublication", LocalDate.class));
                journalScientifique.setNombreDePages(resultSet.getInt("nombrePage"));
                journalScientifique.setAcces(UtilisateurType.valueOf(resultSet.getString("access"))); // Adjust if using an enum
                journalScientifique.setEmprunt(resultSet.getBoolean("isEprunter"));
                journalScientifique.setDomaineRecherche(resultSet.getString("domaine_recherche"));


                if (!isInitialized) {
                    journalScientifiqueMap.put(journalScientifique.getId(), journalScientifique);
                }else {
                    documents.add(journalScientifique);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return documents;
    }

    public void empruntDocument(int id) {
        String sql = "UPDATE journal_scientifique SET isEprunter = true WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book borrowed successfully.");
            } else {
                System.out.println("No book found with the given ISBN or the book is already borrowed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void annulerEmpruntDocument(int id) {
        String sql = "UPDATE journal_scientifique SET isEprunter = false WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book return processed successfully.");
            } else {
                System.out.println("No book found with the given ISBN or the book is not borrowed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}

