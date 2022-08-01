package app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entities.Isporuka;

public interface IsporukaRepository extends JpaRepository<Isporuka, Integer>{
	
	List<Isporuka> findByProizvodjacIdProizvodjac(int id);
	
	@Query("SELECT i FROM Isporuka i WHERE i.ugovor.prevoznik.idPrevoznik = :id")
	List<Isporuka> findByCarrier(int id);
	
	@Query("SELECT i FROM Isporuka i WHERE i.ugovor.vozac.idVozac = :id")
	Page<Isporuka> findByDriver(Integer id, Pageable pageable);
	
	@Query("SELECT count(i) FROM Isporuka i WHERE i.ugovor.vozac.idVozac = :id")
	Integer countByDriver(Integer id);
	
	@Query("SELECT count(i) FROM Isporuka i WHERE i.ugovor.prevoznik.idPrevoznik = :id and i.statusDostave like :status")
	Integer countSuccessfulDeliveries(Integer id, String status);
	
	// Funkcije su MySQL
	@Query("SELECT sum(i.cena) FROM Isporuka i WHERE i.ugovor.prevoznik.idPrevoznik = :id and MONTH(i.vremePolaska) = :month and YEAR(i.vremePolaska) = :year")
	Integer salaryByMonth(Integer id, Integer month, Integer year);
	
	// Funkcije su MySQL
	@Query("SELECT count(i) FROM Isporuka i WHERE i.ugovor.prevoznik.idPrevoznik = :id and MONTH(i.vremePolaska) = :month and YEAR(i.vremePolaska) = :year")
	Integer jobsByMonth(Integer id, Integer month, Integer year);
}
