package com.assignment.AstrotalkAssignment.service.Impl;

import com.assignment.AstrotalkAssignment.DTO.ResponseDTO;
import com.assignment.AstrotalkAssignment.constant.BasicConstants;
import com.assignment.AstrotalkAssignment.exception.ConnectionAlreadyExistException;
import com.assignment.AstrotalkAssignment.exception.InvalidDataException;
import com.assignment.AstrotalkAssignment.model.DistanceRequestModel;
import com.assignment.AstrotalkAssignment.model.RemoveRequestModel;
import com.assignment.AstrotalkAssignment.model.User;
import com.assignment.AstrotalkAssignment.repository.UserRepository;
import com.assignment.AstrotalkAssignment.service.UserService;
import com.assignment.AstrotalkAssignment.util.BasicUtility;
import com.assignment.AstrotalkAssignment.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This is UserServiceImpl class.
 *
 * @author Karan
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * userRepository field.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * This is createNewUser method.
     *
     * @param user user
     * @return user
     * @throws InvalidDataException exception
     */
    @Override
    public User createNewUser(User user) throws InvalidDataException {
        UserValidation.validateUserDetils(user);
        final User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new InvalidDataException(BasicConstants.USER_ALREADY_EXIST);
        }
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    /**
     * This is addConnectionInUser method.
     *
     * @param user  user
     * @param email email
     * @return user
     * @throws InvalidDataException exception
     * @throws Exception            exception
     */
    @Override
    public User addConnectionInUser(User user, String email) throws InvalidDataException, Exception {
        UserValidation.validateUserDetils(user);
        List<User> existingConnection = null;
        final User existingUser = userRepository.findByEmail(email);
        if (existingUser == null) {
            throw new InvalidDataException(BasicConstants.USER_NOT_FOUND);
        } else if (!existingUser.isCreated()) {
            throw new InvalidDataException(BasicConstants.HITTING_UPDATE_USER_API_MESSAGE);
        }
        if (user.getEmail().equalsIgnoreCase(email)) {
            throw new InvalidDataException(BasicConstants.INVALID_CONNECTION);
        }

        List<User> existingList = existingUser.getConnections();
        if (existingList == null) {
            existingList = new ArrayList<>();
        }
        boolean isConnectionExist = false;
        for (User list : existingList
        ) {
            if (list.getEmail().equalsIgnoreCase(user.getEmail())) {
                isConnectionExist = true;
                break;
            }
        }
        if (isConnectionExist) {
            throw new ConnectionAlreadyExistException(BasicConstants.USER_CONNECTION_ALREADY_EXIST + user.getEmail());
        }

        User existingConnectionUser = userRepository.findByEmail(user.getEmail());
        User prepareUser = user;
        if (existingConnectionUser != null) {
            prepareUser = existingConnectionUser;
        }
        if (prepareUser.getConnections() != null) {
            existingConnection = prepareUser.getConnections();
        } else {
            existingConnection = new ArrayList<>();
        }
        existingConnection.add(existingUser);
        prepareUser.setConnections(existingConnection);
        final User returnedUser = userRepository.save(prepareUser);

        existingList.add(returnedUser);
        existingUser.setConnections(existingList);
        userRepository.save(existingUser);
        return returnedUser;
    }

    /**
     * This is addConnectionsInUser method.
     *
     * @param users users
     * @param email email
     * @return list of users
     * @throws InvalidDataException exception
     * @throws Exception            exception
     */
    @Override
    public ResponseDTO addConnectionsInUser(List<User> users, String email) throws InvalidDataException, Exception {
        List<User> responseList = new ArrayList<>();
        List<String> addInfo = new ArrayList<>();
        User responseUser = null;
        for (User user : users
        ) {
            try {
                final User connectionUser = addConnectionInUser(user, email);
                responseUser = new User(connectionUser.getEmail(), connectionUser.getMobile(), connectionUser.isCreated());
                responseList.add(responseUser);
            } catch (ConnectionAlreadyExistException e) {
                addInfo.add(e.getLocalizedMessage());
            }
        }
        final ResponseDTO responseDTO = new ResponseDTO(BasicConstants.CONNECTIONS_ADDED_SUCCESS + email, responseList, BasicConstants.SUCCESS, addInfo);
        return responseDTO;
    }

    /**
     * This is getConnections method.
     *
     * @param email email
     * @return list of users
     * @throws InvalidDataException exception
     */
    @Override
    public List<User> getConnections(String email) throws InvalidDataException {
        final User existingUser = userRepository.findByEmail(email);
        if (existingUser == null) {
            throw new InvalidDataException(BasicConstants.USER_NOT_FOUND);
        } else if (!existingUser.isCreated()) {
            throw new InvalidDataException(BasicConstants.HITTING_UPDATE_USER_API_MESSAGE);
        }
        final List<User> returnedUsers = new ArrayList<>();
        User returnedUser = null;
        List<User> innerConnections = existingUser.getConnections();
        if (innerConnections != null) {
            for (final User user : innerConnections
            ) {
                returnedUser = new User();
                returnedUser.setMobile(user.getMobile());
                returnedUser.setEmail(user.getEmail());
                returnedUser.setIsCreated(user.isCreated());
                returnedUsers.add(returnedUser);
            }
        }
        return returnedUsers;
    }

    /**
     * This is removeConnectionByModel method.
     *
     * @param removeRequestModel field
     * @return user
     * @throws InvalidDataException exception
     */
    @Override
    public User removeConnectionByModel(RemoveRequestModel removeRequestModel) throws InvalidDataException {
        UserValidation.validateRemoveRequestModelDetils(removeRequestModel);
        final User existingUser = userRepository.findByEmail(removeRequestModel.getUserEmail());
        if (existingUser == null) {
            throw new InvalidDataException(BasicConstants.USER_NOT_FOUND);
        } else if (!existingUser.isCreated()) {
            throw new InvalidDataException(BasicConstants.HITTING_UPDATE_USER_API_MESSAGE);
        }
        List<User> existingConnections = existingUser.getConnections();
        int removeIndex = BasicConstants.DEFAULT_REMOVE_INDEX;
        User removedUser = null;
        if (existingConnections != null && existingConnections.size() > BasicConstants.ZERO) {
            for (User user : existingConnections
            ) {
                if (user.getEmail().equalsIgnoreCase(removeRequestModel.getConnectionEmail())) {
                    removedUser = user;
                    removeIndex = existingConnections.indexOf(user);
                    break;
                }
            }
        }
        if (removeIndex == BasicConstants.DEFAULT_REMOVE_INDEX) {
            throw new InvalidDataException(BasicConstants.USER_CONNECTION_NOT_AVAILABLE);
        }
        existingConnections.remove(removeIndex);
        existingUser.setConnections(existingConnections);
        userRepository.save(existingUser);
        return removedUser;
    }

    /**
     * This is updateUser method.
     *
     * @param user field
     * @return user
     * @throws InvalidDataException exception
     */
    @Override
    public User updateUser(User user) throws InvalidDataException {
        UserValidation.validateUserDetils(user);
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser == null) {
            throw new InvalidDataException(BasicConstants.USER_NOT_FOUND);
        }
        existingUser.setIsCreated(user.isCreated());
        return userRepository.save(existingUser);
    }

    /**
     * This is getConnectionsByDistance method.
     * @param distanceRequestModel field
     * @return lists of users
     */
    @Override
    public List<User> getConnectionsByDistance(DistanceRequestModel distanceRequestModel) {
        UserValidation.validateDistanceModelRequestDetails(distanceRequestModel);
        final User existingUser = userRepository.findByEmail(distanceRequestModel.getUserEmail());
        if (existingUser == null) {
            throw new InvalidDataException(BasicConstants.USER_NOT_FOUND);
        } else if (!existingUser.isCreated()) {
            throw new InvalidDataException(BasicConstants.HITTING_UPDATE_USER_API_MESSAGE);
        }
        List<User> innerResult = existingUser.getConnections();
        List<User> result = new ArrayList<>();
        for (int outerVariable = 0; outerVariable < distanceRequestModel.getDistance(); outerVariable++) {
            List<User> innerResultData = new ArrayList<>();

            if (outerVariable != 0) {
                for (User innerTempUser : innerResult
                ) {
                    if (innerTempUser.getConnections() != null)
                        innerResultData.addAll(innerTempUser.getConnections());
                }
                innerResult = innerResultData;
            }
            if (outerVariable == distanceRequestModel.getDistance() - 1) {
                result = innerResult;
                break;
            }
        }
        return result;
    }
}
