package org.example.persistance;

import org.example.metier.Livre;
import org.example.metier.abstracts.Documents;
import org.example.persistance.interfaces.DocumentDAO;
import org.example.utils.DataUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class LivreDAOImp implements DocumentDAO<Livre> {
    private final Connection connection;

    public LivreDAOImp() throws SQLException {
        connection = DataUtils.getInstance().getConnection();
    }

    @Override
    public void createDocument(Livre livre) {
        try {

            String sql = "INSERT INTO Livre ( title, author, datePublication, nombrePage, access, isEprunter,isbn) "
                    + "VALUES (?,?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, livre.getTitle());
            statement.setString(2, livre.getAuthor());
            statement.setObject(3, livre.getDatePublication());
            statement.setInt(4, livre.getNombreDePages());
            statement.setObject(5, livre.getAcces().name(), Types.OTHER);
            statement.setBoolean(6, livre.isEmprunt());
            statement.setInt(7, livre.getIsbn());


            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void updateDocument(Livre document) {

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

