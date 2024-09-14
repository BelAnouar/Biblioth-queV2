package org.example.persistance;

import org.example.Enum.UtilisateurType;
import org.example.metier.Livre;
import org.example.metier.Magasine;
import org.example.persistance.interfaces.DocumentDAO;
import org.example.utils.DataUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LivreDAOImp implements DocumentDAO<Livre> {
    private final Connection connection;
    private static final Map<Integer, Livre> livreMap = new HashMap<>();
    private static boolean isInitialized = false;
    public LivreDAOImp() throws SQLException {
        connection = DataUtils.getInstance().getConnection();
        if (!isInitialized) {
            displayAllDocuments();
            isInitialized = true;
        }
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
    public Livre displayDocument(int documentId) {
        try {
            System.out.println(livreMap.size());
            Livre livre = livreMap.get(documentId);
            return livre;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Livre> displayAllDocuments() {
        List<Livre> livres = new ArrayList<>();

        try {
            String sql = "SELECT id,title, author, datePublication, nombrePage, access, isEprunter, isbn FROM Livre";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Livre livre = new Livre();
                livre.setId(resultSet.getInt("id"));
                livre.setTitle(resultSet.getString("title"));
                livre.setAuthor(resultSet.getString("author"));
                livre.setDatePublication(resultSet.getDate("datePublication").toLocalDate());
                livre.setNombreDePages(resultSet.getInt("nombrePage"));
                livre.setAcces(UtilisateurType.valueOf(resultSet.getString("access")));
                livre.setEmprunt(resultSet.getBoolean("isEprunter"));
                livre.setIsbn(resultSet.getInt("isbn"));


                if (!isInitialized) {
                    livreMap.put(livre.getId(), livre);
                }else {
                    livres.add(livre);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
         System.out.println(livres);
        return livres;
    }


    public void empruntDocument(int id) {
        String sql = "UPDATE Livre SET isEprunter = true WHERE id = ?";

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
        String sql = "UPDATE Livre SET isEprunter = false WHERE id = ?";

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

