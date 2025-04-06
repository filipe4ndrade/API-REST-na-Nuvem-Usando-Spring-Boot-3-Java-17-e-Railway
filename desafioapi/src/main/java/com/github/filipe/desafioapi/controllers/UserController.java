package com.github.filipe.desafioapi.controllers;

import java.net.URI;

import com.github.filipe.desafioapi.controllers.controllerdoc.UserControllerDoc;
import com.github.filipe.desafioapi.controllers.dto.UserDto;
import com.github.filipe.desafioapi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController implements UserControllerDoc {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@Override
	@GetMapping
	public ResponseEntity<Page<UserDto>> findAllPaged(Integer pageNumber, Integer pageSize) {
		var userPage = userService.findAllPaged(pageNumber, pageSize);
		var usersDto = userPage.map(UserDto::new);
		return ResponseEntity.ok(usersDto);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> findById(@PathVariable Long id) {
		var user = userService.findById(id);
		return ResponseEntity.ok(new UserDto(user));
	}

	@Override
	@PostMapping
	public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto userDto) {
		var user = userService.create(userDto.toModel());
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(user.getId())
				.toUri();
		return ResponseEntity.created(location).body(new UserDto(user));
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid UserDto userDto) {
		var user = userService.update(id, userDto.toModel());
		return ResponseEntity.ok(new UserDto(user));
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
