package com.example.exceldemo.main;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;

public class ApkTest {
    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("java.io.tmpdir"));

        String ossPath = "XXX.apk";
        String localPath = "";
        String downloadPath = "";

        FileUtils.copyURLToFile(new URL(ossPath), new File(downloadPath), 10000, 10000);

        try (ApkFile apkFile = new ApkFile(new File(downloadPath))) {
            ApkMeta apkMeta = apkFile.getApkMeta();
            System.out.println(apkMeta.getVersionCode());
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
