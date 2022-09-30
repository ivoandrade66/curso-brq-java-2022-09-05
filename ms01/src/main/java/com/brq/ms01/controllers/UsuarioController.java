package com.brq.ms01.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @GetMapping("usuarios")
    /*
    * o GetMapping permite associar o verbo GET com a rota / Usuarios
    * */
    public String getAllUsuarios(){
        return "GET Usuarios";
    }
}
