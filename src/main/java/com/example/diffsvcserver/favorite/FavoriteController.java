package com.example.diffsvcserver.favorite;

import com.example.diffsvcserver.error.BaseResponse;
import com.example.diffsvcserver.error.DataResponse;
import com.example.diffsvcserver.error.MessageUtils;
import com.example.diffsvcserver.voice.ModelVoice;
import com.example.diffsvcserver.voice.ResponseModelVoice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiResponses({
        @ApiResponse(responseCode = "200", description = MessageUtils.SUCCESS),
        @ApiResponse(responseCode = "400", description = MessageUtils.FAIL,
                content = @Content(schema = @Schema(implementation = BaseResponse.class)))})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/favorite")
@Tag(name = "Favorite", description = "Favorite API")
public class FavoriteController {
    private final FavoriteService favoriteService;
    @Operation(summary = "모델 즐겨찾기 추가", description = "하트 누르면 즐겨찾기에 추가한다.")
    @PostMapping("/")
    public BaseResponse addFavorite(@RequestParam("user_id") Long userId,
                                    @RequestParam("model_id") Long modelVoiceId){
        favoriteService.addFavorite(userId,modelVoiceId);
        return new BaseResponse();
    }

    @Operation(summary = "즐겨찾기 모델 조회", description = "즐겨찾기한 모델 리스트를 조회한다.")
    @GetMapping("/")
    public DataResponse<List<ResponseModelVoice>> viewFavoriteModels(@RequestParam("user_id") Long userId){
        List<ResponseModelVoice> modelVoices = favoriteService.viewFavoriteModels(userId);
        return new DataResponse<>(modelVoices);
    }
}
