package com.coronovirus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.coronovirus.model.LocationStats;
import com.coronovirus.service.VirusDataService;



@Controller
public class HomeController {
	@Autowired
	VirusDataService VirusDataService;
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats= VirusDataService.getAllStats();
		int sumReports=allStats.stream().mapToInt(stat->stat.getLatestTotalReports()).sum();
		int sumNew=allStats.stream().mapToInt(stat->stat.getDiffFromPreviousDay()).sum();
		//System.out.println("aaaaaaaaaaaaaaaaaa"+latestTotal);
		model.addAttribute("locationStats", allStats);
		model.addAttribute("sumReports", sumReports);
		model.addAttribute("sumNew", sumNew);
		return "home";
	}
}
