package net.javaguides.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.EmailAlreadyExistsException;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.mapper.AutoUserMapper;
import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        /*convert UserDTO into User JPA Entity*/
        //User user = UserMapper.mapToUser(userDto);

        /*using modelMapper*/
//       User user = modelMapper.map(userDto, User.class);
        /*check if user email already exists*/
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email Already Exists for User");
        }

        /*using mapstruct*/
        User user = AutoUserMapper.MAPPER.mapToUser(userDto);
        User savedUser = userRepository.save(user);

        /*convert User JPA entity to UserDto*/
        //UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

        /*using modelMapper*/
//        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

        /*using mapstruct*/
        UserDto savedUserDto =AutoUserMapper.MAPPER.mapToUserDto(savedUser);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
//        Optional<User> optionalUser = userRepository.findById(userId);
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User", "id", userId)
        );
//        User user = optionalUser.get();
        //return UserMapper.mapToUserDto(user);

        //uses modelMapper
//        return modelMapper.map(user, UserDto.class);

//        using mapstruct
//        return AutoUserMapper.MAPPER.mapToUserDto(optionalUser.get());
        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
//        return users.stream().map(UserMapper::mapToUserDto)
//                .collect(Collectors.toList());

        //uses modelMapper
//        return users.stream().map((user) -> modelMapper.map(user, UserDto.class))
//                .collect(Collectors.toList());

//        using mapstruct
        return users.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
//        User existingUser = userRepository.findById(user.getId()).get();
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", user.getId())
        );
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
//        return UserMapper.mapToUserDto(updatedUser);

//      uses modelMapper
//        return modelMapper.map(updatedUser, UserDto.class);

//        uses mapstruct
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        userRepository.deleteById(userId);
    }


}
