package jdbc.controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import jdbc.dao.ReservaDAO;
import jdbc.factory.ConnectionFactory;
import jdbc.model.Reserva;

public class ReservationsController {
    private ReservaDAO reservaDAO;

    public ReservationsController() {
        Connection connection = new ConnectionFactory().retrieveConnection();
        this.reservaDAO = new ReservaDAO(connection);
    }

    public void save(Reserva reserva) {
        this.reservaDAO.save(reserva);
    }

    public List<Reserva> findAll() {
        return this.reservaDAO.findAll();
    }

    public List<Reserva> findById(String id) {
        return this.reservaDAO.findById(id);
    }

    public void update(Date checkInDate, Date checkOutDate, String value, String paymentMethod, Integer id) {
        this.reservaDAO.update(checkInDate, checkOutDate, value, paymentMethod, id);
    }

    public void delete(Integer id) {
        this.reservaDAO.delete(id);
    }
}
