package app.controllers;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.entities.Korisnik;
import app.entities.Prevoznik;
import app.entities.Proizvodjac;
import app.entities.Uloga;
import app.repositories.KorisnikRepository;
import app.repositories.PrevoznikRepository;
import app.repositories.ProizvodjacRepository;
import app.repositories.UlogaRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	@Autowired
	KorisnikRepository kr;

	@Autowired
	UlogaRepository ur;

	@Autowired
	ProizvodjacRepository mr;

	@Autowired
	PrevoznikRepository cr;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Token login(String username, String password) {
		Korisnik u = kr.findByUsername(username);
		if (u != null && u.getPassword().equals(password)) {
			return new Token(randomString(), u.getUsername(), u.getUlogas().get(0).getNaziv(), u.getIdPreduzeca());
		}
		
		return null;
	}
	
	@RequestMapping(value = "/credExist", method = RequestMethod.POST)
	public boolean credExist(String username, String password) {
		Korisnik u = kr.findByUsername(username);
		if (u != null)
			return true;
		return false;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public boolean register(String username, String password, String role, String nazivFirme, String sediste,
			String email, String telefon) {
		Uloga u = ur.findByNaziv(role);
		Korisnik kor = kr.findByUsername(username);

		if (u != null && kor == null) {
			kor = new Korisnik();
			kor.setPassword(password);
			kor.setUsername(username);
			kor.addUloga(u);
			u.addKorisnik(kor);
		}

		if (role.equals("CARRIER")) {
			Prevoznik c = new Prevoznik();
			c.setNaziv(nazivFirme);
			c.setSediste(sediste);
			c.setEmail(email);
			c.setTelefon(telefon);
			cr.saveAndFlush(c);
			c = cr.findByNaziv(nazivFirme);
			if(c != null)
				kor.setIdPreduzeca(c.getIdPrevoznik());
		} else {
			Proizvodjac m = new Proizvodjac();
			m.setNaziv(nazivFirme);
			m.setSediste(sediste);
			m.setEmail(email);
			m.setTelefon(telefon);
			mr.saveAndFlush(m);
			m = mr.findByNaziv(nazivFirme);
			if(m != null)
				kor.setIdPreduzeca(m.getIdProizvodjac());
		}
		
		kr.saveAndFlush(kor);
		ur.saveAndFlush(u);

		return true;
	}

	private String randomString() {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 25;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedString;
	}
}
