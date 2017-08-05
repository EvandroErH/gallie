/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gallie.enums;

/**
 *
 * @author operador
 */
public enum EnumStatus {

    TODOS("#999"),
    PENDENTE("#e6be7e"),
    APROVADO("#00cc00"),
    REJEITADO("#ff0000");

    private final String color;

    private EnumStatus(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

}
