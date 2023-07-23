package com.example.exceldemo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.example.exceldemo.entry.DemoEntry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/excel")
public class ExcelTestController {

    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试"+ System.currentTimeMillis(), "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

//        EasyExcel.write(response.getOutputStream(), DemoEntry.class).sheet("模板").doWrite(data(i));


        try (ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), DemoEntry.class).build()) {
            // 这里注意 如果同一个sheet只要创建一次
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
            for (int i = 0; i < 10; i++) {
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                List<DemoEntry> data = data(i);
                excelWriter.write(data, writeSheet);
            }
        }

    }

    private List<DemoEntry> data(int startIndex){
        List<DemoEntry> ds = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            DemoEntry de = new DemoEntry();
            de.setAge(i);
            de.setName(String.join("_", "" + startIndex, ""+i, "name"));
            de.setDesc(String.join("_", "" + startIndex, ""+i, "desc"));
            de.setAge(i);
            ds.add(de);
        }

        return ds;
    }
}
