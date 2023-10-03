package jdbc.model;

import java.sql.Date;

public class Guests {
    private Integer Id;
    private String FirstName;
    private String LastName;
    private Date DateOfBirth;
    private String Nationality;
    private String PhoneNumber;
    private Integer ReservationId;

    public Guests(String firstName, String lastName, Date dateOfBirth, String nationality, String phoneNumber,
                  Integer reservationId) {
        super();
        FirstName = firstName;
        LastName = lastName;
        DateOfBirth = dateOfBirth;
        Nationality = nationality;
        PhoneNumber = phoneNumber;
        ReservationId = reservationId;
    }

    public Guests(Integer id, String firstName, String lastName, Date dateOfBirth, String nationality, String phoneNumber,
                  Integer reservationId) {
        super();
        Id = id;
        FirstName = firstName;
        LastName = lastName;
        DateOfBirth = dateOfBirth;
        Nationality = nationality;
        PhoneNumber = phoneNumber;
        ReservationId = reservationId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Integer getReservationId() {
        return ReservationId;
    }

    public void setReservationId(Integer reservationId) {
        ReservationId = reservationId;
    }
}
