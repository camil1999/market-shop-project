package az.marketshopjamil.MarketShopJamil.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import az.marketshopjamil.MarketShopJamil.model.Authority;
@Transactional
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
	void deleteByUsername(String username);
}
