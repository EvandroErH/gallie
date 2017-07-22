/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gallie.utils;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author operador
 */
public class StringUtil {

    public static String firstLower(String str) {
        if (str != null) {
            if (!str.equals("") && str.length() > 1) {
                return Character.toLowerCase(str.charAt(0)) + str.substring(1);
            }
        }
        return null;
    }

    public static String capitalizar(String string) {
        return StringUtils.capitalize(string);
    }

}
