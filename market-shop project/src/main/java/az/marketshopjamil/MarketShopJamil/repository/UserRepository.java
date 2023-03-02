package az.marketshopjamil.MarketShopJamil.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import az.marketshopjamil.MarketShopJamil.model.User;
@Transactional
public interface UserRepository extends JpaRepository<User, String> {
	User findByUsername(String username);
	void deleteByUsername(String username);

}
