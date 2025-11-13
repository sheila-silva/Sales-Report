package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleReportDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		LocalDate max = (maxDate == null || maxDate.isEmpty())
				? today
				: LocalDate.parse(maxDate);

		LocalDate min = (minDate == null || minDate.isEmpty())
				? max.minusYears(1L)
				: LocalDate.parse(minDate);

		String sellerName = (name == null) ? "" : name;

		// Passo 1: Busca os IDs com paginação
		Page<Long> pageIds = repository.findSalesReportIds(min, max, sellerName, pageable);

		// Passo 2: Busca as entidades completas com JOIN FETCH (1 query única)
		List<Sale> sales = repository.findSalesByIds(pageIds.getContent());

		// Converte para DTO mantendo a ordem
		List<SaleReportDTO> dtos = sales.stream()
				.map(sale -> new SaleReportDTO(sale))
				.toList();

		// Retorna um Page com os dados e metadados corretos
		return new PageImpl<>(dtos, pageable, pageIds.getTotalElements());
	}

	public Page<SaleSummaryDTO> getSummary(String minDate, String maxDate, Pageable pageable) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		LocalDate max = (maxDate == null || maxDate.isEmpty())
				? today
				: LocalDate.parse(maxDate);

		LocalDate min = (minDate == null || minDate.isEmpty())
				? max.minusYears(1L)
				: LocalDate.parse(minDate);

		return repository.findSalesSummary(min, max, pageable);
	}
}