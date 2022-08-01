package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entities.Uloga;

public interface UlogaRepository extends JpaRepository<Uloga, Integer> {
	Uloga findByNaziv(String naziv);
}
