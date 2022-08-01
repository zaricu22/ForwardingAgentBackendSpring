package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entities.Ugovor;

public interface UgovorRepository extends JpaRepository<Ugovor, Integer> {

}
