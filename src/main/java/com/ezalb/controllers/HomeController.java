package com.ezalb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ezalb.models.LocationStats;
import com.ezalb.services.CoronaVirusData;

@Controller
public class HomeController {
	
	@Autowired
	CoronaVirusData coronaVirusData;
	
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = coronaVirusData.getAllStats();
		
		int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestCount()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases", totalCases);
		model.addAttribute("totalNewCases", totalNewCases);
		
		return "home";
	}
}
