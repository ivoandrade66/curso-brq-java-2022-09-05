package com.brq.ms01.controllers;

import com.brq.ms01.models.UsuarioModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


// commenter

/*
 * commenter
 * */

@RestController
public class UsuarioController {

    // ESTE ARRAYLIST É DIDÁTICO, POIS ESTÁ SIMULANDO UM BANCO DE DADOS
    private ArrayList<UsuarioModel> usuarios = new ArrayList<>();

    /*
    * o @GetMapping permite associoar o verbo GET com a rota /usuarios
    * */
    @GetMapping("usuarios")
    public ArrayList<UsuarioModel> getAllUsuarios(){

//        UsuarioModel u = new UsuarioModel();
//        u.setId(1);
//        u.setNome("Manoel");
//        u.setEmail("manoel@gmail.com");
//
//        usuarios.add(u);

        return usuarios;
    }

    @PostMapping("usuarios")
    public UsuarioModel create(@RequestBody UsuarioModel usuario){
    //public String create(@RequestBody UsuarioModel usuario){
        System.out.println(usuario);
        usuarios.add(usuario);
        //return "POST Usuários";
        return usuario;
    }


}
