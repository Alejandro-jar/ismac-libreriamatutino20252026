package com.distribuida.principal;

import com.distribuida.model.Cliente;

public class ClientePrincipal {

    public static void main(String[] args) {

        Cliente cliente = new Cliente(1, "1726692310", "Juan", "Taipe", "av. donde sea", "0996401766", "correo@correa");

        System.out.println(cliente.toString());
    }
}
