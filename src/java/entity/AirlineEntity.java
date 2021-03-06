/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Eske Wolff
 */
@Entity
public class AirlineEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String url;
    private String airlineName;
    private boolean active;

    public AirlineEntity() {
    }

    public AirlineEntity(String url, String airlineName, boolean active) {
        this.url = url;
        this.airlineName = airlineName;
        this.active = active;
    }
    

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    

    @Override
    public String toString() {
        return "entity.AirlineEntity[ url=" + url + ", active: " + active + " ]" ;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }
    
}
