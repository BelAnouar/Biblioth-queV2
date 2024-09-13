package org.example.persistance;

import org.example.metier.JournalScientifique;
import org.example.metier.abstracts.Documents;
import org.example.persistance.interfaces.DocumentDAO;
import org.example.utils.DataUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
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

    }

    @Override
    public void deleteDocument(int documentId) {

    }

    @Override
    public Documents displayDocument(int documentId) {
        return null;
    }

    @Override
    public List<Documents> displayAllDocuments() {
        return List.of();
    }

    @Override
    public List<Documents> searchDocument(int id) {
        return List.of();
    }
}
