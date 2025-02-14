package com.lama.xlsmax.service;

import com.lama.xlsmax.util.SortUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public int getMaxInt(String filePath, int number) {
        filePath = URLDecoder.decode(filePath, StandardCharsets.UTF_8);
        File excelFile = new File(filePath);
        List<Integer> numbers = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(excelFile)) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // Предполагаем, что данные находятся на первом листе

            for (Row currentRow : sheet) {
                if (currentRow.getCell(0).getCellType() == CellType.NUMERIC) { // Проверяем первый столбец
                    double cellValue = currentRow.getCell(0).getNumericCellValue();
                    numbers.add((int) cellValue);
                }
            }

        } catch (FileNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Файл  не найден");
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ошибка чтения");
        }
        if (numbers.isEmpty() ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "В файле нет чисел");
        }
        int[] arr = SortUtil.mergeSort(numbers
                .stream()
                .mapToInt(Integer::intValue).toArray());
        return SortUtil.mergeSort(arr)[numbers.size() - number];
    }
}
