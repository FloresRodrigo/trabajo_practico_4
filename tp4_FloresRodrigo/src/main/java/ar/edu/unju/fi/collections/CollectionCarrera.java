package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;

@Component
public class CollectionCarrera {

	private static List<Carrera> carreras = new ArrayList<Carrera>();

	public static List<Carrera> getCarreras() {
		if (carreras.isEmpty()) {
			carreras.add(new Carrera(1, "APU", (byte) 3, true));
			carreras.add(new Carrera(2, "Ingenieria en sistemas", (byte) 5, true));
			carreras.add(new Carrera(3, "Ingenieria informatica", (byte) 2, false));
		}
		return carreras;
	}

	public static void agregarCarrera(Carrera carrera) {

		int ultimoCodigo = 0;
		for (Carrera carre : carreras) {
			if (carre.getCodigo() > ultimoCodigo) {
				ultimoCodigo = carre.getCodigo();
			}
		}
		carrera.setCodigo(ultimoCodigo + 1);
		carreras.add(carrera);

	}

	public static void eliminarCarrera(int codigoCarrera) {
		Iterator<Carrera> iterator = carreras.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getCodigo() == codigoCarrera) {
				iterator.remove();
			}
		}
	}

	public static void modificarCarrera(Carrera carrera) {

		for (Carrera carre : carreras) {
			if (carre.getCodigo() == carrera.getCodigo()) {
				carre.setNombre(carrera.getNombre());
				carre.setCantidadAnios(carrera.getCantidadAnios());
				carre.setEstado(carrera.isEstado());

			} else {
				System.out.println("No se encontro la carrera");
			}
		}
	}

	public static Carrera buscarCarrera(int codigo) {

		Predicate<Carrera> filterCodigo = carre -> carre.getCodigo() == codigo;
		Optional<Carrera> carrera = carreras.stream().filter(filterCodigo).findFirst();
		if (carrera.isPresent()) {
			return carrera.get();
		} else {
			return null;
		}
	}

}