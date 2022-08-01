package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entities.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, String> {
	Korisnik findByUsername(String username);
}
