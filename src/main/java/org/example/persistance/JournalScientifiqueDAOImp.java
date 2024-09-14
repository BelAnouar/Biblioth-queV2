package org.example.persistance;

import org.example.Enum.UtilisateurType;
import org.example.metier.JournalScientifique;
import org.example.metier.abstracts.Documents;
import org.example.persistance.interfaces.DocumentDAO;
import org.example.utils.DataUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JournalScientifiqueDAOImp implements DocumentDAO<JournalScientifique> {

    private final Connection connection;

    public JournalScientifiqueDAOImp() throws SQLException {
        connection = DataUtils.getInstance().getConnection();
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
    public Documents displayDocument(int documentId) {
        return null;
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

                documents.add(journalScientifique);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return documents;
    }



}
