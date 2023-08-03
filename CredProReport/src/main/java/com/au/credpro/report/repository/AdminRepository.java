package com.au.credpro.report.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.au.credpro.report.entity.Admin;
import com.au.credpro.report.entity.User;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	Admin findByAdminAuId(Long adminAuId);
}
