package app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import app.entities.Vozac;

public interface VozacRepository extends JpaRepository<Vozac, Integer> {
	
	long count();
	
	long countByPrevoznikIdPrevoznik(Integer idPrevoznik);
	
	List<Vozac> findAll();
	
	Page<Vozac> findAll(Pageable pageable);
	
	List<Vozac> findByPrevoznikIdPrevoznik(int id);
	
	Page<Vozac> findByPrevoznikIdPrevoznik(int idPrevoznik, Pageable pageable);
}
