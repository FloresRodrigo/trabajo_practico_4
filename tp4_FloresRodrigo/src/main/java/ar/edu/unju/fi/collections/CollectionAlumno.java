package ar.edu.unju.fi.collections;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Alumno;

@Component
public class CollectionAlumno {

	private static List<Alumno> alumnos = new ArrayList<Alumno>();

	public static List<Alumno> getAlumnos() {

		if (alumnos.isEmpty()) {
			alumnos.add(new Alumno("123456789", "Maria", "Garcia", "garcia@gmail.com", "123456789",
					LocalDate.of(2000, 1, 1), "Calle 5 Manzana 5", "APU001"));
			alumnos.add(new Alumno("12345678", "Juan", "Martinez", "martinez@gmail.com", "12345678",
					LocalDate.of(1999, 1, 1), "Calle 8 Manzana 5", "APU010"));
			alumnos.add(new Alumno("1234567", "Ana", "Lopez", "lopez@gmail.com", "1234567",
					LocalDate.of(1998, 1, 1), "Calle 5 Manzana 3", "INF001"));
		}

		return alumnos;
	}

	public static void agregarAlumno(Alumno alumno) {

		alumnos.add(alumno);

	}

	public static void eliminarAlumno(String dniAlumno) {

		Iterator<Alumno> iterator = alumnos.iterator();

		while (iterator.hasNext()) {
			if (iterator.next().getDni().equals(dniAlumno)) {
				iterator.remove();
			}
		}
	}

	public static void modificarAlumno(Alumno alumno) {

		for (Alumno alumn : alumnos) {
			if (alumn.getDni().equals(alumno.getDni())) {

				alumn.setNombre(alumno.getNombre());
				alumn.setApellido(alumno.getApellido());
				alumn.setEmail(alumno.getEmail());
				alumn.setTelefono(alumno.getTelefono());
				alumn.setFechaNacimiento(alumno.getFechaNacimiento());
				alumn.setDomicilio(alumno.getDomicilio());
				alumn.setLu(alumno.getLu());
				break;
			}
		}
	}

	public static Alumno buscarAlumno(String dni) {

		Predicate<Alumno> filterDni = d -> d.getDni().equals(dni);
		Optional<Alumno> alumno = alumnos.stream().filter(filterDni).findFirst();

		if (alumno.isPresent()) {

			return alumno.get();
		} else {
			return null;
		}

	}

}