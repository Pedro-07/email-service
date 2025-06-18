package br.gov.ma.suinf.email.utils;

import java.util.UUID;

public class GeradorChaveApi {
    public static void main(String[] args) {
        String chave = UUID.randomUUID().toString();
        System.out.println("Chave gerada: " + chave);
    }
}