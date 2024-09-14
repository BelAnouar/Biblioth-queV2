package org.example.persistance.users;

import org.example.metier.Etudiant;
import org.example.metier.abstracts.Utilisateur;
import org.example.persistance.interfaces.UtilisateurDAO;
import org.example.utils.DataUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDAO implements UtilisateurDAO<Etudiant> {

    private final Connection connection;
    public EtudiantDAO() throws SQLException {
        connection = DataUtils.getInstance().getConnection();

    }

    public void createUtilisateur(Etudiant etudiant) {
        String sql = "INSERT INTO etudiant (name, email, numero) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, etudiant.getNom());
            statement.setString(2, etudiant.getEmail());
            statement.setString(3, etudiant.getNumero());

            statement.executeUpdate();
            System.out.println("Etudiant created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateUtilisateur(Etudiant etudiant) {
        String sql = "UPDATE etudiant SET name = ?, email = ?, numero = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, etudiant.getEmail());
            statement.setString(2, etudiant.getEmail());
            statement.setString(3, etudiant.getNumero());
            statement.setInt(4, etudiant.getId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Etudiant updated successfully.");
            } else {
                System.out.println("Etudiant not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUtilisateur(int etudiantId) {
        String sql = "DELETE FROM etudiant WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, etudiantId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Etudiant deleted successfully.");
            } else {
                System.out.println("Etudiant not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Etudiant displayUtilisateur(int etudiantId) {
        String sql = "SELECT id, name, email, numero FROM etudiant WHERE id = ?";
        Etudiant etudiant = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, etudiantId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                etudiant = new Etudiant();
                etudiant.setId(resultSet.getInt("id"));
                etudiant.setEmail(resultSet.getString("name"));
                etudiant.setEmail(resultSet.getString("email"));
                etudiant.setNumero(resultSet.getString("numero"));
                System.out.println("Etudiant found: " + etudiant.getEmail());
            } else {
                System.out.println("Etudiant not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return etudiant;
    }

    public List<Etudiant> displayAllUtilisateur() {
        List<Etudiant> etudiants = new ArrayList<>();
        String sql = "SELECT id, name, email, numero FROM etudiant";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Etudiant etudiant = new Etudiant();
                etudiant.setId(resultSet.getInt("id"));
                etudiant.setNom(resultSet.getString("name"));
                etudiant.setEmail(resultSet.getString("email"));
                etudiant.setNumero(resultSet.getString("numero"));
                etudiants.add(etudiant);
            }

            System.out.println("All etudiants displayed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return etudiants;
    }


}
