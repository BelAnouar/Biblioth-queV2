package org.example.persistance;

import org.example.Enum.UtilisateurType;
import org.example.metier.Magasine;
import org.example.metier.TheseUniversitaire;
import org.example.persistance.interfaces.DocumentDAO;
import org.example.utils.DataUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheseUniversitaireDAOImp implements DocumentDAO<TheseUniversitaire> {

    private final Connection connection;
    private final Map<Integer, TheseUniversitaire> theseUniversitaireMap = new HashMap<>();
    private static boolean isInitialized = false;
    public TheseUniversitaireDAOImp() throws SQLException {
        connection = DataUtils.getInstance().getConnection();
        if (!isInitialized) {
            displayAllDocuments();
            isInitialized = true;
        }
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
        String sql = "UPDATE these_universitaire SET author = ?, datePublication = ?, nombrePage = ?, access = ?::typeuser, title = ?, universite = ?, domaine = ? WHERE id = ?";
        System.out.println(document.getAcces().name());
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, document.getAuthor());
            statement.setObject(2, document.getDatePublication());
            statement.setInt(3, document.getNombreDePages());
            statement.setObject(4, document.getAcces().name(), java.sql.Types.OTHER);
            statement.setString(5, document.getTitle());
            statement.setString(6, document.getUniversite());
            statement.setString(7, document.getDomaine());
            statement.setInt(8, document.getId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Thèse universitaire mise à jour avec succès.");
            } else {
                System.out.println("Thèse universitaire non trouvée.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteDocument(int documentId) {
        String sql = "DELETE FROM these_universitaire WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, documentId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Thèse universitaire supprimée avec succès.");
            } else {
                System.out.println("Thèse universitaire non trouvée.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public TheseUniversitaire displayDocument(int documentId) {
        try {

            TheseUniversitaire theseUniversitaire = theseUniversitaireMap.get(documentId);
            return theseUniversitaire;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<TheseUniversitaire> displayAllDocuments() {
        List<TheseUniversitaire> theses = new ArrayList<>();

        String sql = "SELECT id,title, author, datePublication, nombrePage, access, isEprunter, universite, domaine FROM these_universitaire";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                TheseUniversitaire theseUniversitaire = new TheseUniversitaire();
                theseUniversitaire.setId(resultSet.getInt("id"));
                theseUniversitaire.setTitle(resultSet.getString("title"));
                theseUniversitaire.setAuthor(resultSet.getString("author"));
                theseUniversitaire.setDatePublication(resultSet.getDate("datePublication").toLocalDate());
                theseUniversitaire.setNombreDePages(resultSet.getInt("nombrePage"));
                theseUniversitaire.setAcces(UtilisateurType.valueOf(resultSet.getString("access"))); // Assuming `access` is an enum
                theseUniversitaire.setEmprunt(resultSet.getBoolean("isEprunter"));
                theseUniversitaire.setUniversite(resultSet.getString("universite"));
                theseUniversitaire.setDomaine(resultSet.getString("domaine"));


                if (!isInitialized) {
                    theseUniversitaireMap.put(theseUniversitaire.getId(), theseUniversitaire);
                }else {
                    theses.add(theseUniversitaire);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return theses;
    }

    public void empruntDocument(int id) {
        String sql = "UPDATE these_universitaire SET isEprunter = true WHERE id = ?";

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
        String sql = "UPDATE these_universitaire SET isEprunter = false WHERE id = ?";

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


