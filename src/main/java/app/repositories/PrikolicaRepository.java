package app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import app.entities.Prikolica;

public interface PrikolicaRepository extends JpaRepository<Prikolica, Integer> {
	
	long count();
	
	long countByPrevoznikIdPrevoznik(Integer idPrevoznik);
	
	List<Prikolica> findAll();
	
	Page<Prikolica> findAll(Pageable pageable);
	
	List<Prikolica> findByPrevoznikIdPrevoznik(int id);
	
	Page<Prikolica> findByPrevoznikIdPrevoznik(int idPrevoznik, Pageable pageable);
	
}
