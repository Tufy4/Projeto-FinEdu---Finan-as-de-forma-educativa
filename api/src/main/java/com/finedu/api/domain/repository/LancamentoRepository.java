package com.finedu.api.domain.repository;

import com.finedu.api.domain.model.LancamentoFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoRepository extends JpaRepository<LancamentoFinanceiro, Long> {
}