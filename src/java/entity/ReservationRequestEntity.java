/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Michael
 */
@Entity


public class ReservationRequestEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String flightID;
    private int numberOfSeats;
    private String reserveeName;
    private String reservePhone;
    private String reserveeEmail;
    @OneToMany
    private List<Passenger> passengers;

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getReserveeName() {
        return reserveeName;
    }

    public void setReserveeName(String reserveeName) {
        this.reserveeName = reserveeName;
    }

    public String getReserveePhone() {
        return reservePhone;
    }

    public void setReserveePhone(String reserveePhone) {
        this.reservePhone = reserveePhone;
    }

    public String getReserveeEmail() {
        return reserveeEmail;
    }

    public void setReserveeEmail(String reserveeEmail) {
        this.reserveeEmail = reserveeEmail;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.flightID);
        hash = 97 * hash + this.numberOfSeats;
        hash = 97 * hash + Objects.hashCode(this.reserveeName);
        hash = 97 * hash + Objects.hashCode(this.reservePhone);
        hash = 97 * hash + Objects.hashCode(this.reserveeEmail);
        hash = 97 * hash + Objects.hashCode(this.passengers);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReservationRequestEntity other = (ReservationRequestEntity) obj;
        if (!Objects.equals(this.flightID, other.flightID)) {
            return false;
        }
        if (this.numberOfSeats != other.numberOfSeats) {
            return false;
        }
        if (!Objects.equals(this.reserveeName, other.reserveeName)) {
            return false;
        }
        if (!Objects.equals(this.reservePhone, other.reservePhone)) {
            return false;
        }
        if (!Objects.equals(this.reserveeEmail, other.reserveeEmail)) {
            return false;
        }
        if (!Objects.equals(this.passengers, other.passengers)) {
            return false;
        }
        return true;
    }

    
    
}
