package az.marketshopjamil.MarketShopJamil.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import az.marketshopjamil.MarketShopJamil.exception.AlreadyDefinedException;
import az.marketshopjamil.MarketShopJamil.exception.MyAccessDeniedException;
import az.marketshopjamil.MarketShopJamil.exception.NotFoundException;
import az.marketshopjamil.MarketShopJamil.model.Admin;
import az.marketshopjamil.MarketShopJamil.model.Authority;
import az.marketshopjamil.MarketShopJamil.model.Cashier;
import az.marketshopjamil.MarketShopJamil.model.User;
import az.marketshopjamil.MarketShopJamil.repository.AdminRepository;
import az.marketshopjamil.MarketShopJamil.repository.AuthorityRepository;
import az.marketshopjamil.MarketShopJamil.repository.CashierRepository;
import az.marketshopjamil.MarketShopJamil.repository.UserRepository;
import az.marketshopjamil.MarketShopJamil.request.RequestUser;
import az.marketshopjamil.MarketShopJamil.response.ResponseUser;
import az.marketshopjamil.MarketShopJamil.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private CashierRepository cashierRepository;

	@Autowired
	private ModelMapper mapper;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	private ResponseUser convertRespUser(User user) {
		ResponseUser response = new ResponseUser();
		mapper.map(user, response);
		return response;
	}

	@Override
	public List<ResponseUser> findAllUser() {
		List<ResponseUser> users = userRepository.findAll().stream().map(user -> convertRespUser(user))
				.collect(Collectors.toList());
		if (users.isEmpty()) {
			throw new NotFoundException("İstifadəçi tapılmadı");
		}
		return users;

	}

	@Override
	public void saveUser(RequestUser requestUser) {
		boolean usernameFound = false;
		User findedUser = userRepository.findByUsername(requestUser.getUsername());
		if (findedUser != null)
			usernameFound = true;
		if (usernameFound) {
			throw new AlreadyDefinedException("İstifadəçi adı artıq mövcuddur! " + requestUser.getUsername());
		}
		switch (requestUser.getType()) {
		case "Admin":
			Admin admin = new Admin();
			mapper.map(requestUser, admin);
			adminRepository.save(admin);
			break;

		case "Cashier":
			Cashier cashier = new Cashier();
			mapper.map(requestUser, cashier);
			cashierRepository.save(cashier);
			break;
		default:
			throw new MyAccessDeniedException("İstifadəçi tipi düzgün seçilməyib!-" + requestUser.getType());
		}

		User user = new User();
		user.setUsername(requestUser.getUsername());
		user.setPassword("{bcrypt}" + encoder.encode(requestUser.getPassword()));
		user.setEnabled(true);
		user.setType(requestUser.getType());
		userRepository.save(user);

		Authority authority = new Authority();
		authority.setUsername(requestUser.getUsername());
		authority.setAuthority(requestUser.getType());
		authorityRepository.save(authority);

	}

}
