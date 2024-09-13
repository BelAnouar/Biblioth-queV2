package org.example.persistance;

import org.example.metier.Magasine;
import org.example.metier.abstracts.Documents;
import org.example.persistance.interfaces.DocumentDAO;
import org.example.utils.DataUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class MagazineDAOImp implements DocumentDAO<Magasine> {

    private final Connection connection;

    public MagazineDAOImp() throws SQLException {
        connection = DataUtils.getInstance().getConnection();
    }
    @Override
    public void createDocument(Magasine magazine) {
        try {
            String sql = "INSERT INTO magazine ( title, author, datePublication, nombrePage, access, isEprunter,numero) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, magazine.getTitle());
                statement.setString(2, magazine.getAuthor());
                statement.setObject(3, magazine.getDatePublication());
                statement.setInt(4, magazine.getNombreDePages());
                statement.setObject(5, magazine.getAcces().name(), Types.OTHER);
                statement.setBoolean(6, magazine.isEmprunt());
                statement.setInt(7, magazine.getNumero());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void updateDocument(Magasine document) {

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
