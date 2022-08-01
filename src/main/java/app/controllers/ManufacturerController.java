package app.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entities.Isporuka;
import app.entities.Kamion;
import app.entities.Prikolica;
import app.entities.Proizvodjac;
import app.entities.Teret;
import app.entities.Vozac;
import app.repositories.IsporukaRepository;
import app.repositories.KamionRepository;
import app.repositories.PrikolicaRepository;
import app.repositories.ProizvodjacRepository;
import app.repositories.TeretRepository;
import app.repositories.VozacRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "/manu")
public class ManufacturerController {
	@Autowired
	KamionRepository kamRep;
	@Autowired
	PrikolicaRepository prikRep;
	@Autowired
	IsporukaRepository ispRep;
	@Autowired
	VozacRepository vozRep;
	@Autowired
	ProizvodjacRepository proizRep;
	@Autowired
	TeretRepository terRep;
	
	@GetMapping(value = "/getManufacturer/{id}")
	public Proizvodjac getManufacturer(@PathVariable int id) {
		return proizRep.findById(id).get();
	}
	
	@GetMapping(value = "/cargoAll/{id}")
	public List<Teret> cargoAll(@PathVariable int id) {
		return terRep.findByProizvodjacIdProizvodjac(id);
	}
	
	@GetMapping(value = "/deliveryAll/{id}")
	public List<Isporuka> deliveryAll(@PathVariable int id) {
		return ispRep.findByProizvodjacIdProizvodjac(id);
	}
	
	@GetMapping(value = "/truckNum")
	public long truckNum() {
		return kamRep.count();
	}
	
	@GetMapping(value = "/truckPage")
	public List<Kamion> truckPage(Integer page, Integer perPage, String sortBy) {
		Pageable pageable = PageRequest.of(page, perPage, Sort.by(sortBy).ascending());
		return  kamRep.findAll(pageable).toList();
	}
	
	@GetMapping(value = "/trailerNum")
	public long trailerNum() {
		return prikRep.count();
	}
	
	@GetMapping(value = "/trailerPage")
	public List<Prikolica> trailerPage(Integer page, Integer perPage, String sortBy) {
		Pageable pageable = PageRequest.of(page, perPage, Sort.by(sortBy).ascending());
		return  prikRep.findAll(pageable).toList(); 
	}
	
	@GetMapping(value = "/driverAll")
	public List<Vozac> driverJobs() {
		return  vozRep.findAll(); 
	}
	
	@GetMapping(value = "/driverJobs")
	public List<Isporuka> driverJobs(Integer page, Integer perPage, String sortBy, Integer id) {
		Pageable pageable = PageRequest.of(page, perPage, Sort.by(sortBy).ascending());
		return  ispRep.findByDriver(id, pageable).toList(); 
	}
	
	@GetMapping(value = "/driverJobsNum/{id}")
	public Integer driverJobsNum(@PathVariable Integer id) {
		return  ispRep.countByDriver(id); 
	}
	
	@GetMapping(value = "/goodsQuantityByMonth/{id}")
	public List<Integer> goodsQuantityByMonth(@PathVariable int id) {
		LocalDate currDate = LocalDate.now();
		Integer currMonth = currDate.getMonthValue();
		Integer currYear = currDate.getYear();
		List<Integer> list = new ArrayList<Integer>(); 
		for (int i = 1; i <= currMonth; i++) {
			Integer localSum = terRep.goodsQuantityByMonth(id, i, currYear);
			if(localSum != null)
				list.add(localSum);
			else
				list.add(0);
		}
		return list;
	}
	
	@GetMapping(value = "/deliveryCostsByMonth/{id}")
	public List<Integer> deliveryCostsByMonth(@PathVariable int id) {
		LocalDate currDate = LocalDate.now();
		Integer currMonth = currDate.getMonthValue();
		Integer currYear = currDate.getYear();
		List<Integer> list = new ArrayList<Integer>(); 
		for (int i = 1; i <= currMonth; i++) {
			Integer localSum = terRep.deliveryCostsByMonth(id, i, currYear);
			if(localSum != null)
				list.add(localSum);
			else
				list.add(0);
		}
		return list;
	}
	
	@GetMapping(value = "/cargoByType/{id}")
	public List<Integer> cargoByType(@PathVariable int id) {
		List<Integer> list = new ArrayList<Integer>(); 
		list.add(terRep.countCargoByType(id, "običan"));
		list.add(terRep.countCargoByType(id, "težak"));
		list.add(terRep.countCargoByType(id, "lomljiv"));
		list.add(terRep.countCargoByType(id, "vredan"));
		list.add(terRep.countCargoByType(id, "opasan"));
		return list;
	}
}
