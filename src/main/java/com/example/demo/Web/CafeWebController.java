package com.example.demo.Web;

import java.io.OutputStream;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DAO.ConsommationRepository;
import com.example.demo.DAO.ProgrammeurRepository;

import com.example.demo.model.Consommation;
import com.example.demo.model.Programmeur;
import com.example.demo.util.PdfBox;

import io.jsonwebtoken.io.IOException;
//API REST
@RestController
public class CafeWebController {

	@Autowired
	private ConsommationRepository consommationRepository;
	@Autowired
	private ProgrammeurRepository programmeurRepository;

	// Recuperer les consommations
	@GetMapping(path = "/consommations")
	public List<Consommation> listConsommation() {
		return consommationRepository.findAll();
	}

	// Ajouter une consommation
	@PostMapping(path = "consommations")
	public Consommation save(@RequestBody @Valid Consommation c)
			throws SQLIntegrityConstraintViolationException, MethodArgumentNotValidException {

		return consommationRepository.save(c);
	}

	// recuperer les programmeurs
	@GetMapping(path = "/programmeurs")
	public List<Programmeur> listProgrammeur() {
		return programmeurRepository.findAll();
	}

	// Ajouter un programmeur
	@PostMapping(path = "/programmeurs")
	public Programmeur save(@RequestBody @Valid Programmeur p) throws Exception {
		if (programmeurRepository.findByNomAndPrenom(p.getNom(), p.getPrenom()) != null) {

			throw new Exception("Ce programmeur existe Deja");
		} else {
			return programmeurRepository.save(p);

		}
	}

	// filter les consommations par semaine
	@GetMapping(path = "/consommations/{num_semaine}")
	public List<Consommation> listConsoparSem(@PathVariable("num_semaine") int num_semaine) {
		return consommationRepository.findByNumSemaine(num_semaine);
	}

	// supprimer une consommation
	@DeleteMapping(path = "/consommations/{id}")
	public String supprimerConsommation(@PathVariable("id") int idconsommation) {
		consommationRepository.deleteById(idconsommation);
		return "Consommation ,id : " + idconsommation + "  supprimé";
	}

	// supprimer un programmeur
	@DeleteMapping(path = "/programmeurs/{id}")
	public String supprimerProgrammeur(@PathVariable("id") int idprogrammeur) {
		programmeurRepository.deleteById(idprogrammeur);
		return "Programmeur ,id : " + idprogrammeur + "  supprimé";
	}
    //creer un pdf des consommations pour une semaine donnee
	@GetMapping(path = "/monPdf/{num_semaine}")
	public OutputStream getPdf(HttpServletResponse response, @PathVariable("num_semaine") int num_semaine)
			throws IOException, java.io.IOException, COSVisitorException {

		List<Consommation> consommations = consommationRepository.findByNumSemaine(num_semaine);
		response.setContentType("application/pdf");
		OutputStream out = response.getOutputStream();

		PDDocument document = new PDDocument();
		PdfBox monPdfBox = new PdfBox(document);

		monPdfBox.write(consommations);

		document.save(out);
		document.close();
		return out;

	}

}


