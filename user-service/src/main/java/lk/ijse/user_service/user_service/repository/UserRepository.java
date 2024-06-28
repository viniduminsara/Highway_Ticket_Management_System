package lk.ijse.user_service.user_service.repository;

import lk.ijse.user_service.user_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
