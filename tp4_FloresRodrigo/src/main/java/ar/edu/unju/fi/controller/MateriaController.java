package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.collections.CollectionCarrera;
import ar.edu.unju.fi.collections.CollectionDocente;
import ar.edu.unju.fi.collections.CollectionMateria;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;

@Controller
@RequestMapping("/materia")
public class MateriaController {

	@Autowired
	private Materia materia;

	@Autowired
	private Docente docente;

	@Autowired
	private Carrera carrera;

	@GetMapping("/listado")
	public String getMateriasList(Model model) {

		model.addAttribute("materias", CollectionMateria.getMaterias());
		model.addAttribute("titulo", "Materias");

		return "materias";
	}

	@GetMapping("/nueva")
	public String getNuevaMateriaPage(Model model) {

		boolean edicion = false;
		model.addAttribute("materia", materia);
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nueva Materia");

		return "materia";
	}

	@PostMapping("/guardar")
	public ModelAndView guardarMateria(@ModelAttribute("materia") Materia materia) {

		docente = CollectionDocente.buscarDocente(materia.getDocente().getLegajo());
		carrera = CollectionCarrera.buscarCarrera(materia.getCarrera().getCodigo());
		materia.setDocente(docente);
		materia.setCarrera(carrera);

		ModelAndView modelView = new ModelAndView("materias");
		CollectionMateria.agregarMateria(materia);
		modelView.addObject("materias", CollectionMateria.getMaterias());
		return modelView;
	}

	@GetMapping("/modificar/{codigo}")
	public String getEditarMateria(Model model, @PathVariable(value = "codigo") int codigo) {

		boolean edicion = true;
		Materia materiaEncontrada = new Materia();
		materiaEncontrada = CollectionMateria.buscarMateria(codigo);
		model.addAttribute("edicion", edicion);
		model.addAttribute("materia", materiaEncontrada);
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("titulo", "Modificar Carrera");

		return "materia";
	}

	@PostMapping("/modificar")
	public String modificarMateria(@ModelAttribute("materia") Materia materia) {

		docente = CollectionDocente.buscarDocente(materia.getDocente().getLegajo());
		carrera = CollectionCarrera.buscarCarrera(materia.getCarrera().getCodigo());
		materia.setDocente(docente);
		materia.setCarrera(carrera);

		CollectionMateria.modificarMateria(materia);

		return "redirect:/materia/listado";
	}

	@GetMapping("/eliminar/{codigo}")
	public String eliminarCarrera(@PathVariable(value = "codigo") int codigo) {

		CollectionMateria.eliminarMateria(codigo);

		return "redirect:/materia/listado";

	}

}