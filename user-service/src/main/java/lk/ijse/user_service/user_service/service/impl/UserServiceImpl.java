package lk.ijse.user_service.user_service.service.impl;

import lk.ijse.user_service.user_service.dto.UserDTO;
import lk.ijse.user_service.user_service.repository.UserRepository;
import lk.ijse.user_service.user_service.service.UserService;
import lk.ijse.user_service.user_service.util.Conversion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final Conversion conversion;

    @Override
    public void registerUser(UserDTO userDTO) {
        userRepo.save(conversion.convertUserEntity(userDTO));
    }

}
