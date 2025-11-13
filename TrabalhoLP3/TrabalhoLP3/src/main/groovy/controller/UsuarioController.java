package controller;

import model.UsuarioModel;
import repository.UsuarioRepository;
import java.util.List;

public class UsuarioController {
    private UsuarioRepository usuarioRepository = new UsuarioRepository();

    public String salvar(UsuarioModel usuario) {
        return usuarioRepository.salvar(usuario);
    }

    public String editar(UsuarioModel usuario) {
        return usuarioRepository.editar(usuario);
    }

    public List<UsuarioModel> buscarTodos() {
        return usuarioRepository.buscarTodos();
    }

    public String remover(Long id) {
        UsuarioModel usuario = usuarioRepository.buscarPorId(id);
        if (usuario != null) {
            return usuarioRepository.remover(usuario);
        }
        return "Usuário não encontrado!";
    }
}
