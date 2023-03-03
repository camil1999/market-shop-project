package az.marketshopjamil.MarketShopJamil.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.marketshopjamil.MarketShopJamil.exception.NotFoundException;
import az.marketshopjamil.MarketShopJamil.model.Cashier;
import az.marketshopjamil.MarketShopJamil.repository.AuthorityRepository;
import az.marketshopjamil.MarketShopJamil.repository.CashierRepository;
import az.marketshopjamil.MarketShopJamil.repository.UserRepository;
import az.marketshopjamil.MarketShopJamil.request.RequestCashier;
import az.marketshopjamil.MarketShopJamil.response.ResponseCashier;

@Service
public class CashierService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private CashierRepository cashierRepository;

	@Autowired
	private ModelMapper mapper;

	public List<ResponseCashier> findAllCashier() {
		List<ResponseCashier> cashiers = cashierRepository.findAll().stream()
				.map(cashier -> convertRespCashier(cashier)).collect(Collectors.toList());
		if (cashiers.isEmpty()) {
			throw new NotFoundException(" Kassir tapılmadı");
		}
		return cashiers;

	}

	public void editCashier(Integer id, RequestCashier requestCashier) {
		Optional<Cashier> cashier = cashierRepository.findById(id);
		if (cashier.isEmpty()) {
			throw new NotFoundException("Kassir tapılmadı!");
		}
		Cashier editCashier = cashier.get();
		mapper.map(requestCashier, editCashier);
		cashierRepository.save(editCashier);
	}

	public void deleteById(Integer id) {
		Optional<Cashier> findedOpt = cashierRepository.findById(id);
		if (findedOpt.isEmpty()) {
			throw new NotFoundException("Seçdiyiniz Kassir tapılmadı-" + id);

		} else {
			Cashier findedCashier = findedOpt.get();
			cashierRepository.delete(findedCashier);
			userRepository.deleteByUsername(findedCashier.getUsername());
			authorityRepository.deleteByUsername(findedCashier.getUsername());

		}
	}

	private ResponseCashier convertRespCashier(Cashier cashier) {
		ResponseCashier responseCashier = new ResponseCashier();
		mapper.map(cashier, responseCashier);
		return responseCashier;
	}
}
