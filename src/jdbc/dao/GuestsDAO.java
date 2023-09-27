package jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.model.Guests;

public class GuestsDAO {
    private Connection connection;

    public GuestsDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Guests guest) {
        try {
            String sql = "INSERT INTO guests (first_name, last_name, date_of_birth, nationality, phone_number, reservation_id) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstmt.setString(1, guest.getFirstName());
                pstmt.setString(2, guest.getLastName());
                pstmt.setDate(3, guest.getDateOfBirth());
                pstmt.setString(4, guest.getNationality());
                pstmt.setString(5, guest.getPhoneNumber());
                pstmt.setInt(6, guest.getReservationId());

                pstmt.execute();

                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    while (rs.next()) {
                        guest.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Guests> listGuests() {
        List<Guests> guests = new ArrayList<Guests>();
        try {
            String sql = "SELECT id, first_name, last_name, date_of_birth, nationality, phone_number, reservation_id FROM guests";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.execute();

                transformResultSetToGuests(guests, pstmt);
            }
            return guests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Guests> searchByReservationId(String reservationId) {
        List<Guests> guests = new ArrayList<Guests>();
        try {

            String sql = "SELECT id, first_name, last_name, date_of_birth, nationality, phone_number, reservation_id FROM guests WHERE reservation_id = ?";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, reservationId);
                pstmt.execute();

                transformResultSetToGuests(guests, pstmt);
            }
            return guests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String firstName, String lastName, Date dateOfBirth, String nationality, String phoneNumber, Integer reservationId, Integer id) {
        try (PreparedStatement stm = connection.prepareStatement(
                "UPDATE guests SET first_name = ?, last_name = ?, date_of_birth = ?, nationality = ?, phone_number = ?, reservation_id = ? WHERE id = ?")) {
            stm.setString(1, firstName);
            stm.setString(2, lastName);
            stm.setDate(3, dateOfBirth);
            stm.setString(4, nationality);
            stm.setString(5, phoneNumber);
            stm.setInt(6, reservationId);
            stm.setInt(7, id);
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Integer id) {
        try (PreparedStatement stm = connection.prepareStatement("DELETE FROM guests WHERE id = ?")) {
            stm.setInt(1, id);
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void transformResultSetToGuests(List<Guests> guests, PreparedStatement pstmt) throws SQLException {
        try (ResultSet rs = pstmt.getResultSet()) {
            while (rs.next()) {
                Guests guest = new Guests(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4),
                        rs.getString(5), rs.getString(6), rs.getInt(7));
                guests.add(guest);
            }
        }
    }
}
