package jdbc.model;

import java.sql.Date;

public class Reservation {
    private Integer id;
    private Date dateD;
    private Date dateS;
    private String value;
    private String paymentMode;

    public Reservation(Date dateD, Date dateS, String value, String paymentMode) {
        this.id = null; // El id se establece como nulo por defecto
        this.dateD = dateD;
        this.dateS = dateS;
        this.value = value;
        this.paymentMode = paymentMode;
    }

    public Reservation(Integer id, Date dateD, Date dateS, String value, String paymentMode) {
        this.id = id;
        this.dateD = dateD;
        this.dateS = dateS;
        this.value = value;
        this.paymentMode = paymentMode;
    }

    public Integer getId() {
        return id;
    }

    public Date getDateD() {
        return dateD;
    }

    public Date getDateS() {
        return dateS;
    }

    public String getValue() {
        return value;
    }

    public String getPaymentMode() {
        return paymentMode;
    }
}
