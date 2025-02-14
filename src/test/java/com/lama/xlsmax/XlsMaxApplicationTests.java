package com.lama.xlsmax;

import com.lama.xlsmax.service.ExcelServiceImpl;
import com.lama.xlsmax.util.SortUtil;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;


class XlsMaxApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void mergeSort() {
        int[] arr = {10, 4, 22, 5, 7, 55, 19, 8};
        int[] result = SortUtil.mergeSort(arr);
        int[] expected = {4, 5, 7, 8, 10, 19, 22, 55};
        assertArrayEquals(result, expected);
    }

    @Test
    void mergeSortEmptyArray() {
        int[] arr = new int[0];
        int[] result = SortUtil.mergeSort(arr);
        int[] expected = new int[0];
        assertArrayEquals(result, expected);
    }


    @Test
    void shouldReadExcelFile() {
        ExcelServiceImpl excelService = new ExcelServiceImpl();
        int maxInt = excelService.getMaxInt("src/test/resources/int1.xlsx", 3);
        assertEquals(44, maxInt);
    }


    @Test
    void shouldBadReadExcelFile() {
        ExcelServiceImpl excelService = new ExcelServiceImpl();
        assertThrows(ResponseStatusException.class, ()-> {
            excelService.getMaxInt("src/test/resources/int2.xlsx", 3);
        });
    }

    @Test
    void shouldReadCleanExcelFile() {
        ExcelServiceImpl excelService = new ExcelServiceImpl();
        assertThrows(ResponseStatusException.class, ()-> {
            excelService.getMaxInt("src/test/resources/clean.xlsx", 3);
        });
    }


}
