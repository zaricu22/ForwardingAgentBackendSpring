package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entities.Prevoznik;

public interface PrevoznikRepository extends JpaRepository<Prevoznik, Integer> {
	Prevoznik findByNaziv(String naziv);
}
