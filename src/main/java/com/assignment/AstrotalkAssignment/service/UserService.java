package com.assignment.AstrotalkAssignment.service;


import com.assignment.AstrotalkAssignment.DTO.ResponseDTO;
import com.assignment.AstrotalkAssignment.exception.InvalidDataException;
import com.assignment.AstrotalkAssignment.model.DistanceRequestModel;
import com.assignment.AstrotalkAssignment.model.RemoveRequestModel;
import com.assignment.AstrotalkAssignment.model.User;
import java.util.List;

/**
 * This is UserService interface.
 */
public interface UserService {

    /**
     * This is createNewUser method.
     * @param user user
     * @return user
     * @throws InvalidDataException exception
     */
    User createNewUser(User user) throws InvalidDataException;

    /**
     * This is addConnectionInUser method.
     * @param user user
     * @param email email
     * @return user
     * @throws InvalidDataException exception
     * @throws Exception exception
     */
    User addConnectionInUser(User user, String email) throws InvalidDataException,Exception;

    /**
     * This is addConnectionsInUser method.
     * @param users users
     * @param email email
     * @return list of users
     * @throws InvalidDataException exception
     * @throws Exception exception
     */
    ResponseDTO addConnectionsInUser(List<User> users, String email) throws InvalidDataException,Exception;

    /**
     * This is getConnections method.
     * @param email email
     * @return users
     * @throws InvalidDataException exception
     */
    List<User> getConnections(String email) throws InvalidDataException;

    /**
     * This is removeConnectionByModel method.
     * @param removeRequestModel model
     * @return user
     * @throws InvalidDataException exception
     */
    User removeConnectionByModel(RemoveRequestModel removeRequestModel) throws InvalidDataException;

    /**
     * This is updateUser method.
     * @param user user
     * @return user
     * @throws InvalidDataException exception
     */
    User updateUser(User user) throws InvalidDataException;

    /**
     * This is getConnectionsByDistance method.
     * @param distanceRequestModel field
     * @return lists of users
     */
    List<User> getConnectionsByDistance(DistanceRequestModel distanceRequestModel);
}