package ai.ready.ready.user.dto;

import ai.ready.ready.user.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserDTOMapper {
    UserDTO toUserDTO(User user);
    User toUser(UserDTO userDTO);
    List<UserDTO> toUserDTOs(List<User> userList);
    List<User> toUsers(List<UserDTO> userDTOList);
}
