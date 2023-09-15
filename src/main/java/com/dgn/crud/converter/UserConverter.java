package com.dgn.crud.converter;

import com.dgn.crud.dto.UserCreateRequest;
import com.dgn.crud.dto.UserDto;
import com.dgn.crud.model.User;
import org.springframework.stereotype.Component;


@Component
public class UserConverter {
    public UserDto userDtoConverter(User user){
        return UserDto.builder()
                .name(user.getName())
                .surName(user.getSurName())
                .city(user.getCity())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
    public User userRequestConverter(UserCreateRequest userCreateRequest){
        return User.builder()
                .name(userCreateRequest.getName())
                .surName(userCreateRequest.getSurName())
                .phoneNumber(userCreateRequest.getPhoneNumber())
                .city(userCreateRequest.getCity())
                .build();
    }
}
