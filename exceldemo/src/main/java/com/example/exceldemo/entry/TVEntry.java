package com.example.exceldemo.entry;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;

@ExcelIgnoreUnannotated
@Getter
@Setter
public class TVEntry {

    @ExcelProperty("有线mac")
    private String wiredMac;

    @ExcelProperty("酒店名称")
    private String hotelName;
}
