package com.brq.ms01.repositores;

import com.brq.ms01.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
* O primeiro argumento do JpaRepository é a classe modelo para mapear uma tabela e a classe java
*
* O segundo argumento é o tipo de dado da chave primaria
 */
@Repository
public interface UsuarioRepository extends JpaRepository <UsuarioModel, Integer>{

}
