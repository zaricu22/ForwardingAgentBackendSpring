package app.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entities.Isporuka;
import app.entities.Kamion;
import app.entities.Prevoznik;
import app.entities.Prikolica;
import app.entities.Vozac;
import app.repositories.IsporukaRepository;
import app.repositories.KamionRepository;
import app.repositories.PrevoznikRepository;
import app.repositories.PrikolicaRepository;
import app.repositories.VozacRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "/carr")
public class CarrierController {
	@Autowired
	KamionRepository kamRep;
	@Autowired
	PrikolicaRepository prikRep;
	@Autowired
	VozacRepository vozRep;
	@Autowired
	PrevoznikRepository prevRep;
	@Autowired
	IsporukaRepository ispRep;
	
	@GetMapping(value = "/getCarrier/{id}")
	public Prevoznik getCarrier(@PathVariable int id) {
		return prevRep.findById(id).get();
	}
	
	@GetMapping(value = "/deliveryAll/{id}")
	public List<Isporuka> deliveryAll(@PathVariable int id) {
		return ispRep.findByCarrier(id);
	}
	
	@GetMapping(value = "/truckNum")
	public long truckNum(Integer idPrevoznik) {
		return kamRep.countByPrevoznikIdPrevoznik(idPrevoznik);
	}
	
	@GetMapping(value = "/truckAll/{idPrevoznik}")
	public List<Kamion> truckAll(@PathVariable Integer idPrevoznik) {
		return  kamRep.findByPrevoznikIdPrevoznik(idPrevoznik);
	}
	
	@GetMapping(value = "/truckPage")
	public List<Kamion> truckPage(Integer page, Integer perPage, String sortBy, Integer idPrevoznik) {
		Pageable pageable = PageRequest.of(page, perPage, Sort.by(sortBy).ascending());
		return  kamRep.findByPrevoznikIdPrevoznik(idPrevoznik, pageable).toList();
	}
	
	@GetMapping(value = "/trailerAll/{idPrevoznik}")
	public List<Prikolica> trailerAll(@PathVariable Integer idPrevoznik) {
		return  prikRep.findByPrevoznikIdPrevoznik(idPrevoznik);
	}
	
	@GetMapping(value = "/trailerNum")
	public long trailerNum(Integer idPrevoznik) {
		return prikRep.countByPrevoznikIdPrevoznik(idPrevoznik);
	}
	
	@GetMapping(value = "/trailerPage")
	public List<Prikolica> trailerPage(Integer page, Integer perPage, String sortBy, Integer idPrevoznik) {
		Pageable pageable = PageRequest.of(page, perPage, Sort.by(sortBy).ascending());
		return  prikRep.findByPrevoznikIdPrevoznik(idPrevoznik, pageable).toList();  
	}
	
	@GetMapping(value = "/driverNum")
	public long driverNum(Integer idPrevoznik) {
		return vozRep.countByPrevoznikIdPrevoznik(idPrevoznik);
	}
	
	@GetMapping(value = "/driverPage")
	public List<Vozac> driverPage(Integer page, Integer perPage, String sortBy, Integer idPrevoznik) {
		Pageable pageable = PageRequest.of(page, perPage, Sort.by(sortBy).ascending());
		System.out.println(vozRep.findByPrevoznikIdPrevoznik(idPrevoznik, pageable).toList().get(0));
		return  vozRep.findByPrevoznikIdPrevoznik(idPrevoznik, pageable).toList();  
	}
	
	@PostMapping(value = "/createTruck/{idPrevoznik}")
	public Kamion createTruck(@RequestBody Kamion kamion, @PathVariable int idPrevoznik) {
		Prevoznik p = prevRep.findById(idPrevoznik).get();
		kamion.setPrevoznik(p);
		return kamRep.save(kamion);
	}
	
	@PutMapping(value = "/updateTruck")
	public Kamion updateTruck(@RequestBody Kamion kamion) {
		return kamRep.save(kamion);
	}
	
	@DeleteMapping(value = "/deleteTruck/{id}")
	public boolean deleteTruck(@PathVariable int id) {
		kamRep.deleteById(id);
		return true;
	}
	
	@PostMapping(value = "/createTrailer/{idPrevoznik}")
	public Prikolica createTrailer(@RequestBody Prikolica prikolica, @PathVariable int idPrevoznik) {
		Prevoznik p = prevRep.findById(idPrevoznik).get();
		prikolica.setPrevoznik(p);
		return prikRep.save(prikolica);
	}
	
	@PutMapping(value = "/updateTrailer")
	public Prikolica updateTrailer(@RequestBody Prikolica prikolica) {
		return prikRep.save(prikolica);
	}
	
	@DeleteMapping(value = "/deleteTrailer/{id}")
	public boolean deleteTrailer(@PathVariable int id) {
		prikRep.deleteById(id);
		return true;
	}
	
	@PostMapping(value = "/createDriver/{idPrevoznik}")
	public Vozac createDriver(@RequestBody Vozac vozac, @PathVariable int idPrevoznik) {
		Prevoznik p = prevRep.findById(idPrevoznik).get();
		vozac.setPrevoznik(p);
		return vozRep.save(vozac);
	}
	
	@PutMapping(value = "/updateDriver")
	public Vozac updateDriver(@RequestBody Vozac vozac) {
		return vozRep.save(vozac);
	}
	
	@DeleteMapping(value = "/deleteDriver/{id}")
	public boolean deleteDriver(@PathVariable int id) {
		vozRep.deleteById(id);
		return true;
	}
	
	@GetMapping(value = "/truckAge/{id}")
	public List<Integer> truckAge(@PathVariable int id) {
		List<Integer> ageList = new ArrayList<Integer>(); 
		ageList.add(kamRep.countByAge(id, 0, 2));
		ageList.add(kamRep.countByAge(id, 3, 5));
		ageList.add(kamRep.countByAge(id, 6, 10));
		ageList.add(kamRep.countByAge(id, 11, 15));
		ageList.add(kamRep.countByAge(id, 16, 20));
		return ageList;
	}
	
	@GetMapping(value = "/deliverySuccess/{id}")
	public List<Integer> deliverySuccess(@PathVariable int id) {
		List<Integer> list = new ArrayList<Integer>(); 
		list.add(ispRep.countSuccessfulDeliveries(id, "završena"));
		list.add(ispRep.countSuccessfulDeliveries(id, "zakasnela"));
		list.add(ispRep.countSuccessfulDeliveries(id, "otkazana"));
		return list;
	}
	
	@GetMapping(value = "/salaryByMonth/{id}")
	public List<Integer> salaryByMonth(@PathVariable int id) {
		LocalDate currDate = LocalDate.now();
		Integer currMonth = currDate.getMonthValue();
		Integer currYear = currDate.getYear();
		List<Integer> list = new ArrayList<Integer>(); 
		for (int i = 1; i <= currMonth; i++) {
			Integer localSum = ispRep.salaryByMonth(id, i, currYear);
			if(localSum != null)
				list.add(localSum);
			else
				list.add(0);
		}
		return list;
	}
	
	@GetMapping(value = "/jobsByMonth/{id}")
	public List<Integer> jobsByMonth(@PathVariable int id) {
		LocalDate currDate = LocalDate.now();
		Integer currMonth = currDate.getMonthValue();
		Integer currYear = currDate.getYear();
		List<Integer> list = new ArrayList<Integer>(); 
		for (int i = 1; i <= currMonth; i++) {
			Integer localSum = ispRep.jobsByMonth(id, i, currYear);
			if(localSum != null)
				list.add(localSum);
			else
				list.add(0);
		}
		return list;
	}
	
	
}
