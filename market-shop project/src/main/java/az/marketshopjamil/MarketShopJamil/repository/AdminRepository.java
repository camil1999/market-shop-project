package az.marketshopjamil.MarketShopJamil.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import az.marketshopjamil.MarketShopJamil.model.Admin;
@Transactional
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	Admin findByUsername(String username);
}