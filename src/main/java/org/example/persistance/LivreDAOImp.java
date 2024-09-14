package org.example.persistance;

import org.example.Enum.UtilisateurType;
import org.example.metier.Livre;
import org.example.metier.abstracts.Documents;
import org.example.persistance.interfaces.DocumentDAO;
import org.example.utils.DataUtils;

import java.sql.*;
import java.util.ArrayList;
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
        String sql = "UPDATE Livre SET title = ?, author = ?, datePublication = ?, nombrePage = ?, access = ?::typeUser, isbn = ? WHERE id = ? ";

        System.out.println(document.getId());
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, document.getTitle());
            statement.setString(2, document.getAuthor());
            statement.setDate(3, java.sql.Date.valueOf(document.getDatePublication()));
            statement.setInt(4, document.getNombreDePages());
            statement.setString(5, document.getAcces().name());
            statement.setInt(6, document.getIsbn());
            statement.setInt(7, document.getId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Document updated successfully.");
            } else {
                System.out.println("Document not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDocument(int documentId) {
        String sql = "DELETE FROM Livre WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, documentId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Document deleted successfully.");
            } else {
                System.out.println("Document not found.");
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
    public List<Livre> displayAllDocuments() {
        List<Livre> livres = new ArrayList<>();

        try {
            String sql = "SELECT title, author, datePublication, nombrePage, access, isEprunter, isbn FROM Livre";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Livre livre = new Livre();
                livre.setTitle(resultSet.getString("title"));
                livre.setAuthor(resultSet.getString("author"));
                livre.setDatePublication(resultSet.getDate("datePublication").toLocalDate()); // Assuming you are using LocalDate
                livre.setNombreDePages(resultSet.getInt("nombrePage"));
                livre.setAcces(UtilisateurType.valueOf(resultSet.getString("access"))); // Assuming Acces is an enum
                livre.setEmprunt(resultSet.getBoolean("isEprunter"));
                livre.setIsbn(resultSet.getInt("isbn"));

                livres.add(livre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
         System.out.println(livres);
        return livres;
    }


}

