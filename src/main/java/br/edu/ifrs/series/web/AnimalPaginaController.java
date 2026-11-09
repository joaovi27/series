package br.edu.ifrs.series.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.edu.ifrs.series.dao.AnimalDAO;
import br.edu.ifrs.series.dao.TutorDAO;
import br.edu.ifrs.series.dominio.Animal;
import br.edu.ifrs.series.dominio.Especie;
import br.edu.ifrs.series.dominio.Porte;
import br.edu.ifrs.series.dominio.Tutor;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/paginas/animais")
public class AnimalPaginaController {

    private final AnimalDAO animalDAO;
    private final TutorDAO tutorDAO;

    public AnimalPaginaController(AnimalDAO animalDAO, TutorDAO tutorDAO) {
        this.animalDAO = animalDAO;
        this.tutorDAO = tutorDAO;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("animais", animalDAO.listarTodos());
        return "animais/lista";
    }

    private void carregarOpcoes(Model model) {
        model.addAttribute("especies", Especie.values());
        model.addAttribute("portes", Porte.values());
        model.addAttribute("tutores", tutorDAO.listarTodos());
    }

    @GetMapping("/nova")
    public String novo(Model model) {
        model.addAttribute("animal", new Animal());
        carregarOpcoes(model);
        return "animais/formulario";
    }

    @PostMapping("/nova")
    public String cadastrar(
            @Valid @ModelAttribute("animal") Animal animal,
            BindingResult resultado,
            Model model) {

        verificarTutor(animal, resultado);

        if (resultado.hasErrors()) {
            carregarOpcoes(model);
            return "animais/formulario";
        }

        animalDAO.salvar(animal);
        return "redirect:/paginas/animais";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {

        Animal animal = animalDAO.buscarPorId(id);

        if (animal == null) {
            return "redirect:/paginas/animais";
        }

        model.addAttribute("animal", animal);
        carregarOpcoes(model);

        return "animais/edicao";
    }

    @PostMapping("/editar")
    public String atualizar(
            @Valid @ModelAttribute("animal") Animal animal,
            BindingResult resultado,
            Model model) {

        verificarTutor(animal, resultado);

        if (resultado.hasErrors()) {
            carregarOpcoes(model);
            return "animais/edicao";
        }

        if (animal.getId() == null ||
            animalDAO.buscarPorId(animal.getId()) == null) {
            return "redirect:/paginas/animais";
        }

        animalDAO.atualizar(animal);
        return "redirect:/paginas/animais";
    }

    private void verificarTutor(
            Animal animal,
            BindingResult resultado) {

        if (animal.getTutor() == null ||
            animal.getTutor().getId() == null) {

            resultado.rejectValue(
                "tutor",
                "tutor",
                "Selecione um tutor."
            );
            return;
        }

        Tutor tutor = tutorDAO.buscarPorId(
            animal.getTutor().getId()
        );

        if (tutor == null) {
            resultado.rejectValue(
                "tutor",
                "tutor",
                "Selecione um tutor válido."
            );
        } else {
            animal.setTutor(tutor);
        }
    }

    @PostMapping("/{id}/remover")
    public String remover(@PathVariable Long id) {
        animalDAO.remover(id);
        return "redirect:/paginas/animais";
    }
}