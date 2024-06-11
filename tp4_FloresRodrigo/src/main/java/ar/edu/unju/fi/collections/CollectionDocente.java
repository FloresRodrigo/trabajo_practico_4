package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Docente;

@Component
public class CollectionDocente {
	private static List<Docente> docentes = new ArrayList<Docente>();

	public static List<Docente> getDocentes() {

		if (docentes.isEmpty()) {
			docentes.add(new Docente(1, "Juan", "Carlos", "juancarlos@gmail.com", "123456789"));
			docentes.add(new Docente(2, "Perez", "Ibarra", "perezibarra@gmail.com", "12345678"));
			docentes.add(new Docente(3, "Hector", "Liberatori", "liberatori@gmail.com", "1234567"));
		}
		return docentes;
	}

	public static void agregarDocente(Docente docente) {

		int ultimoCodigo = 0;
		for (Docente docen : docentes) {
			if (docen.getLegajo() > ultimoCodigo) {
				ultimoCodigo = docen.getLegajo();
			}
		}
		docente.setLegajo(ultimoCodigo + 1);
		docentes.add(docente);
	}

	public static void eliminarDocente(int legajoDocente) {
		Iterator<Docente> iterator = docentes.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getLegajo() == legajoDocente) {
				iterator.remove();
			}
		}
	}

	public static void modificarDocente(Docente docente) {

		for (Docente docen : docentes) {
			if (docen.getLegajo() == docente.getLegajo()) {
				docen.setNombre(docente.getNombre());
				docen.setApellido(docente.getApellido());
				docen.setEmail(docente.getEmail());
				docen.setTelefono(docente.getTelefono());

			}
		}
	}

	public static Docente buscarDocente(int legajo) {

		Predicate<Docente> filterLegajo = l -> l.getLegajo() == legajo;
		Optional<Docente> docente = docentes.stream().filter(filterLegajo).findFirst();
		if (docente.isPresent()) {
			return docente.get();
		} else {
			return null;
		}
	}

}