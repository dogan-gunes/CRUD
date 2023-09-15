package com.dgn.crud.service;

import com.dgn.crud.converter.UserConverter;
import com.dgn.crud.dto.UserCreateRequest;
import com.dgn.crud.dto.UserDto;
import com.dgn.crud.dto.UserUpdateDto;
import com.dgn.crud.exception.UserNotFoundException;
import com.dgn.crud.model.User;
import com.dgn.crud.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserService(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public UserDto createUser(UserCreateRequest userCreateRequest) {
        final User user = userRepository.save(userConverter.userRequestConverter(userCreateRequest));
        return userConverter.userDtoConverter(user);
    }

    public List<UserDto> getAllUser() {

        return userRepository.findAll()
                .stream()
                .map(userConverter::userDtoConverter)
                .toList();
    }

    public UserDto getUserById(Long id) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not Found ID : " + id));

        return userConverter.userDtoConverter(user);
    }

    public void deleteUserById(Long id) {

        final User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not Found ID : " + id));

        userRepository.delete(user);

        //userRepository.deleteById(id);
    }


    public UserDto updateUser(UserUpdateDto userUpdateDto, Long id) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not Found ID : " + id));
        user.setName(userUpdateDto.getName());
        user.setSurName(userUpdateDto.getSurName());
        user.setCity(userUpdateDto.getCity());
        user.setPhoneNumber(userUpdateDto.getPhoneNumber());

        final User saveUser = userRepository.save(user);

        return userConverter.userDtoConverter(user);

    }

}
