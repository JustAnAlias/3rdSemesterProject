/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Michael
 */
@Entity
public class Flight implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String flightID;
    private String flightNumber;
    private String date;
    private int numberOfSeats;
    private float totalPrice;
    private int traveltime;
    private String origin;
    private String destination;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.flightID);
        hash = 53 * hash + Objects.hashCode(this.flightNumber);
        hash = 53 * hash + Objects.hashCode(this.date);
        hash = 53 * hash + this.numberOfSeats;
        hash = 53 * hash + Float.floatToIntBits(this.totalPrice);
        hash = 53 * hash + this.traveltime;
        hash = 53 * hash + Objects.hashCode(this.origin);
        hash = 53 * hash + Objects.hashCode(this.destination);
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
        final Flight other = (Flight) obj;
        if (!Objects.equals(this.flightID, other.flightID)) {
            return false;
        }
        if (!Objects.equals(this.flightNumber, other.flightNumber)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (this.numberOfSeats != other.numberOfSeats) {
            return false;
        }
        if (Float.floatToIntBits(this.totalPrice) != Float.floatToIntBits(other.totalPrice)) {
            return false;
        }
        if (this.traveltime != other.traveltime) {
            return false;
        }
        if (!Objects.equals(this.origin, other.origin)) {
            return false;
        }
        if (!Objects.equals(this.destination, other.destination)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NewEntity{" + "flightId=" + flightID + ", flightNumber=" + flightNumber + ", date=" + date + ", numberOfSeats=" + numberOfSeats + ", totalPrice=" + totalPrice + ", travelTime=" + traveltime + ", origin=" + origin + ", destination=" + destination + '}';
    }
    
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTraveltime() {
        return traveltime;
    }

    public void setTraveltime(int traveltime) {
        this.traveltime = traveltime;
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

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }
    
}
 