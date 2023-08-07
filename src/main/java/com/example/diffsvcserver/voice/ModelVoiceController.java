package com.example.diffsvcserver.voice;

import com.example.diffsvcserver.error.BaseResponse;
import com.example.diffsvcserver.error.DataResponse;
import com.example.diffsvcserver.error.MessageUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@ApiResponses({
        @ApiResponse(responseCode = "200", description = MessageUtils.SUCCESS),
        @ApiResponse(responseCode = "400", description = MessageUtils.FAIL,
                content = @Content(schema = @Schema(implementation = BaseResponse.class)))})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/model")
public class ModelVoiceController {
    private final ModelVoiceService modelVoiceService;
    @Operation(summary = "모델 상세보기",description = "모델 클릭 시 모델의 상세 페이지로 이동한다")
    @GetMapping("/{id}")
    public DataResponse<ModelVoice> viewModel(@PathVariable Long id){
        return new DataResponse<>(modelVoiceService.findById(id));
    }
}
