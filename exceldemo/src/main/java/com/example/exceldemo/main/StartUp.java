package com.example.exceldemo.main;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.example.exceldemo.entry.TVEntry;
import org.apache.logging.log4j.util.Strings;

import java.util.HashSet;
import java.util.Set;

public class StartUp {

    public static void main(String[] args) {
        String fileName = "xxx/.1.xlsx";
        Set<String> mac = new HashSet<>();

        EasyExcel.read(fileName, TVEntry.class, new PageReadListener<TVEntry>(dataList->{
            for (TVEntry tvEntry: dataList){
                if(Strings.isBlank(tvEntry.getHotelName())){
                    mac.add(tvEntry.getWiredMac());
                }
            }
        })).sheet().doRead();
        System.out.println(mac);
    }
}
