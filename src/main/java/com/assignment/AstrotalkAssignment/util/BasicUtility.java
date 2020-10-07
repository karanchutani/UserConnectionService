package com.assignment.AstrotalkAssignment.util;

import com.assignment.AstrotalkAssignment.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This is Constant file.
 * @author Karan
 */
public class BasicUtility {

    /**
     * This is static getResponse method.
     * @param user user
     * @return user
     */
    public static User getResponse(User user){
        User responseUser = new User();
        responseUser.setIsCreated(user.isCreated());
        responseUser.setMobile(user.getMobile());
        responseUser.setEmail(user.getEmail());
        return responseUser;
    }

    public static List<User> changeResponsePattern(List<User> connectionsByDistance) {
        Set<User> connectionsSet = new HashSet<>(connectionsByDistance);
        connectionsByDistance.clear();
        connectionsByDistance.addAll(connectionsSet);
        List<User> returnedUsers = new ArrayList<>();
        connectionsByDistance.stream().forEach(connection ->{
            returnedUsers.add(getResponse(connection));
        });
        return returnedUsers;
    }
}
