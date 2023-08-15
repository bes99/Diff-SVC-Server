package com.example.diffsvcserver.user;

import com.example.diffsvcserver.error.BaseResponse;
import com.example.diffsvcserver.error.DataResponse;
import com.example.diffsvcserver.error.MessageUtils;
import com.example.diffsvcserver.voice.ModelVoice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@ApiResponses({
        @ApiResponse(responseCode = "200", description = MessageUtils.SUCCESS),
        @ApiResponse(responseCode = "400", description = MessageUtils.FAIL,
                content = @Content(schema = @Schema(implementation = BaseResponse.class)))})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "user", description = "User API")
public class UserController {
    private final UserService userService;

    @Operation(summary = "유저 상세정보 조회", description = "유저의 상세정보와 해당 유저를 보유하고 있는 결과 보이스를 조회합니다.")
    @GetMapping("/{id}")
    public DataResponse<User> select(@PathVariable Long id){
        return new DataResponse<>(userService.findById(id));
    }

    @Operation(summary = "유저 회원가입", description = "유저의 정보를 받아 db에 저장합니다.")
    @PostMapping("")
    public BaseResponse join(@Valid @RequestBody UserFormDTO userFormDTO){
        userService.join(userFormDTO);
        return new BaseResponse();
    }

    @Operation(summary = "유저 회원 탈퇴", description = "유저의 정보를 db에서 삭제합니다.")
    @DeleteMapping("/{id}")
    public BaseResponse deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
        return new BaseResponse();
    }
//    @Operation(summary = "모델 적용하기", description = "사용할 모델을 가져온 후 유저 정보에 저장한다.")
//    @GetMapping("")
//    public DataResponse<> selectModel(){
//
//    }

//    @Operation(summary = "모델 즐겨찾기",description = "유저가 즐겨찾기 설정한 모델들을 가져온다")
//    @GetMapping("/{id}/bookmark")
//    public DataResponse<List<ModelVoice>> bookmark(@PathVariable Long id){
//        return new DataResponse<>(userService.getBookmark(id));
//    }

}
