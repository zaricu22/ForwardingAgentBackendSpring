package app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entities.Teret;

public interface TeretRepository extends JpaRepository<Teret, Integer> {
	
	List<Teret> findByProizvodjacIdProizvodjac(int id);

	// Funkcije su MySQL
	@Query("SELECT sum(t.tezina) FROM Teret t WHERE t.proizvodjac.idProizvodjac = :id and MONTH(t.isporuka.vremePolaska) = :month and YEAR(t.isporuka.vremePolaska) = :year")
	Integer goodsQuantityByMonth(Integer id, Integer month, Integer year);
	
	// Funkcije su MySQL
	@Query("SELECT sum(t.isporuka.cena) FROM Teret t WHERE t.proizvodjac.idProizvodjac = :id and MONTH(t.isporuka.vremePolaska) = :month and YEAR(t.isporuka.vremePolaska) = :year")
	Integer deliveryCostsByMonth(Integer id, Integer month, Integer year);
	
	@Query("SELECT count(t) FROM Teret t WHERE t.proizvodjac.idProizvodjac = :id and t.tip like :tip")
	Integer countCargoByType(Integer id, String tip);
}
