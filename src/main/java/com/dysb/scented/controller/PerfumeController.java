package com.dysb.scented.controller;

import com.dysb.scented.annotation.ApiPageParameters;
import com.dysb.scented.dto.PerfumeDto;
import com.dysb.scented.model.ApiPageParameter;
import com.dysb.scented.model.ApiQueryParameter;
import com.dysb.scented.model.ApiResponseData;
import com.dysb.scented.service.PerfumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

@RequestMapping("/perfume")
@RestController
@RequiredArgsConstructor
public class PerfumeController {

    private final PerfumeService perfumeService;

    @Operation(summary = "향수 리스트 조회", tags = {"Perfume Controller"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PerfumeDto.class))))
    })
    @Parameter(in = ParameterIn.QUERY, name = "name", schema = @Schema(type = "String"), description = "name")
    @ApiPageParameters
    @GetMapping
    public ResponseEntity<Object> getList() {
        return ApiResponseData.ok(perfumeService.getList(
                new ApiQueryParameter(RequestContextHolder.currentRequestAttributes()),
                new ApiPageParameter(RequestContextHolder.currentRequestAttributes())));
    }

    @Operation(summary = "향수 등록", tags = {"Perfume Controller"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = PerfumeDto.class)))
    })
    @PostMapping
    public ResponseEntity<Object> addPerfume(@RequestBody PerfumeDto perfumeDto) {
        return ApiResponseData.ok(perfumeService.addPerfume(perfumeDto));
    }

    @Operation(summary = "향수 수정", tags = {"Perfume Controller"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = PerfumeDto.class)))
    })
    @PutMapping
    public ResponseEntity<Object> updatePerfume(@RequestBody PerfumeDto perfumeDto) {
        return ApiResponseData.ok(perfumeService.updatePerfume(perfumeDto));
    }

    @Operation(summary = "향수 삭제", tags = {"Perfume Controller"})
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletePerfume(@PathVariable("id") Long id) {
        perfumeService.deletePerfume(id);
        return ApiResponseData.ok();
    }


}
