package com.brq.ms06.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.brq.ms06.exceptions.NaoAcheiException;
import com.brq.ms06.models.UsuarioModel;
import com.brq.ms06.repositories.UsuarioRepository;
import com.brq.ms06.utils.Utils;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UsuarioServiceTest {

	// instanciar o objeto que queremos testar
	@Autowired
	private UsuarioService service;
	
	// mockar os outros objetos necessário para teste unitário
	@MockBean
	private UsuarioRepository repository;
	
	@MockBean
	private Utils utils;
	
	@Test
	void getAllTest() {
		
		// DADO QUE (cenário inicial de teste : inicialização das variáveis)

		// QUANDO( mockar: quando simulamos as outras camadas necessárias do teste)
		final var model = UsuarioModel
						.builder()
						.id("1")
						.nome("nome")
						.email("email")
						.build();
		
		final var listEntity = Arrays.asList(model);
		when(repository.findAll()).thenReturn(listEntity);

		// ENTÃO (execução do teste: chamar o método a ser testado)
		final var response = service.getAll();

		// VERIFICAR (verificar o resultado do passo anterior)
		assertThat(response.get(0).getNome())
			.isEqualTo(listEntity.get(0).getNome());
		
		assertThat(response.get(0).getEmail())
			.isEqualTo(listEntity.get(0).getEmail());
		
	} 
	
	@Test
	void getOneWhenFindUser() {
		
		// dado que
		String id = "1";
		String nome = "nome";
		String email = "email";
		
		final var usuarioModel = getUsuarioModelMock(id, nome, email);
		final var optional = Optional.of(usuarioModel);
		
		// quando
		when(repository.findById(id)).thenReturn(optional);
		
		// então
		final var response = service.getOne(id);
		
		// validar teste
		assertThat(response.getEmail()).isEqualTo(email);
		assertThat(response.getNome()).isEqualTo(nome);
		assertThat(response.getId()).isEqualTo(id);
	}
	
	@Test
	void getOneNotWhenFindUser() {
		
		// dado que
		String id = "1";
		Optional<UsuarioModel> optional = Optional.empty();
		
		// quando
		when(repository.findById(id)).thenReturn(optional);
		
		// então
		assertThrows( NaoAcheiException.class, () -> service.getOne(id));
		
		// verificar testes
	}
	
	@Test
	void createTest() {
		
		// dado que
		String id = "1";
		String nome = "nome";
		String email = "email";
		
		final var usuarioModel = getUsuarioModelMock(id, nome, email);
		
		// quando
		when(repository.save(usuarioModel)).thenReturn(usuarioModel);
		
		// então
		final var response = service.create(usuarioModel);
		
		//verificar testes
		assertThat(response.getEmail()).isEqualTo(email);
		assertThat(response.getNome()).isEqualTo(nome);
		assertThat(response.getId()).isEqualTo(id);
	}
	
	@Test
	void updateWhenFindUser() {
		
		// dado que
		String id = "1";
		String nome = "nome";
		String email = "email";
		String nomeAlterado = "nomeAlterado";
		String emailAlterado = "emailAlterado";
		
		final var usuarioModel = getUsuarioModelMock(id, nome, email);
		final var optional = Optional.of(usuarioModel);
		
		final var usuarioModelAlterado = getUsuarioModelMock(id, nomeAlterado, emailAlterado);
		
		// quando
		when(repository.findById(id)).thenReturn(optional);
		when(repository.save(usuarioModelAlterado)).thenReturn(usuarioModelAlterado);
		
		// então
		final var response = service.update(id, usuarioModelAlterado.toDTO());
		
		// validar o teste
		assertThat(response.getNome())
			.isEqualTo(usuarioModelAlterado.getNome());
		
		assertThat(response.getEmail())
		.isEqualTo(usuarioModelAlterado.getEmail());
		
		assertThat(response.getId())
		.isEqualTo(usuarioModelAlterado.getId());
	}
	
	@Test
	void updateWhenNotFindUser() {
		
		// dado que
		String id = "1";
		String nomeAlterado = "nomeAlterado";
		String emailAlterado = "emailAlterado";
		
		final var usuarioModelAlterado = getUsuarioModelMock(id, nomeAlterado, emailAlterado);
		Optional<UsuarioModel> optional = Optional.empty();

		// quando
		when(repository.findById(id)).thenReturn(optional);
		
		// então
		assertThrows(NaoAcheiException.class, 
					() -> service.update(id, 
							usuarioModelAlterado.toDTO())) ;
	}
	
	@Test
	void deleteWhenFindUser() {
		
		// dado que
		String id = "1";
		String nome = "nome";
		String email = "email";
		
		final var usuarioModel = getUsuarioModelMock(id, nome, email);
		final var optional = Optional.of(usuarioModel);
		
		// quando
		when(repository.findById(id)).thenReturn(optional);
		
		// então
		service.delete(id);
		
		// verificar se o teste deu certo
		verify(repository, times(1)).deleteById(id);
	}
	
	@Test
	void deleteWhenNotFindUser() {

		// dado que
		String id = "1";

		//final var usuarioModel = getUsuarioModelMock(id, nome, email);
		Optional<UsuarioModel> optional = Optional.empty();

		// quando
		when(repository.findById(id)).thenReturn(optional);

		// então
		assertThrows(NaoAcheiException.class, () -> service.delete(id));
	}
	
	@Test
	void findByNomeTest() {
		
		// dado que 
		
		String id = "1";
		String nome = "nome";
		String email = "email";
		
		final var listEntity = Arrays
				.asList(getUsuarioModelMock(id, nome,email));
		
		// quando
		when(repository.findByNome(nome)).thenReturn(listEntity);
		
		// então
		final var response = service.findByNome(nome);
		
		// verificar resultado do teste
		
		assertThat(response.get(0))
			.isEqualTo(listEntity.get(0).toDTO());
	}
	
	@Test
	void findByNomeContainsTest() {
		
		// dado que
		String id = "1";
		String nome = "nome";
		String email = "email";
		
		final var listEntity = Arrays.asList(getUsuarioModelMock(id, nome, email));
		
		//quando
		when(repository.findAll()).thenReturn(listEntity);
		
		//então
		final var response = service.findByNomeContains(nome);
		
		// verificar testes
		assertThat(response.get(0))
			.isEqualTo(listEntity.get(0).toDTO());
		
	}
	
	@Test
	void findByEmailTest() {
		// Dado que
		
		final var email = "email";
		final var page = 0;
		final var limit = 5;
		final var direction = Direction.ASC;
		final var orderBy = "id";
		final var pageRequest = 
				PageRequest.of(page, limit, direction, orderBy );
		

		final var pageUsuarioModel = getPageUsuarioModel();
		
		// Quando
		
		when(repository.findByEmail(email, pageRequest))
			.thenReturn(pageUsuarioModel);
		
		//então
		
		final var response = service.findByEmail(email, page, limit, orderBy, "ASC");
		
		// Verificar resultado
		
		assertThat(response.getContent().size()).isEqualTo(1);
		assertThat(response.getTotalElements()).isEqualTo(1L);
		
	}
	
	@Test
	void insertManyTest() {
		
		// dado que 
		final var times = 1;
		final var listEntity = Arrays
				.asList(getUsuarioModelMock(null, "Usuario 0","usuario@gmail.com"));
		
		// quando
		when(repository.saveAll(listEntity)).thenReturn(listEntity);
		doNothing().when(utils).mostrarMensagemNoConsole("Olá");
		
		//então
		service.insertMany(times);
			
		//verificar resultado
		verify(repository, times(1)).saveAll(listEntity);
		
	}
	
	@Test
	void deleteAllTest() {
		// dado que
		
		// quando
		doNothing().when(repository).deleteAll();
		
		//então
		service.deleteAll();
		
		// verificar resultado
		verify(repository, times(1)).deleteAll();
	}
	
	private UsuarioModel getUsuarioModelMock(String id, String nome, String email) {
		
		final var model = UsuarioModel
				.builder()
				.id(id)
				.nome(nome)
				.email(email)
				.build();

		return model;
	}
	
	private Page<UsuarioModel> getPageUsuarioModel(){
		
			Page<UsuarioModel> pageUsuarioModel = new Page<UsuarioModel>() {
			
				UsuarioModel model = UsuarioModel
						.builder()
						.id("1")
						.nome("nome")
						.email("email")
						.build();

				List<UsuarioModel> listEntity = Arrays.asList(model);
				
			@Override
			public Iterator<UsuarioModel> iterator() {
				return (Iterator<UsuarioModel>) listEntity;
			}
			
			@Override
			public Pageable previousPageable() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Pageable nextPageable() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean isLast() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isFirst() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean hasPrevious() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean hasContent() {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public Sort getSort() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getSize() {
				
				return listEntity.size();
			}
			
			@Override
			public int getNumberOfElements() {
				
				return listEntity.size();
			}
			
			@Override
			public int getNumber() {
				
				return 1;
			}
			
			@Override
			public List<UsuarioModel> getContent() {
				
				return listEntity;
			}
			
			@Override
			public <U> Page<U> map(Function<? super UsuarioModel, ? extends U> converter) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getTotalPages() {
			
				return 1;
			}
			
			@Override
			public long getTotalElements() {
				
				return listEntity.size();
			}
		};
		
		return pageUsuarioModel;
	}
	
}
