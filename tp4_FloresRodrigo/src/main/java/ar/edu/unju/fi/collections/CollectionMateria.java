package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Materia;

@Component
public class CollectionMateria {

	private static List<Materia> materias = new ArrayList<Materia>();

	public static List<Materia> getMaterias() {
		return materias;
	}

	public static void agregarMateria(Materia materia) {

		int ultimoCodigo = 0;
		for (Materia mate : materias) {
			if (mate.getCodigo() > ultimoCodigo) {
				ultimoCodigo = mate.getCodigo();
			}
		}
		materia.setCodigo(ultimoCodigo + 1);
		materias.add(materia);
	}

	public static void eliminarMateria(int codigoMateria) {
		Iterator<Materia> iterator = materias.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getCodigo() == codigoMateria) {
				iterator.remove();
			}
		}
	}

	public static void modificarMateria(Materia materia) {

		for (Materia mate : materias) {
			if (mate.getCodigo() == materia.getCodigo()) {
				mate.setNombre(materia.getNombre());
				mate.setCurso(materia.getCurso());
				mate.setCantidadHoras(materia.getCantidadHoras());
				mate.setModalidad(materia.getModalidad());
				mate.setDocente(materia.getDocente());
				mate.setCarrera(materia.getCarrera());

			}
		}
	}

	public static Materia buscarMateria(int codigo) {

		Predicate<Materia> filterCodigo = cod -> cod.getCodigo() == codigo;
		Optional<Materia> materia = materias.stream().filter(filterCodigo).findFirst();
		if (materia.isPresent()) {
			return materia.get();
		} else {
			return null;
		}
	}

}