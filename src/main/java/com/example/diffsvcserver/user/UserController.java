package com.example.diffsvcserver.user;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "계정 생성", notes = "회원 정보를 입력받아 db에 저장한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "계정 생성 완료"),
            @ApiResponse(code = 400, message = "형식에 부합하지 않은 필드에 대한 오류 메시지 반환")
    })
    @PostMapping("/")
    public ResponseEntity<?> join(@Valid @RequestBody UserFormDTO userFormDTO){
        String message = userService.join(userFormDTO);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
        return userService.checkIfUserExists(id)
                ? ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자가 존재하지 않습니다.");
    }
}
