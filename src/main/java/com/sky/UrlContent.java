package com.sky;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class UrlContent {

	public List<Company> getCompaniesFromUrl(String urlString) {

		URL url;

		//urlString = "https://opencorpdata.com/sg?page=220";

		try {
			// get URL content

			//String urlString="https://opencorpdata.com/sg?page=220";
			url = new URL(urlString);
			URLConnection conn = url.openConnection();

			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String inputLine;
			String content = "";
			while ((inputLine = br.readLine()) != null) {
				//System.out.println(inputLine);
				content += inputLine;
			}
			br.close();

			//System.out.println(content);
			return parseAndGetCompanies(content);
			//System.out.println("Done");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Company> parseAndGetCompanies(String s)
	{

		List<Company> list = new ArrayList<Company>();

		int p = s.indexOf("<table");
		if(p == -1)
			return list;
		int q = s.indexOf("</table>");

		s = s.substring(p, q + 8);

		s = s.replaceAll("\t", "");

		//System.out.println(s);


		String sTag = "<td>";
		String eTag = "</td>";

		int ind = 0;
		while(s.indexOf(sTag,ind)>0) {
			Company company = new Company();

			for (int z = 0; z < 4; z++) {
				int sInd = s.indexOf(sTag, ind);
				int eInd = s.indexOf(eTag, ind);
				ind = eInd + 1;

				String t = s.substring(sInd + 4, eInd);
				t = t.trim();
				if (z == 0) {
					// t is = <a href="https://opencorpdata.com/sg/201720945C">SOCIALOOP PTE. LTD.</a>

					int a = t.indexOf("\"");
					int b = t.indexOf("\"", a + 1);
					String link = t.substring(a + 1, b);

					int c = t.indexOf(">");
					int d = t.indexOf("<", c);
					String name = t.substring(c + 1, d);

					company.setEntityName(name);
					company.setLink(link);

				} else if (z == 1) {
					company.setOfficeAddress(t);
				} else if (z == 2) {
					company.setStartDate(t);
				} else if (z == 3) {
					company.setStatus(t);

				}

			}

			if(company.getStatus().equals("Registered"))
			list.add(company);

		}
		return list;
	}
}



