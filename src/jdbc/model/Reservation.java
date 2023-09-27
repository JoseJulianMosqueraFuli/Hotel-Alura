package jdbc.model;

import java.sql.Date;

public class Reservation {
    private Integer id;
    private Date dateIn;
    private Date dateOut;
    private String value;
    private String paymentMethod;

    public Reservation(Date dateIn, Date dateOut, String value, String paymentMethod) {
        this.id = null; // El id se establece como nulo por defecto
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.value = value;
        this.paymentMethod = paymentMethod;
    }

    public Reservation(Integer id, Date dateIn, Date dateOut, String value, String paymentMethod) {
        this.id = id;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.value = value;
        this.paymentMethod = paymentMethod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInDate() {
        return dateIn;
    }

    public Date getOutDate() {
        return dateOut;
    }

    public String getValue() {
        return value;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
