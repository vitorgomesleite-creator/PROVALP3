package controller;

import model.LivroModel;
import repository.LivroRepository;
import java.util.List;

public class LivroController {
    private LivroRepository livroRepository = new LivroRepository();

    public String salvar(LivroModel livro) {
        return livroRepository.salvar(livro);
    }

    public String editar(LivroModel livro) {
        return livroRepository.editar(livro);
    }

    public List<LivroModel> buscarTodos() {
        return livroRepository.buscarTodos();
    }

    public String remover(Long id) {
        LivroModel livro = livroRepository.buscarPorId(id);
        if (livro != null) {
            return livroRepository.remover(livro);
        }
        return "Livro não encontrado!";
    }
}
