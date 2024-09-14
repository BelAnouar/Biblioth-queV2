package org.example.persistance;

import org.example.Enum.UtilisateurType;
import org.example.metier.Magasine;
import org.example.metier.abstracts.Documents;
import org.example.persistance.interfaces.DocumentDAO;
import org.example.utils.DataUtils;

import java.sql.*;
import java.util.ArrayList;
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
        String sql = "UPDATE magazine SET title = ?, author = ?, datePublication = ?, nombrePage = ?, access = ?, numero = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, document.getTitle());
            statement.setString(2, document.getAuthor());
            statement.setObject(3, document.getDatePublication());
            statement.setInt(4, document.getNombreDePages());
            statement.setObject(5, document.getAcces().name(), Types.OTHER);
            statement.setInt(6, document.getNumero());
            statement.setInt(7, document.getId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Magazine updated successfully.");
            } else {
                System.out.println("Magazine not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteDocument(int documentId) {
        String sql = "DELETE FROM magazine WHERE numero = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, documentId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Magazine deleted successfully.");
            } else {
                System.out.println("Magazine not found.");
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
    public List<Magasine> displayAllDocuments() {
        List<Magasine> magazines = new ArrayList<>();

        String sql = "SELECT title, author, datePublication, nombrePage, access, isEprunter, numero FROM magazine";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Magasine magazine = new Magasine();
                magazine.setTitle(resultSet.getString("title"));
                magazine.setAuthor(resultSet.getString("author"));
                magazine.setDatePublication(resultSet.getDate("datePublication").toLocalDate());
                magazine.setNombreDePages(resultSet.getInt("nombrePage"));
                magazine.setAcces(UtilisateurType.valueOf(resultSet.getString("access")));
                magazine.setEmprunt(resultSet.getBoolean("isEprunter"));
                magazine.setNumero(resultSet.getInt("numero"));

                magazines.add(magazine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return magazines;
    }


}
