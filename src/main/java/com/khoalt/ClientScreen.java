package com.khoalt;

import org.apache.commons.lang3.StringUtils;

public class ClientScreen {
    public static void main(String[] args) {
        var string = "Khoa Dang; Duy";
        var s = StringUtils.substringAfter(string, ";");
        System.out.println(s);
        //dependency management: gradle, maven
    }
}
