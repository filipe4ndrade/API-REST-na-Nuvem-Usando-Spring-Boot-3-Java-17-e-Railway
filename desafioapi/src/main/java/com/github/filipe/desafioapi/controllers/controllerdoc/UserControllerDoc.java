package com.github.filipe.desafioapi.controllers.controllerdoc;

import com.github.filipe.desafioapi.controllers.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Users Controller", description = "RESTful API for managing users.")
public interface UserControllerDoc {

    @Operation(summary = "Get all users in a paginated form",
            description = "Retrieve a list of all users in a paginated form")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful")
    })
    ResponseEntity<Page<UserDto>> findAllPaged(
            @RequestParam(defaultValue = "1") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize
    );

    @Operation(summary = "Get a user by ID", description = "Retrieve a specific user based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    ResponseEntity<UserDto> findById(@PathVariable Long id);

    @Operation(summary = "Create a new user", description = "Create a new user and return the created user's data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
    ResponseEntity<UserDto> create(@RequestBody UserDto userDto);

    @Operation(summary = "Update a user", description = "Update the data of an existing user based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
    ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto userDto);

    @Operation(summary = "Delete a user", description = "Delete an existing user based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    ResponseEntity<Void> delete(@PathVariable Long id);


}
