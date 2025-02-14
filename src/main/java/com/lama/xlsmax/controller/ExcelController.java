package com.lama.xlsmax.controller;

import com.lama.xlsmax.service.ExcelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {

    private final ExcelService excelService;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @Operation(
            summary = "Найти максимальное число в файле Excel",
            description = "Этот метод принимает путь к файлу Excel и число N, считывает первый столбец и возвращает N-ное максимальное число."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешный ответ с максимальным числом", content = @Content),
            @ApiResponse(responseCode = "400", description = "Ошибка при пустом файле", content = @Content),
            @ApiResponse(responseCode = "404", description = "Ошибка при отсутствии файла", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content)
    })
    @GetMapping("/maxint")
    public int getMaxInt(
            @Parameter(description = "path for local file") @RequestParam String filePath,
            @Parameter(description = "number of max int") @RequestParam int max
    ) {
        return excelService.getMaxInt(filePath, max);
    }
}
