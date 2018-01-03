package com.sky;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by SKY on 12/20/2017.
 */
public class Fetch {

	public void fetchCompanyObjectsFromWeb() throws Exception {

		//beauty cafe cleaning consulting construction contractor dental design electrical engineering fashion family trust fitness food service hotel insurance investment logistic marketing massage media photo property management real estate realty restaurant sports storage studio taxi technologies therapy towing trading transport

		//		String tokens[] = { "", "family+trust", "accounting", "asset+management", "beauty", "cafe",
		//				"cleaning", "consulting", "construction", "pte.+ltd.", "pte", "ltd", "contractor",
		//				"dental", "design", "electrical", "fashion", "fitness", "food", "service",
		//				"services", "hotel", "hotel", "insurance", "investment", "logistic", "marketing",
		//				"massage", "media", "photo", "property", "management", "real", "estate", "realty",
		//				"restaurant", "sports", "storage", "studio", "taxi", "therapy", "towing", "trading",
		//				"transport", "engineer", "engineering", "technology",
		//				"technologies", };

		String tokens[] = { "engineer", "engineering", }; // use "" for general search - it will simply ignore name=x parameter

		Arrays.sort(tokens);

		int minPageNo = 1;
		int maxPageNo = 1001;

		UrlContent urlContent = new UrlContent();
		Set<Company> companySet = new HashSet<Company>();

		for (String token : tokens) {
			for (int i = minPageNo; i <= maxPageNo; i++) {
				System.out.println("Token = :" + token + ": Page = :" + i + ":");
				String urlString =
						String.format("https://opencorpdata.com/sg?name=%s&page=%s", token,
								(i + ""));
				System.out.println(urlString);
				List<Company> list = urlContent.getCompaniesFromUrl(urlString);
				if (list != null) {
					if (list.size() == 0) {
						break;
					} else {
						for (Company company : list) {
							companySet.add(company);
						}
					}

				}

			}
		}

		System.out.println("Set size = " + companySet.size());
		writeToCSVFile(companySet);
	}

	public void writeToCSVFile(Set<Company> companySet) throws Exception {

		//Delimiter used in CSV file
		String COMMA_DELIMITER = ",";
		String NEW_LINE_SEPARATOR = "\n";

		//CSV file header
		String FILE_HEADER = "id,name,address,date,status,link";

		FileWriter fileWriter = null;

		String fileName = System.getProperty("user.home") + "/Desktop/company_all_engineer.csv";
		System.out.println("File Name : " + fileName);

		try

		{
			fileWriter = new FileWriter(fileName);

			//Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());

			//Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);

			int i = 1;
			for (Company company : companySet) {
				fileWriter.append(String.valueOf(i));
				i++;
				fileWriter.append(COMMA_DELIMITER);

				String name = "\"" + company.getEntityName() + "\"";
				fileWriter.append(name);
				fileWriter.append(COMMA_DELIMITER);

				String address = "\"" + company.getOfficeAddress() + "\"";
				fileWriter.append(address);
				fileWriter.append(COMMA_DELIMITER);

				fileWriter.append(company.getStartDate());
				fileWriter.append(COMMA_DELIMITER);

				fileWriter.append(company.getStatus());
				fileWriter.append(COMMA_DELIMITER);

				fileWriter.append(company.getLink());
				fileWriter.append(NEW_LINE_SEPARATOR);
			}

			System.out.println("CSV file was created successfully !!!");

		} catch (Exception e)

		{
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally

		{

			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}

		}
	}
}

