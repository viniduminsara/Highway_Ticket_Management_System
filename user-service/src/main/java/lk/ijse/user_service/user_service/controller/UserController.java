package lk.ijse.user_service.user_service.controller;

import lk.ijse.user_service.user_service.dto.UserDTO;
import lk.ijse.user_service.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String health(){
        return "User Ok";
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){
        try {
            userService.registerUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
