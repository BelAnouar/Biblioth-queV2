package org.example.persistance;

import org.example.metier.TheseUniversitaire;
import org.example.metier.abstracts.Documents;
import org.example.persistance.interfaces.DocumentDAO;
import org.example.utils.DataUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class TheseUniversitaireDAOImp implements DocumentDAO<TheseUniversitaire> {

    private final Connection connection;

    public TheseUniversitaireDAOImp() throws SQLException {
        connection = DataUtils.getInstance().getConnection();
    }
    @Override
    public void createDocument(TheseUniversitaire theseUniversitaire) {
        try {
            String sql = "INSERT INTO these_universitaire (title, author, datePublication, nombrePage, access, isEprunter, universite, domaine) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, theseUniversitaire.getTitle());
                statement.setString(2, theseUniversitaire.getAuthor());
                statement.setObject(3, theseUniversitaire.getDatePublication());
                statement.setInt(4, theseUniversitaire.getNombreDePages());
                statement.setObject(5, theseUniversitaire.getAcces().name(), Types.OTHER);
                statement.setBoolean(6, theseUniversitaire.isEmprunt());

                statement.setString(7, theseUniversitaire.getUniversite());
                statement.setString(8, theseUniversitaire.getDomaine());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void updateDocument(TheseUniversitaire document) {

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
