package com.retuerta.GamerStore.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class FacturaService {
	
//	@Value("${spring.datasource.driver-class-name}")
//	private String className;
	
	private String datasourceUrl = "jdbc:mysql://localhost:3306/GamerStore?useSSL=false";
	private String datasourceUsername = "root";
	private String datasourcePassword = "root";
	
	public FacturaService() { }

	public byte[] generateReport(Long id, char tipo) {
		
		byte[] pdfData = null;
		
		try {			

			Connection con = DriverManager.getConnection(datasourceUrl, datasourceUsername, datasourcePassword);  

			String nombreInforme = "./reports/";
			if (tipo == 'A') {
				nombreInforme += "FacturaAlquiler.jasper";
			} else if (tipo == 'V') {
				nombreInforme += "FacturaVenta.jasper";	
			}

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("id", id);

			JasperPrint jasperPrint = JasperFillManager.fillReport(nombreInforme, parameters, con);
			pdfData = JasperExportManager.exportReportToPdf(jasperPrint);
			
			con.close();

		} catch (Exception e1) { e1.printStackTrace(); }
		
		return pdfData;
	}
	
	
}
