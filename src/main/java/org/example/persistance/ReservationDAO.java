package org.example.persistance;

import org.example.utils.DataUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationDAO {
    private final Connection connection;
    public ReservationDAO() throws SQLException {
        connection = DataUtils.getInstance().getConnection();

    }
    public void reserveDocument(int userId, int documentId) {

        String sql = "INSERT INTO reservations (user_id, document_id, date_reservation, status) VALUES (?, ?, CURRENT_DATE, true)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setInt(2, documentId);


            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Reservation created successfully.");
            } else {
                System.out.println("Failed to create the reservation.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean getReservationStatus(int reservationId) {
        String sql = "SELECT status FROM reservations WHERE id = ?";
        boolean status = false;


        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservationId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    status = resultSet.getBoolean("status");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return status;
    }
    public boolean annulerReservation(int reservationId) {
        String sql = "UPDATE reservations SET status = false WHERE id = ? AND status = true";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservationId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
               return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

}
