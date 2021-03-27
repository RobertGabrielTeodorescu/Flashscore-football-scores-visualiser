package mappers;

import dto.UserDto;
import entity.User;

public class UserMapper {

    public static UserDto entityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId_user(user.getId_user());
        userDto.setPassword(user.getPassword());
        userDto.setUsername(user.getUsername());
        userDto.setType(user.getType());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    public static User dtoToEntity(UserDto userDto){
        User user=new User();
        user.setId_user(userDto.getId_user());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setType(userDto.getType());
        return user;
    }
}
