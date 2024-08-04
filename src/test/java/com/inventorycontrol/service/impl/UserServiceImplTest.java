package com.inventorycontrol.service.impl;

import com.inventorycontrol.exception.DataAlreadyRegisteredException;
import com.inventorycontrol.model.ProfileModel;
import com.inventorycontrol.model.UserModel;
import com.inventorycontrol.repository.UserRepository;
import jakarta.persistence.NoResultException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private PasswordEncoder passwordEncoderMock;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll_ContainsData() {
        // Criando um usuário simulado que será retornado pelo mock do repository
        UserModel user = new UserModel("Marquin05TF", "zMarcust.st@gmail.com");

        // Adicionando a lista
        List<UserModel> users = new ArrayList<>();
        users.add(user);

        // Define o comportamento esperado (retornar a lista populada)
        when(userRepositoryMock.findAll()).thenReturn(users);

        // Chama o método findAll e verifica o resultado
        List<UserModel> result = userService.findAll();

        // Verifica se a lista retornada não está vazia
        assertFalse(result.isEmpty());

        // Verifica que o método findAll foi chamado apenas uma vez
        verify(userRepositoryMock, times(1)).findAll();
    }

    @Test
    public void testFindAll_Empty() {
        // Adicionando a lista
        List<UserModel> users = new ArrayList<>();

        // Define o comportamento esperado (retornar a lista populada)
        when(userRepositoryMock.findAll()).thenReturn(users);

        // Chama o método findAll e verifica o resultado
        List<UserModel> result = userService.findAll();

        // Verifica se a lista retornada não está vazia
        assertTrue(result.isEmpty());

        // Verifica que o método findAll foi chamado apenas uma vez
        verify(userRepositoryMock, times(1)).findAll();
    }

    @Test
    public void testFindById_UserExists() {
        // Criando um UUID válido para buscar o usuário
        UUID userId = UUID.fromString("e0b56c9e-7544-4508-8b57-099be8a98ccc");

        // Criando um usuário simulado que será retornado pelo mock do repository
        UserModel expectedUser = new UserModel("Marquin05TF", "zMarcust.st@gmail.com");
        when(userRepositoryMock.findById(userId)).thenReturn(Optional.of(expectedUser));

        // Chamando o método findById do UserServiceImpl
        UserModel actualUser = userService.findById(userId);

        // Verificando se o usuário retornado é o mesmo que foi simulado
        assertEquals(expectedUser, actualUser);
    }

    @Test(expected = NoResultException.class)
    public void testFindById_UserNotFound() {
        // Criando um UUID que não existe no repositório
        UUID userId = UUID.randomUUID();

        // Simulando que o repository não encontra nenhum usuário com esse UUID
        when(userRepositoryMock.findById(userId)).thenReturn(Optional.empty());

        // Chamando o método findById deve lançar a exceção NoResultException
        userService.findById(userId);
    }

    @Test
    public void testSaveUser_Success() {
        // Criando um usuário para salvar
        UserModel userToSave = new UserModel("UserTest", "user.test@example.com", "123456", "123456789");

        // Simulando que os métodos de verificação de existência retornem falso
        when(userRepositoryMock.existsByLogin(userToSave.getLogin())).thenReturn(false);
        when(userRepositoryMock.existsByEmail(userToSave.getEmail())).thenReturn(false);
        when(userRepositoryMock.existsByCpf(userToSave.getCpf())).thenReturn(false);

        // Simulando a codificação da senha
        when(passwordEncoderMock.encode(anyString())).thenReturn("encodedPassword");

        // Simulando o salvamento do usuário
        when(userRepositoryMock.save(any(UserModel.class))).thenReturn(userToSave);

        // Chama o método save do UserServiceImpl
        UserModel savedUser = userService.save(userToSave);

        // Verifica se o método save foi chamado corretamente no userRepositoryMock
        verify(userRepositoryMock, times(1)).save(userToSave);

        // Verifica se a senha foi corretamente codificada
        assertEquals("encodedPassword", savedUser.getPassword());
    }

    @Test(expected = DataAlreadyRegisteredException.class)
    public void testSaveUser_DuplicateLogin() {
        // Criando um usuário com um login que já existe
        UserModel userToSave = new UserModel("UserTest", "user.test@example.com", "123456", "123456789");

        // Simulando que existsByLogin retorne true
        when(userRepositoryMock.existsByLogin(anyString())).thenReturn(true);

        // Chamando o método save deve lançar a exceção DataAlreadyRegisteredException
        userService.save(userToSave);
    }

    @Test
    public void testUpdateUser_Success() {
        // Criando um UUID válido para atualizar o usuário
        UUID userId = UUID.randomUUID();

        // Criando um usuário simulado que será retornado pelo mock do repository
        UserModel existingUser = new UserModel(userId, "UserTest", "user.test@example.com", "123456", "123456789");

        // Simulando que o repository encontra um usuário com esse UUID
        when(userRepositoryMock.findById(userId)).thenReturn(Optional.of(existingUser));

        // Criando um novo modelo com os dados atualizados
        UserModel updatedUserModel = new UserModel("UserTest Updated", "user.test.updated@example.com", "654321", "987654321");

        // Simulando a codificação da nova senha
        when(passwordEncoderMock.encode(updatedUserModel.getPassword())).thenReturn("encodedUpdatedPassword");

        // Simulando o salvamento do usuário atualizado
        when(userRepositoryMock.save(any(UserModel.class))).thenReturn(updatedUserModel);

        // Chama o método update do UserServiceImpl
        UserModel updatedUser = userService.update(userId, updatedUserModel);

        // Verifica se o método findById foi chamado corretamente no userRepositoryMock
        verify(userRepositoryMock, times(1)).findById(userId);

        // Verifica se o método save foi chamado corretamente no userRepositoryMock com o usuário atualizado
        verify(userRepositoryMock, times(1)).save(updatedUserModel);

        // Verifica se a senha foi corretamente codificada
        assertEquals("encodedUpdatedPassword", updatedUser.getPassword());
    }

    @Test(expected = NoResultException.class)
    public void testUpdateUser_UserNotFound() {
        // Criando um UUID que não existe no repositório
        UUID userId = UUID.randomUUID();

        // Criando um usuário simulado para atualizar
        UserModel updatedUserModel = new UserModel("UserTest Updated", "user.test.updated@example.com", "654321", "987654321");

        // Simulando que o repository não encontra nenhum usuário com esse UUID
        when(userRepositoryMock.findById(userId)).thenReturn(Optional.empty());

        // Chamando o método update deve lançar a exceção NoResultException
        userService.update(userId, updatedUserModel);
    }

    @Test(expected = DataAlreadyRegisteredException.class)
    public void testUpdateUser_DuplicatePassword() {
        // Criando um UUID válido para atualizar o usuário
        UUID userId = UUID.randomUUID();

        // Criando um usuário simulado que será retornado pelo mock do repository
        UserModel existingUser = new UserModel(userId, "UserTest", "user.test@example.com", "123456", "123456789");

        // Simulando que o repository encontra um usuário com esse UUID
        when(userRepositoryMock.findById(userId)).thenReturn(Optional.of(existingUser));

        // Criando um novo modelo com a mesma senha do usuário existente
        UserModel updatedUserModel = new UserModel("UserTest Updated", "user.test.updated@example.com", "123456", "987654321");

        // Chamando o método update deve lançar a exceção DataAlreadyRegisteredException
        userService.update(userId, updatedUserModel);
    }

    @Test
    public void testDeleteUser_Success() {
        // Criando um UUID válido para deletar o usuário
        UUID userId = UUID.randomUUID();

        // Criando um usuário simulado que será retornado pelo mock do repository
        UserModel existingUser = new UserModel(userId, "UserTest", "user.test@example.com", "123456", "123456789");

        // Simulando que o repository encontra um usuário com esse UUID
        when(userRepositoryMock.findById(userId)).thenReturn(Optional.of(existingUser));

        // Chamando o método delete do UserServiceImpl
        UUID deletedUserId = userService.delete(userId);

        // Verifica se o método findById foi chamado corretamente no userRepositoryMock
        verify(userRepositoryMock, times(1)).findById(userId);

        // Verifica se o método delete foi chamado corretamente no userRepositoryMock com o usuário encontrado
        verify(userRepositoryMock, times(1)).delete(existingUser);

        // Verifica se o ID do usuário deletado é o mesmo que foi passado
        assertEquals(userId, deletedUserId);
    }

    @Test(expected = NoResultException.class)
    public void testDeleteUser_UserNotFound() {
        // Criando um UUID que não existe no repositório
        UUID userId = UUID.randomUUID();

        // Simulando que o repository não encontra nenhum usuário com esse UUID
        when(userRepositoryMock.findById(userId)).thenReturn(Optional.empty());

        // Chamando o método delete deve lançar a exceção NoResultException
        userService.delete(userId);
    }

}
