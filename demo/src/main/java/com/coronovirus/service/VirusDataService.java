package com.coronovirus.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.coronovirus.model.LocationStats;



@Service
public class VirusDataService {
	
	private String dataURL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
	private List<LocationStats> allStats=new ArrayList<>();
	

	public List<LocationStats> getAllStats() {
		return allStats;
	}


	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		List<LocationStats> newStats=new ArrayList<>();
		HttpClient client=HttpClient.newHttpClient();
		
		HttpRequest request=HttpRequest.newBuilder().uri(URI.create(dataURL)).build();
		
		HttpResponse<String> response=client.send(request, HttpResponse.BodyHandlers.ofString());
		
		StringReader csvBodyReader=new StringReader(response.body());
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
		    LocationStats stats=new LocationStats();
		    stats.setState(record.get("Province/State"));
		    stats.setCountry(record.get("Country/Region"));
		    int todayCases=Integer.parseInt(record.get(record.size()-1));
		    int yesterdayCases=Integer.parseInt(record.get(record.size()-2));
		    int diff=todayCases-yesterdayCases;
		    stats.setLatestTotalReports(todayCases);
		    stats.setDiffFromPreviousDay(diff);
		    //System.out.println(stats);
		    newStats.add(stats);
		}
		this.allStats=newStats;
	}

}
