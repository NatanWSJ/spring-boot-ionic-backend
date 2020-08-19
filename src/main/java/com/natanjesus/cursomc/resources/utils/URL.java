package com.natanjesus.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

    public static String decodeParam(String param) {

        try {
            return URLDecoder.decode(param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }

    }

    public static List<Integer> decodeIntList(String param) {

        List<Integer> list = new ArrayList<>();
        String[] strings = param.split(",");

        for (String s : strings) {
            list.add(Integer.parseInt(s));
        }

        return list;

        //Forma Abreviada
        //return Arrays.asList(param.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());

    }


}