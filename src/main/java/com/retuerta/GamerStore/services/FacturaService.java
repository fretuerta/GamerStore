package com.retuerta.GamerStore.services;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class FacturaService {
	
	private static String datasourceUrl;
	private static String datasourceUsername;
	private static String datasourcePassword;
	
	@Value("${spring.datasource.url}")
	public void setURL(String value) {
		FacturaService.datasourceUrl = value;
	}
	
	@Value("${spring.datasource.username}")
	public void setUsername(String value) {
		FacturaService.datasourceUsername = value;
	}

	@Value("${spring.datasource.password}")
	public void setPassword(String value) {
		FacturaService.datasourcePassword = value;
	}
	
	public FacturaService() { }

	public byte[] generateReport(Long id, char tipo) {
		
		byte[] pdfData = null;
		
		try {			

			Connection con = DriverManager.getConnection(datasourceUrl, datasourceUsername, datasourcePassword);  

			String nombreInforme = "reports/";
			
			if (tipo == 'A') {
				nombreInforme += "FacturaAlquiler.jasper";
			} else if (tipo == 'V') {
				nombreInforme += "FacturaVenta.jasper";	
			}

			InputStream informeIS = new ClassPathResource(nombreInforme).getInputStream();
			
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("id", id);

			JasperPrint jasperPrint = JasperFillManager.fillReport(informeIS, parameters, con);
			pdfData = JasperExportManager.exportReportToPdf(jasperPrint);
			
			con.close();

		} catch (Exception e1) { e1.printStackTrace(); }
		
		return pdfData;
	}
	
	
}
