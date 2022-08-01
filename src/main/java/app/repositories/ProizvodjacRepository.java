package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entities.Proizvodjac;

public interface ProizvodjacRepository extends JpaRepository<Proizvodjac, Integer> {
	Proizvodjac findByNaziv(String naziv);
}
