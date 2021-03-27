package service;

import dto.UserDto;
import entity.Match;
import entity.User;
import exception.CustomExceptionMessages;
import exception.CustomUserExceptionMessages;
import exception.EntityNotExistsException;
import mappers.MatchMapper;
import mappers.UserMapper;
import repository.UserRepo;
import utils.ApplicationUtils;
import validator.UserValidator;

public class UserService {

    private UserRepo userRepo;

    public UserService() {
        this.userRepo = new UserRepo();
    }

    public UserDto getUserById(String id) {

        if (id == null || id.equals("")) {
            throw new IllegalArgumentException(CustomUserExceptionMessages.INVALID_ID_MESSAGE);
        }
        User user = userRepo.findUserById(id);
        if (user == null) {
            throw new EntityNotExistsException("No user having id " + id + " exists.");
        }
        return UserMapper.entityToDto(user);
    }

    public void addUser(UserDto userDto) {

        User user=UserMapper.dtoToEntity(userDto);
        UserValidator.validateAddNewUserFlow(user);
        user.setId_user(ApplicationUtils.generateUUID());
        userRepo.insertNewUser(user);
    }

    public void removeUser(UserDto userDto){
        User user=UserMapper.dtoToEntity(userDto);
        userRepo.deleteUser(user);
    }

    public UserDto getUserByName(String username){
        if(username==null||username.equals("")){
            throw new IllegalArgumentException(CustomUserExceptionMessages.INVALID_USERNAME_MESSAGE);
        }
        User user= userRepo.findUserByName(username);
        if(user==null){
            throw new EntityNotExistsException("No user having username+ "+username+" exists.");
        }
        return UserMapper.entityToDto(user);
    }

    public void updateUserUsername(UserDto userDto,String newUserName){
        if(newUserName==null||newUserName.equals("")){
            throw new IllegalArgumentException(CustomUserExceptionMessages.INVALID_USERNAME_MESSAGE);
        }
        userDto.setUsername(newUserName);
        User user=UserMapper.dtoToEntity(userDto);
        userRepo.updateUser(user);
    }

    public void updateUserPassword(UserDto userDto,String password){
        userDto.setPassword(password);
        User user=UserMapper.dtoToEntity(userDto);
        userRepo.updateUser(user);
    }

    public void updateUser(UserDto userDto){
        User user = UserMapper.dtoToEntity(userDto);
        userRepo.updateUser(user);
    }

}
