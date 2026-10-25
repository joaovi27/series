package br.edu.ifrs.series.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import br.edu.ifrs.series.dao.SerieDAO;
import br.edu.ifrs.series.dominio.Serie;

@RestController
public class SerieController {
    private final SerieDAO dao;
    public SerieController(SerieDAO dao) {
        this.dao = dao;
    }
    

    @GetMapping("/series")
    public List<Serie> listar() {
        return dao.listarTodas();
    }


    @PostMapping("/series")
    @ResponseStatus(HttpStatus.CREATED)
    public Serie cadastrar(@RequestBody Serie serie) {
        dao.salvar(serie);
        return serie;
    }
    
    @GetMapping("/series/{id}")
    public Serie buscarPorId(@PathVariable Long id) {
        return dao.buscarPorId(id);
    }
    
    @PutMapping("/series/{id}")
    public Serie atualizar(@PathVariable Long id, @RequestBody Serie dados) {
        Serie serie = dao.buscarPorId(id);
        serie.setNome(dados.getNome());
        serie.setPlataforma(dados.getPlataforma());
        serie.setAnoLancamento(dados.getAnoLancamento());
        serie.setGenero(dados.getGenero());
        dao.atualizar(serie);
        return serie;
    }
    
    @DeleteMapping("/series/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        dao.remover(id);
    }

}
