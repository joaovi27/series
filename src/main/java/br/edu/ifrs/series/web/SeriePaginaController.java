package br.edu.ifrs.series.web;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifrs.series.dao.SerieDAO;
import br.edu.ifrs.series.dominio.Serie;
import jakarta.validation.Valid;

@Controller
public class SeriePaginaController {
    private final SerieDAO dao;
    public SeriePaginaController(SerieDAO dao) {
        this.dao = dao;
    }
    
    @GetMapping("/paginas/series")
    public String listar(Model model) {
        model.addAttribute("series", dao.listarTodas());
        return "series/lista";
    }

    @GetMapping("/paginas/series/nova")
    public String formulario(Model model) {
        model.addAttribute("serie", new Serie());
        return "series/formulario";
    }

    @PostMapping("/paginas/series/nova")
    public String cadastrar(@Valid @ModelAttribute Serie serie, BindingResult resultado) {
    	if (resultado.hasErrors()) {
    		return "series/formulario";
    	}
        dao.salvar(serie);
        return "redirect:/paginas/series";
    }
    
    // PAGE EDIT
    @GetMapping("/paginas/series/{id}/editar")
    public String edicao(@PathVariable Long id, Model model) {
        model.addAttribute("serie", dao.buscarPorId(id));
        return "series/edicao";
    }
    
    // PAGE UPDATE 
    @PostMapping("/paginas/series/editar")
    public String atualizar(@Valid @ModelAttribute Serie serie, BindingResult resultado) {
    	if (resultado.hasErrors()) {
    		return "series/edicao";
    	}
        dao.atualizar(serie);
        return "redirect:/paginas/series";
    }
    
    // SERIE REMOVE
    @PostMapping("/paginas/series/{id}/remover")
    public String remover(@PathVariable Long id) {
        dao.remover(id);
        return "redirect:/paginas/series";
    }
    
}