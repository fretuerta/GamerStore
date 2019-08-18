package com.retuerta.GamerStore.services;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.retuerta.GamerStore.DTO.AlquilerDTO;
import com.retuerta.GamerStore.DTO.FacturaDTO;
import com.retuerta.GamerStore.DTO.VentaDTO;
import com.retuerta.GamerStore.entities.Alquiler;
import com.retuerta.GamerStore.entities.AlquilerDetalle;
import com.retuerta.GamerStore.entities.Venta;
import com.retuerta.GamerStore.entities.VentaDetalle;
import com.retuerta.GamerStore.repositories.AlquilerDetalleRepository;
import com.retuerta.GamerStore.repositories.VentaDetalleRepository;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class FacturaService {
	
	@Autowired
	private VentaService ventaService;
	
	@Autowired
	private AlquilerService alquilerService;
	
	@Autowired
	private AlquilerDetalleRepository alquilerDetalleRepository;
	
	@Autowired
	private VentaDetalleRepository ventaDetalleRepository;
	
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

	public List<FacturaDTO> getFacturasCliente(Long clienteId) {
		List<FacturaDTO> facturasList = new ArrayList<FacturaDTO>();
		
		List<Alquiler> alquileres = alquilerService.getAlquileres();
		List<Venta> ventas = ventaService.getVentas();


		for (Alquiler alq : alquileres) {
			if (alq.getCliente() != null && alq.getCliente().getId() == clienteId ) {
				AlquilerDTO alquilerDTO = new AlquilerDTO(alq);
				FacturaDTO facturaDTO = new FacturaDTO();
				facturaDTO.setFromAlquilerDTO(alquilerDTO);
				facturasList.add(facturaDTO);
			}
		}
		
		for (Venta vts : ventas) {
			if (vts.getCliente() != null && vts.getCliente().getId() == clienteId ) {
				VentaDTO ventaDTO = new VentaDTO(vts);
				FacturaDTO facturaDTO = new FacturaDTO();
				facturaDTO.setFromVentaDTO(ventaDTO);
				facturasList.add(facturaDTO);
			}
		}
		
		return facturasList;
		
	}
	
	public List<FacturaDTO> getFacturasDetCliente(Long clienteId) {
		List<FacturaDTO> facturasList = new ArrayList<FacturaDTO>();
		
		List<Alquiler> alquileres = alquilerService.getAlquileres();
		List<AlquilerDetalle> alquilerDetalleListTODOS = alquilerDetalleRepository.findAll();
		List<Venta> ventas = ventaService.getVentas();
		List<VentaDetalle> ventaDetalleListTODOS = ventaDetalleRepository.findAll();
		
		for (Alquiler alq : alquileres) {
			if (alq.getCliente().getId() == clienteId ) {
				AlquilerDTO alquilerDTO = new AlquilerDTO(alq);
				for (AlquilerDetalle alqDet : alquilerDetalleListTODOS) {
					if (alqDet.getAlquiler().equals(alq)) {
						alquilerDTO.addAlquilerDetalle(alqDet);
					}
				}
				FacturaDTO facturaDTO = new FacturaDTO();
				facturaDTO.setFromAlquilerDTO(alquilerDTO);
				facturasList.add(facturaDTO);
			}
		}
		
		for (Venta vts : ventas) {
			if (vts.getCliente().getId() == clienteId ) {
				VentaDTO ventaDTO = new VentaDTO(vts);
				for (VentaDetalle vtaDet : ventaDetalleListTODOS) {
					if (vtaDet.getVenta() .equals(vts)) {
						ventaDTO.addVentaDetalle(vtaDet);
					}
					
				}
				FacturaDTO facturaDTO = new FacturaDTO();
				facturaDTO.setFromVentaDTO(ventaDTO);
				facturasList.add(facturaDTO);
			}
		}

		return facturasList;
	}
	
	
	public FacturaDTO getFacturaDet(String numFactura) {
		
		FacturaDTO facturaDTO = new FacturaDTO();
		
		if (numFactura.length() < 2) { return facturaDTO; }
		
		Long id = Long.parseLong(numFactura.substring(1));
		
		if (numFactura.charAt(0) == 'A') {
			Alquiler alquiler = alquilerService.getAlquiler(id);
			AlquilerDTO alquilerDTO = new AlquilerDTO(alquiler);
		  	List<AlquilerDetalle> alquilerDetallesList = new ArrayList<AlquilerDetalle>();
		  	alquilerDetallesList = alquilerDetalleRepository.findAll();
			for (AlquilerDetalle alqDet : alquilerDetallesList) {
				if (alqDet.getAlquiler().equals(alquiler)) {
					alquilerDTO.addAlquilerDetalle(alqDet);
				}
			}
			facturaDTO.setFromAlquilerDTO(alquilerDTO);
		}
		
		if (numFactura.charAt(0) == 'V') {
			Venta venta = ventaService.getVenta(id);
			VentaDTO ventaDTO = new VentaDTO(venta);
			List<VentaDetalle> ventaDetallesList = new ArrayList<VentaDetalle>();
			ventaDetallesList = ventaDetalleRepository.findAll();
			for (VentaDetalle vtaDet : ventaDetallesList) {
				if (vtaDet.getVenta().equals(venta)) {
					ventaDTO.addVentaDetalle(vtaDet);
				}
			}
			facturaDTO.setFromVentaDTO(ventaDTO);
		}

		return facturaDTO;
	}
	
	
	
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
