/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gallie.utils;

import javax.el.ELException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author SmartBr
 */
public class WebUtil {

    public static <T> T managedBean(String classe) {
        classe = StringUtil.firstLower(classe);
        return (T) WebUtil.evaluateGet("#{" + classe + "}", Object.class);
    }

    public static <T> T managedBean(Class<T> classe) {
        String beanName = "";

        if (classe.isAnnotationPresent(ManagedBean.class)) {
            if (classe.getAnnotation(ManagedBean.class).name() != null) {
                if (classe.getAnnotation(ManagedBean.class).name().equals("")) {
                    beanName = StringUtil.firstLower(classe.getSimpleName());
                } else {
                    beanName = classe.getAnnotation(ManagedBean.class).name();
                }
            } else {
                beanName = StringUtil.firstLower(beanName);
            }

            Object mb = managedBean(beanName);
            if (mb == null) {
                try {
                    mb = classe.newInstance();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            return (T) mb;
        }

        return null;
    }

    public static void messageErro(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Atenção", msg));
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash().setKeepMessages(true);
    }

    public static void messageInfo(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Atenção", msg));
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash().setKeepMessages(true);
    }

    public static void redirect(String url) {
        try {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.getExternalContext().redirect(url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static <T> T evaluateGet(String expression, Class<T> clzz) throws ELException {
        FacesContext context = FacesContext.getCurrentInstance();
        return (T) context.getApplication().evaluateExpressionGet(context, expression, Object.class);
    }
}
