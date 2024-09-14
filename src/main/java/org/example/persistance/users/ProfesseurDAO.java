package org.example.persistance.users;

import org.example.metier.Professeur;
import org.example.metier.abstracts.Utilisateur;
import org.example.persistance.interfaces.UtilisateurDAO;
import org.example.utils.DataUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesseurDAO implements UtilisateurDAO<Professeur> {
    private final Connection connection;
    public ProfesseurDAO() throws SQLException {
        connection = DataUtils.getInstance().getConnection();

    }

    @Override
    public void createUtilisateur(Professeur professeur) {
        String sql = "INSERT INTO professeur (name, email, departement) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, professeur.getNom());
            stmt.setString(2, professeur.getEmail());
            stmt.setString(3, professeur.getDepartemment());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Professeur displayUtilisateur(int id) {
        String sql = "SELECT * FROM professeur WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Professeur(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("departement")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Professeur> displayAllUtilisateur() {
        List<Professeur> professeurs = new ArrayList<>();
        String sql = "SELECT * FROM professeur";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                professeurs.add(new Professeur(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("departement")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professeurs;
    }

    @Override
    public void updateUtilisateur(Professeur professeur) {
        String sql = "UPDATE professeur SET name = ?, email = ?, departement = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, professeur.getNom());
            stmt.setString(2, professeur.getEmail());
            stmt.setString(3, professeur.getDepartemment());
            stmt.setInt(4, professeur.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUtilisateur(int id) {
        String sql = "DELETE FROM professeur WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}