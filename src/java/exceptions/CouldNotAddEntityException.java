/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Michael
 */
public class CouldNotAddEntityException extends Exception{

    public CouldNotAddEntityException(String message) {
        super(message);
    }

}
