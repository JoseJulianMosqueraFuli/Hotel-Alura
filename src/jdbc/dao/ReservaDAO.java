package jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.model.Reserva;

public class ReservationDAO {

    private Connection connection;

    public ReservationDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Reserva reserva) {
        try {
            String sql = "INSERT INTO reservations (check_in_date, check_out_date, value, payment_method) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstmt.setDate(1, reserva.getInDate());
                pstmt.setDate(2, reserva.getOutDate());
                pstmt.setString(3, reserva.getValue());
                pstmt.setString(4, reserva.getPaymentMethod());

                pstmt.executeUpdate();

                try (ResultSet rst = pstmt.getGeneratedKeys()) {
                    while (rst.next()) {
                        reserva.setId(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Reserva> findAll() {
        List<Reserva> reservas = new ArrayList<Reserva>();
        try {
            String sql = "SELECT id, check_in_date, check_out_date, value, payment_method FROM reservations";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.execute();

                transformResultSetToReservation(reservas, pstmt);
            }
            return reservas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Reserva> findById(String id) {
        List<Reserva> reservas = new ArrayList<Reserva>();
        try {

            String sql = "SELECT id, check_in_date, check_out_date, value, payment_method FROM reservations WHERE id = ?";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, id);
                pstmt.execute();

                transformResultSetToReservation(reservas, pstmt);
            }
            return reservas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Integer id) {
        try (PreparedStatement stm = connection.prepareStatement("DELETE FROM reservations WHERE id = ?")) {
            stm.setInt(1, id);
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Date checkInDate, Date checkOutDate, String value, String paymentMethod, Integer id) {
        try (PreparedStatement stm = connection
                .prepareStatement("UPDATE reservations SET check_in_date = ?, check_out_date = ?, value = ?, payment_method = ? WHERE id = ?")) {
            stm.setDate(1, checkInDate);
            stm.setDate(2, checkOutDate);
            stm.setString(3, value);
            stm.setString(4, paymentMethod);
            stm.setInt(5, id);
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void transformResultSetToReservation(List<Reserva> reservas, PreparedStatement pstmt) throws SQLException {
        try (ResultSet rst = pstmt.getResultSet()) {
            while (rst.next()) {
                Reserva reserva = new Reserva(rst.getInt(1), rst.getDate(2), rst.getDate(3), rst.getString(4), rst.getString(5));

                reservas.add(reserva);
            }
        }
    }
}
