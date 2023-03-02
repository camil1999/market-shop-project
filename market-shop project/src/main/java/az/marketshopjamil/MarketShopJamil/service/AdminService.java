package az.marketshopjamil.MarketShopJamil.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import az.marketshopjamil.MarketShopJamil.exception.NotDeleteSelfException;
import az.marketshopjamil.MarketShopJamil.exception.NotFoundException;
import az.marketshopjamil.MarketShopJamil.model.Admin;
import az.marketshopjamil.MarketShopJamil.repository.AdminRepository;
import az.marketshopjamil.MarketShopJamil.repository.AuthorityRepository;
import az.marketshopjamil.MarketShopJamil.repository.UserRepository;
import az.marketshopjamil.MarketShopJamil.request.RequestAdmin;
import az.marketshopjamil.MarketShopJamil.response.ResponseAdmin;

@Service
public class AdminService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private ModelMapper mapper;

	private String getUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	public List<ResponseAdmin> findAllAdmin() {
		List<ResponseAdmin> admins = adminRepository.findAll().stream().map(admin -> convertRespAdmin(admin))
				.collect(Collectors.toList());
		if (admins.isEmpty()) {
			throw new NotFoundException("Admin tapılmadı!");
		}
		return admins;

	}

	public void editAdmin(Integer id, RequestAdmin requestAdmin) {
		Optional<Admin> admin = adminRepository.findById(id);
		if (admin.isEmpty()) {
			throw new NotFoundException("Admin tapılmadı!");
		}
		Admin editAdmin = admin.get();
		mapper.map(requestAdmin, editAdmin);
		adminRepository.save(editAdmin);
	}

	public void deleteById(@PathVariable Integer id) {
		Optional<Admin> findedOpt = adminRepository.findById(id);
		if (findedOpt.isEmpty()) {
			throw new NotFoundException("Seçdiyiniz Admin tapılmadı-" + id);

		} else {
			Admin currentAdmin = adminRepository.findByUsername(getUsername());
			Admin findedAdmin = findedOpt.get();

			if (currentAdmin.getId() == findedAdmin.getId()) {
				throw new NotDeleteSelfException("Özünüzü silə bilmərsiz!");
			}

			adminRepository.delete(findedAdmin);
			userRepository.deleteByUsername(findedAdmin.getUsername());
			authorityRepository.deleteByUsername(findedAdmin.getUsername());

		}
	}

	private ResponseAdmin convertRespAdmin(Admin admin) {
		ResponseAdmin responseAdmin = new ResponseAdmin();
		mapper.map(admin, responseAdmin);
		return responseAdmin;
	}
}
