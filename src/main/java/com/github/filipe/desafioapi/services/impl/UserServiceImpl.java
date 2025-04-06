package com.github.filipe.desafioapi.services.impl;

import com.github.filipe.desafioapi.entities.User;
import com.github.filipe.desafioapi.repositories.UserRepository;
import com.github.filipe.desafioapi.services.UserService;
import com.github.filipe.desafioapi.services.exceptions.BusinessException;
import com.github.filipe.desafioapi.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
public class UserServiceImpl implements UserService {

    private static final Long UNCHANGEABLE_USER_ID = 1L;

    @Autowired
    private UserRepository userRepository;


    @Transactional(readOnly = true)
    public Page<User> findAllPaged(Integer page, Integer size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));
    }

    @Override
    @Transactional
    public User create(User user) {
        validateUniqueConstraints(user);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(Long id, User user) {
        validateChangeableId(id, "updated");
        User existingUser = findById(id);

        if (!existingUser.getId().equals(user.getId())) {
            throw new BusinessException("Update IDs must match");
        }

        validateUniqueConstraintsOnUpdate(existingUser, user);
        updateUserFields(existingUser, user);

        return userRepository.save(existingUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        validateChangeableId(id, "deleted");
        User user = findById(id);
        userRepository.delete(user);
    }

    private void validateUniqueConstraints(User user) {
        if (userRepository.existsByAccountNumber(user.getAccount().getNumber())) {
            throw new BusinessException("Account number already exists");
        }
        if (userRepository.existsByCardNumber(user.getCard().getNumber())) {
            throw new BusinessException("Card number already exists");
        }
    }

    private void validateUniqueConstraintsOnUpdate(User existingUser, User updatedUser) {
        if (!existingUser.getAccount().getNumber().equals(updatedUser.getAccount().getNumber()) &&
                userRepository.existsByAccountNumber(updatedUser.getAccount().getNumber())) {
            throw new BusinessException("New account number already exists");
        }

        if (!existingUser.getCard().getNumber().equals(updatedUser.getCard().getNumber()) &&
                userRepository.existsByCardNumber(updatedUser.getCard().getNumber())) {
            throw new BusinessException("New card number already exists");
        }
    }

    private void updateUserFields(User existingUser, User updatedUser) {
        existingUser.setName(updatedUser.getName());
        existingUser.setAccount(updatedUser.getAccount());
        existingUser.setCard(updatedUser.getCard());
        existingUser.setFeatures(new ArrayList<>(updatedUser.getFeatures()));
        existingUser.setNews(new ArrayList<>(updatedUser.getNews()));
    }

    //Lógica para impedir atualização e exclusão do usuário 1, pois este será adm
    private void validateChangeableId(Long id, String operation) {
        if (UNCHANGEABLE_USER_ID.equals(id)) {
            throw new BusinessException("User with ID %d cannot be %s".formatted(id, operation));
        }
    }
}

