package com.brq.ms01.services;

import com.brq.ms01.dtos.UsuarioDTO;
import com.brq.ms01.models.UsuarioModel;
import com.brq.ms01.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/*
* A camada Service é responsável por armazenar as regras de negócio da aplicação
* */
@Service
public class UsuarioService {

    // ESTE ARRAYLIST É DIDÁTICO, POIS ESTÁ SIMULANDO UM BANCO DE DADOS
    private ArrayList<UsuarioModel> usuarios = new ArrayList<>();
    private int counter = 1;

    @Autowired
    private UsuarioRepository usuRepository;

    public void mostrarMensagemService(){
        System.out.println("Mensagem do serviço");
    }

    public List<UsuarioModel> getAllUsuarios(){

        // a repository vai executar : SELECT * FROM usuarios;
        List<UsuarioModel> list = usuRepository.findAll();

        return list;
        //return usuarios;
    }

    public UsuarioDTO create(UsuarioDTO usuario){

        // usuario.setId( counter );
        //usuarios.add(usuario);
        //counter++;

        // TEMOS QUE CONVERTER UM DTO PARA UM MODEL
        // jeito Fabrizio (Burro)
//        UsuarioModel usuarioDTOtoModel = new UsuarioModel();
//        usuarioDTOtoModel.setId( usuario.getId() );
//        usuarioDTOtoModel.setNome(usuario.getNome());
//        usuarioDTOtoModel.setTelefone(usuario.getTelefone());
//        usuarioDTOtoModel.setEmail(usuario.getEmail());

        // INSERT INTO usuarios (name_user, email_user ) VALUEs()....
        UsuarioModel usuarioSalvo = usuRepository.save( usuario.toModel() );
        // return  usuRepository.save( usuario );
        // return "POST Usuários";
        //return usuario;
        return usuarioSalvo.toDTO();
    }

    public UsuarioModel update(int id, UsuarioModel usuarioBody)  {

        UsuarioModel usuario = usuRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Usuário não localizado") );


        usuario.setEmail( usuarioBody.getEmail() );
        usuario.setNome( usuarioBody.getNome() );
        usuario.setTelefone( usuarioBody.getTelefone() );

        return usuRepository.save(usuario);


//        // ver se os dados existem
//        Optional<UsuarioModel> usuarioOptional = usuRepository.findById(id);
//
//        // eu achei o usuário no banco de dados
//        if (usuarioOptional.isPresent()){
//            // retorna os valores do usuário encontrado no banco de dados
//            UsuarioModel meuUsuario = usuarioOptional.get();
//
//            meuUsuario.setEmail( usuarioBody.getEmail() );
//            meuUsuario.setNome( usuarioBody.getNome() );
//
//            UsuarioModel usuarioSalvo = usuRepository.save(meuUsuario);
//
//            return usuarioSalvo;
//        }
//        // não achei o usuário no banco
//        else{
//            return usuarioOptional.orElseThrow( () -> new RuntimeException("Usuário não encontrado"));
//        }

//        // como achar o usuário a ser alterado?
//        for ( int i = 0; i <  usuarios.size(); i++ ){
//            if (usuarios.get(i).getId() == id){
//                // achamos o usuário a ser alterado
//                usuarios.get(i).setNome( usuarioBody.getNome() );
//                usuarios.get(i).setEmail( usuarioBody.getEmail() );
//
//                return usuarios.get(i);
//            } // if
//        }// for
//
//        return null;
    }

    public String delete(int id){
        // FORECH
//        for (UsuarioModel usuarioLocal: usuarios) {
//            usuarios.remove(usuarioLocal);
//        }
//        for (int i = 0; i < usuarios.size(); i++){
//            // se achar o usuário, então delete do arraylist
//            if (usuarios.get(i).getId() == id){
//                usuarios.remove(i);
//                return "Usuário delatado com sucesso!";
//            } // if
//        } // for
//        return "Usuário não encontrado";

        usuRepository.deleteById(id);
        return "Usuário delatado com sucesso!";
    }

    public UsuarioModel getOne(int id){

        return usuRepository.findById(id)
                    .orElseThrow( () -> new RuntimeException("Usuário não localizado"));
//        Optional<UsuarioModel> usuarioOptional = usuRepository.findById(id);
//
//        if (usuarioOptional.isPresent()){
//            UsuarioModel usuario = usuarioOptional.get();
//
//            return usuario;
//        }
//        else {
//            return usuarioOptional.orElseThrow( ()-> new RuntimeException("Usuário não localizado") );
//        }


//        for (int i = 0; i < usuarios.size(); i++){
//            if (usuarios.get(i).getId() == id){
//                return usuarios.get(i);
//            } // if
//        } // for
//        return null;
    }
}
