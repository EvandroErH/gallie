/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gallie.utils;

import javax.faces.context.FacesContext;

/**
 *
 * @author SmartBr
 */
public class WebUtil {

    public static void messageErro(String msg) {

    }

    public static void messageInfo(String msg) {

    }

    public static void redirect(String url) {
        try {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.getExternalContext().redirect(url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
