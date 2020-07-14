package br.com.sistemaShopping.service;

import java.util.List;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.ws.rs.BadRequestException;

import br.com.sistemaShopping.DAO.ShopDAO;
import br.com.sistemaShopping.model.Shop;
import br.com.utils.Utils;

@Stateless
public class ShopService {

	ShopDAO<Shop> shopDAO = new ShopDAO<Shop>();

	public Shop getShopById(Long id) {
		List<Shop> sh = this.getAllShops();
		Shop sp = new Shop();
		if (sh != null && !sh.isEmpty()) {
			sp = sh.stream().filter(s -> Objects.equals(s.getId(), id)).findAny().orElse(null);
		}
		return sp;
	}

	public List<Shop> getAllShops() {
		return shopDAO.findALL();
	}

	public void saveOrUpdate(Long id, Shop shop) {
		Shop sh = new Shop();
		if (id != null) {
			sh = this.getShopById(id);
			if (sh != null) {
				sh = parseShop(shop, sh);
				sh.setId(id);
				if (!Utils.isValidCNPJ(sh.getCNPJ().replaceAll("[^0-9]", ""))) {
					throw new BadRequestException("O CNPJ da loja não é válido.");
				}
				shopDAO.saveOrUpdate(sh);
			}
		}
		validateShop(shop);
		shopDAO.saveOrUpdate(shop);
	}

	private Shop parseShop(Shop newShop, Shop oldShop) {
		if (newShop.getName() == null && oldShop.getName() != null) {
			newShop.setName(oldShop.getName());
		}
		if (newShop.getCNPJ() == null && oldShop.getCNPJ() != null) {
			newShop.setCNPJ(oldShop.getCNPJ());
		}
		if (newShop.getFloor() == null && oldShop.getFloor() != null) {
			newShop.setFloor(oldShop.getFloor());
		}
		if (newShop.getNumber() == null && oldShop.getNumber() != null) {
			newShop.setNumber(oldShop.getNumber());
		}
		if (newShop.isActived() && !oldShop.isActived()) {
			newShop.setActived(oldShop.isActived());
		}
		return newShop;
	}

	private void validateShop(Shop shop) {
		if (shop.getName() == null) {
			throw new BadRequestException("Nome da loja não pode ser nulo.");
		}
		if (shop.getCNPJ() == null) {
			throw new BadRequestException("O CNPJ da loja não pode ser nulo.");
		} else if (!Utils.isValidCNPJ(shop.getCNPJ().replaceAll("[^0-9]", ""))) {
			throw new BadRequestException("O CNPJ da loja não é válido.");
		}
		if (shop.getFloor() == null) {
			throw new BadRequestException("O piso da loja não pode ser nulo.");
		}
		if (shop.getNumber() == null) {
			throw new BadRequestException("O número da loja não pode ser nulo.");
		}
	}

}
