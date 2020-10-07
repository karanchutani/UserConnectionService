package com.assignment.AstrotalkAssignment.validation;

import com.assignment.AstrotalkAssignment.constant.BasicConstants;
import com.assignment.AstrotalkAssignment.exception.InvalidDataException;
import com.assignment.AstrotalkAssignment.model.DistanceRequestModel;
import com.assignment.AstrotalkAssignment.model.RemoveRequestModel;
import com.assignment.AstrotalkAssignment.model.User;

import java.util.regex.Pattern;

/**
 * This is UserValidation class.
 * @author Karan
 */
public class UserValidation {

    /**
     * This is validateUserDetils method.
     * @param user user
     */
    public static void validateUserDetils(User user) {
        if(user.getEmail().isBlank()){
            throw new InvalidDataException(BasicConstants.EMAIL_MANDATORY);
        }
        if(!isValid(user.getEmail())){
            throw new InvalidDataException(BasicConstants.EMAIL_NOT_VALID);
        }
    }

    /**
     * This is validateRemoveRequestModelDetils method.
     * @param removeRequestModel field
     */
    public static void validateRemoveRequestModelDetils(RemoveRequestModel removeRequestModel) {
        if(removeRequestModel.getUserEmail().isBlank()){
            throw new InvalidDataException(BasicConstants.EMAIL_MANDATORY);
        }
        if(!isValid(removeRequestModel.getUserEmail())){
            throw new InvalidDataException(BasicConstants.EMAIL_NOT_VALID);
        }
        if(removeRequestModel.getConnectionEmail().isBlank()){
            throw new InvalidDataException(BasicConstants.CONNECTION_EMAIL_MANDATORY);
        }
        if(!isValid(removeRequestModel.getConnectionEmail())){
            throw new InvalidDataException(BasicConstants.CONNECTION_EMAIL_NOT_VALID);
        }
    }

    /**
     * This is isValid method.
     * @param email field
     * @return boolean
     */
    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pattern.matcher(email).matches();
    }

    public static void validateDistanceModelRequestDetails(DistanceRequestModel distanceRequestModel) {

        if(distanceRequestModel.getUserEmail().isBlank()){
            throw new InvalidDataException(BasicConstants.EMAIL_MANDATORY);
        }
        if(!isValid(distanceRequestModel.getUserEmail())){
            throw new InvalidDataException(BasicConstants.EMAIL_NOT_VALID);
        }
        if(distanceRequestModel.getDistance()<=0){
            throw new InvalidDataException(BasicConstants.DISTANCE_NOT_VALID);
        }
    }
}
