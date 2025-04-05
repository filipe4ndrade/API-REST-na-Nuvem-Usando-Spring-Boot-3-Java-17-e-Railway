package com.github.filipe.desafioapi.services;

import com.github.filipe.desafioapi.entities.User;
import com.github.filipe.desafioapi.repositories.UserRepository;
import com.github.filipe.desafioapi.services.exceptions.ResourceEmptyException;
import com.github.filipe.desafioapi.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public Page<User> findAllPaged(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		Page<User> userPage = userRepository.findAll(pageable);

		if (userPage.isEmpty()) {
			throw new ResourceEmptyException("Não foram encontrados registros, base vazia.");
		}

		return userPage;
	}

	@Transactional
	public User findById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceEmptyException("Não foram encontrados registros, base vazia."));
	}

	@Transactional
	public User create(User userToCreate) {
		return userRepository.save(userToCreate);
	}

	@Transactional
	public void delete(Long id) {
		try {
			if (!userRepository.existsById(id)) {
				throw new ResourceNotFoundException(id);
			}
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	@Transactional
	public User update(Long id, User userToUpdate) {
		User dbUser = this.findById(id);
		dbUser.setId(id);
		if (dbUser.getId().equals(userToUpdate.getId())) {
			throw new ResourceNotFoundException("Update IDs must be the same.");
		}

		dbUser.setName(userToUpdate.getName());
		dbUser.setAccount(userToUpdate.getAccount());
		dbUser.setCard(userToUpdate.getCard());
		dbUser.setFeatures(userToUpdate.getFeatures());
		dbUser.setNews(userToUpdate.getNews());

		return this.userRepository.save(dbUser);
	}

}
