package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    // Query 1: Busca apenas os IDs com paginação
    @Query("SELECT obj.id FROM Sale obj "
            + "WHERE obj.date BETWEEN :minDate AND :maxDate "
            + "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<Long> findSalesReportIds(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

    // Query 2: Busca as entidades completas com JOIN FETCH usando os IDs
    @Query("SELECT obj FROM Sale obj "
            + "JOIN FETCH obj.seller "
            + "WHERE obj.id IN :ids")
    List<Sale> findSalesByIds(List<Long> ids);

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleSummaryDTO(obj.seller.name, SUM(obj.amount)) "
            + "FROM Sale obj "
            + "WHERE obj.date BETWEEN :minDate AND :maxDate "
            + "GROUP BY obj.seller.name")
    Page<SaleSummaryDTO> findSalesSummary(LocalDate minDate, LocalDate maxDate, Pageable pageable);
}