package br.edu.ifrs.series.web;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import br.edu.ifrs.series.dao.TutorDAO;
import br.edu.ifrs.series.dominio.Tutor;
import jakarta.validation.Valid;

@Controller
public class TutorPaginaController {
    private final TutorDAO dao;
    public TutorPaginaController(TutorDAO dao) {
        this.dao = dao;
    }
    
    @GetMapping("/paginas/tutores")
    public String listar(Model model) {
        model.addAttribute("tutores", dao.listarTodos());
        return "tutores/lista";
    }

    @GetMapping("/paginas/tutores/nova")
    public String formulario(Model model) {
        model.addAttribute("tutor", new Tutor());
        return "tutores/formulario";
    }

    @PostMapping("/paginas/tutores/nova")
    public String cadastrar(@Valid @ModelAttribute Tutor tutor, BindingResult resultado) {
    	if (resultado.hasErrors()) {
    		return "tutores/formulario";
    	}
        dao.salvar(tutor);
        return "redirect:/paginas/tutores";
    }
    
    
    // PAGE EDIT
    @GetMapping("/paginas/tutores/{id}/editar")
    public String edicao(@PathVariable Long id, Model model) {
        model.addAttribute("tutor", dao.buscarPorId(id));
        return "tutores/edicao";
    }
    
    // PAGE UPDATE 
    @PostMapping("/paginas/tutores/editar")
    public String atualizar(@Valid @ModelAttribute Tutor tutor, BindingResult resultado) {
    	if (resultado.hasErrors()) {
    		return "tutores/edicao";
    	}
        dao.atualizar(tutor);
        return "redirect:/paginas/tutores";
    }
    
    @PostMapping("/paginas/tutores/{id}/remover")
    public String remover(@PathVariable Long id) {
        dao.remover(id);
        return "redirect:/paginas/tutores";
    }
    
}