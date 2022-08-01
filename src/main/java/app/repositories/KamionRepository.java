package app.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entities.Kamion;

public interface KamionRepository extends JpaRepository<Kamion, Integer> {
	
	long count();
	
	long countByPrevoznikIdPrevoznik(Integer idPrevoznik);
	
	Page<Kamion> findAll(Pageable pageable);
	
	List<Kamion> findByPrevoznikIdPrevoznik(int id);
	
	Page<Kamion> findByPrevoznikIdPrevoznik(int idPrevoznik, Pageable pageable);
	
	@Query("select count(k) from Kamion k where k.prevoznik.idPrevoznik = :idPrevoznik and (2020 - k.godinaProizvodnje) >= :from and (2020 - k.godinaProizvodnje) <= :to")
	Integer countByAge(int idPrevoznik, int from, int to);

}
