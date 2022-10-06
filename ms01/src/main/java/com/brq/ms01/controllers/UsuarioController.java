package com.brq.ms01.controllers;

import com.brq.ms01.models.UsuarioModel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


// commenter

/*
 * commenter
 * */

@RestController
public class UsuarioController {

    // ESTE ARRAYLIST É DIDÁTICO, POIS ESTÁ SIMULANDO UM BANCO DE DADOS
    private int counter = 1;
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
        //System.out.println(usuario);
        usuario.setId( counter );
        usuarios.add(usuario);
        counter++;
        System.out.println(usuario);

        //usuarios.add(usuario);
        //return "POST Usuários";
        return usuario;
    }

    // /usuarios/1 --> o valor do id vai ser 1
    @PatchMapping("usuarios/{id}")
    public UsuarioModel update(@RequestBody UsuarioModel usuarioBody,
                               @PathVariable int id ){
        // como achar o usuário a ser alterado?
        for ( int i = 0; i <  usuarios.size(); i++ ){
            if (usuarios.get(i).getId() == id){
                // achamos o usuário a ser alterado
                usuarios.get(i).setNome( usuarioBody.getNome() );
                usuarios.get(i).setEmail( usuarioBody.getEmail() );

                return usuarios.get(i);
            }//if

        }// for

        return null;
    }// update()
}
