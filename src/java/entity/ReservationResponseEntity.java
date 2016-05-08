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
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Michael
 */
@Entity
public class ReservationResponseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String flightNumber;
    private String origin;
    private String destination;
    private String date;
    private int flightTime;
    private int numberOfSeats;
    private String reserveeName;
    @OneToMany
    private List<Passenger> passengers;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(int flightTime) {
        this.flightTime = flightTime;
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

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.flightNumber);
        hash = 97 * hash + Objects.hashCode(this.origin);
        hash = 97 * hash + Objects.hashCode(this.destination);
        hash = 97 * hash + Objects.hashCode(this.date);
        hash = 97 * hash + this.flightTime;
        hash = 97 * hash + this.numberOfSeats;
        hash = 97 * hash + Objects.hashCode(this.reserveeName);
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
        final ReservationResponseEntity other = (ReservationResponseEntity) obj;
        if (!Objects.equals(this.flightNumber, other.flightNumber)) {
            return false;
        }
        if (!Objects.equals(this.origin, other.origin)) {
            return false;
        }
        if (!Objects.equals(this.destination, other.destination)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (this.flightTime != other.flightTime) {
            return false;
        }
        if (this.numberOfSeats != other.numberOfSeats) {
            return false;
        }
        if (!Objects.equals(this.reserveeName, other.reserveeName)) {
            return false;
        }
        if (!Objects.equals(this.passengers, other.passengers)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ReservationResponseEntity{" + "flightNumber=" + flightNumber + ", origin=" + origin + ", destination=" + destination + ", date=" + date + ", flightTime=" + flightTime + ", numberOfSeats=" + numberOfSeats + ", reserveeName=" + reserveeName + ", passengers=" + passengers + '}';
    }
    
    
}
