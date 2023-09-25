package com.example.diffsvcserver.user;

import com.example.diffsvcserver.error.BaseResponse;
import com.example.diffsvcserver.error.DataResponse;
import com.example.diffsvcserver.error.MessageUtils;
import com.example.diffsvcserver.voice.ResponseModelVoice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

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

    @Operation(summary = "유저 회원가입", description = "유저의 정보를 받아 db에 저장한다.")
    @PostMapping("/")
    public BaseResponse join(@Valid @RequestBody UserFormDTO userFormDTO){
        userService.join(userFormDTO);
        return new BaseResponse();
    }

    @Operation(summary = "유저 회원 탈퇴", description = "유저의 정보를 db에서 삭제한다.")
    @DeleteMapping("/{id}")
    public BaseResponse deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
        return new BaseResponse();
    }
    @Operation(summary = "모델 적용하기", description = "모델 상세보기에서 적용하기 버튼 클릭 시 적용")
    @PutMapping("/")
    public BaseResponse applyModel(@RequestParam("user_id") Long userId,
                                   @RequestParam("model_id") Long modelId){
        userService.applyModel(userId,modelId);
        return new BaseResponse();
    }

    @Operation(summary = "적용중인 모델 조회", description = "유저가 현재 적용중인 모델을 조회한다.")
    @GetMapping("/{id}/applied-model")
    public DataResponse<ResponseModelVoice> viewAppliedModel(@PathVariable("id") Long userId){
        return new DataResponse<>(userService.viewAppliedModel(userId));
    }


}
