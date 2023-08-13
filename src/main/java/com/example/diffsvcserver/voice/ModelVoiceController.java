package com.example.diffsvcserver.voice;

import com.example.diffsvcserver.error.BaseResponse;
import com.example.diffsvcserver.error.DataResponse;
import com.example.diffsvcserver.error.MessageUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @Operation(summary = "모델 업로드",description = ".ckpt형식의 학습된 모델을 저장한다.(최대 10MB)")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse resistModel(
            @Parameter(description = "multipart/form-data 형식의 파일을 input으로 받는다. 이때 key 값은 file이다.")
            @RequestPart("file") MultipartFile multipartFile,
            @RequestPart String name, @RequestPart String description, @RequestPart String tag) throws Exception {
        modelVoiceService.uploadAndSave(name,description,tag,multipartFile);
        return new BaseResponse();
    }
}
