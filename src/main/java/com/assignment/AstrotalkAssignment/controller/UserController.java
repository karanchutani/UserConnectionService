package com.assignment.AstrotalkAssignment.controller;

import com.assignment.AstrotalkAssignment.DTO.ResponseDTO;
import com.assignment.AstrotalkAssignment.constant.BasicConstants;
import com.assignment.AstrotalkAssignment.exception.InvalidDataException;
import com.assignment.AstrotalkAssignment.model.DistanceRequestModel;
import com.assignment.AstrotalkAssignment.model.RemoveRequestModel;
import com.assignment.AstrotalkAssignment.model.User;
import com.assignment.AstrotalkAssignment.service.UserService;
import com.assignment.AstrotalkAssignment.util.BasicUtility;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Basic;
import java.util.List;

/**
 * Thi is UserController class.
 * @author Karan
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * userService field.
     */
    @Autowired
    private UserService userService;

    /**
     * This is createUser method.
     * @param user user
     * @return response DTO
     * @throws InvalidDataException exception
     */

    @ApiOperation(value = BasicConstants.CREATE_USER, response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = BasicConstants.USER_CREATED_SUCCESS),
            @ApiResponse(code = 403, message = BasicConstants.FORBIDDEN),
            @ApiResponse(code = 400, message = BasicConstants.BAD_REQUEST)
    }
    )
    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDTO> createUser(@RequestBody User user) throws InvalidDataException {
        final User returnedUser = userService.createNewUser(user);
        final ResponseDTO responseDTO = new ResponseDTO(BasicConstants.USER_CREATED_SUCCESS, BasicUtility.getResponse(returnedUser), BasicConstants.SUCCESS);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * This is addConnection method.
     * @param user user
     * @param email email
     * @return response DTO
     * @throws InvalidDataException exception
     * @throws Exception exception
     */

    @ApiOperation(value = "Add connection in user", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = BasicConstants.CONNECTION_ADDED_SUCCESS),
            @ApiResponse(code = 403, message = BasicConstants.FORBIDDEN),
            @ApiResponse(code = 400, message = BasicConstants.BAD_REQUEST)
    }
    )
    @PostMapping(value = "/connection/create/in/{email}")
    public ResponseEntity<ResponseDTO> addConnection(@RequestBody User user, @PathVariable("email") String email) throws InvalidDataException,Exception {
        final User returnedUser = userService.addConnectionInUser(user, email);
        final ResponseDTO responseDTO = new ResponseDTO(BasicConstants.CONNECTION_ADDED_SUCCESS_USER + email, BasicUtility.getResponse(returnedUser), "Success");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * This is addConnections method.
     * @param users users
     * @param email email
     * @return response DTO
     * @throws InvalidDataException exception
     * @throws Exception exception
     */

    @ApiOperation(value = "Add connections in user", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = BasicConstants.CONNECTIONS_ADDED_SUCCESS),
            @ApiResponse(code = 403, message = BasicConstants.FORBIDDEN),
            @ApiResponse(code = 400, message = BasicConstants.BAD_REQUEST)
    }
    )
    @PostMapping("/connections/create/in/{email}")
    public ResponseEntity<ResponseDTO> addConnections(@RequestBody List<User> users, @PathVariable("email") String email) throws InvalidDataException,Exception {
        final ResponseDTO responseDTO = userService.addConnectionsInUser(users, email);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * This is getConnectionsByUserEmail method.
     * @param email field
     * @return response DTO
     * @throws InvalidDataException exception
     */

    @ApiOperation(value = "Get all connection of a user", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = BasicConstants.FETCH_CONNECTIONS_SUCCESS),
            @ApiResponse(code = 403, message = BasicConstants.FORBIDDEN),
            @ApiResponse(code = 400, message = BasicConstants.BAD_REQUEST)
    }
    )
    @GetMapping("/connections/{email}")
    public ResponseEntity<ResponseDTO> getConnectionsByUserEmail(@PathVariable("email") String email) throws InvalidDataException {
        final List<User> returnedUsers = userService.getConnections(email);
        final ResponseDTO responseDTO = new ResponseDTO(BasicConstants.FETCH_CONNECTIONS_SUCCESS_USER + email, returnedUsers, "Success");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * This is removeConnection method.
     * @param removeRequestModel field
     * @return response DTO
     * @throws InvalidDataException exception
     */

    @ApiOperation(value = "Remove connection", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = BasicConstants.REMOVE_CONNECTION_SUCCESS),
            @ApiResponse(code = 403, message = BasicConstants.FORBIDDEN),
            @ApiResponse(code = 400, message = BasicConstants.BAD_REQUEST)
    }
    )
    @PostMapping("/remove")
    public ResponseEntity<ResponseDTO> removeConnection(@RequestBody RemoveRequestModel removeRequestModel) throws InvalidDataException {
        final User removedUser = userService.removeConnectionByModel(removeRequestModel);
        final ResponseDTO responseDTO = new ResponseDTO(BasicConstants.CONNECTION_REMOVED, BasicUtility.getResponse(removedUser), "Success");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * This is updateUserState method.
     * @param user user
     * @return response DTO
     * @throws InvalidDataException exception
     */

    @ApiOperation(value = "User updated successfully", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = BasicConstants.USER_UPDATED_SUCCESS),
            @ApiResponse(code = 403, message = BasicConstants.FORBIDDEN),
            @ApiResponse(code = 400, message = BasicConstants.BAD_REQUEST)
    }
    )
    @PutMapping("/update/state")
    public ResponseEntity<ResponseDTO> updateUserState(@RequestBody User user) throws InvalidDataException {
        final User updatedUser = userService.updateUser(user);
        final ResponseDTO responseDTO = new ResponseDTO(BasicConstants.USER_UPDATED_SUCCESS, BasicUtility.getResponse(updatedUser), "Success");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all connections at given distance of a user", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = BasicConstants.FETCH_CONNECTIONS_SUCCESS),
            @ApiResponse(code = 403, message = BasicConstants.FORBIDDEN),
            @ApiResponse(code = 400, message = BasicConstants.BAD_REQUEST)
    }
    )
    @PostMapping("/distance/connections/")
    public ResponseEntity<ResponseDTO> getConnectionsAtGivenDistance(@RequestBody DistanceRequestModel distanceRequestModel) throws InvalidDataException {
        final List<User> returnedUsers = BasicUtility.changeResponsePattern(userService.getConnectionsByDistance(distanceRequestModel));
        final ResponseDTO responseDTO = new ResponseDTO(BasicConstants.FETCH_CONNECTIONS_SUCCESS_USER + distanceRequestModel.getUserEmail(), returnedUsers, "Success");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
