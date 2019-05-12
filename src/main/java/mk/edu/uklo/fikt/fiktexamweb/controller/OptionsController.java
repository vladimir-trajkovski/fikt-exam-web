package mk.edu.uklo.fikt.fiktexamweb.controller;

import java.util.List;

import javax.validation.Valid;

import mk.edu.uklo.fikt.fiktexamweb.util.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mk.edu.uklo.fikt.fiktexamweb.model.Options;

@RestController
@RequestMapping({"/options"})
public class OptionsController {
	
	@Autowired
    OptionsService optionsService;
	
	@GetMapping({"/get"})
	public List<Options> getAllOptions(){
		return optionsService.getOptions();
	}
	
//	@GetMapping({"/get/byquestion"})
//	public List<Options> getByQuestion(String questionText){
//		return optionsService.getOptionsForQuestion(questionText);
//	}
//
	@PostMapping({"/post"})
	public Options addOption(@Valid @RequestBody Options option) {
		return optionsService.addOption(option);
	}
	
}
