package br.com.sistemaShopping.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.resteasy.annotations.cache.NoCache;

import br.com.sistemaShopping.model.Shop;
import br.com.sistemaShopping.service.ShopService;

@Path("/shop")
@Consumes({ "application/json; charset=UTF-8" })
@Produces({ "application/json; charset=UTF-8" })
@Stateless
public class ShopController {

	@Inject
	private ShopService shopService;

	@POST
	@Path("/")
	@NoCache
	public void addShop(Shop shop) {
		shopService.saveOrUpdate(null, shop);
	}

	@GET
	@Path("{shopId}")
	@NoCache
	public Shop getShop(@PathParam("shopId") Long shopId) {
		return shopService.getShopById(shopId);
	}

	@GET
	@Path("/")
	@NoCache
	public List<Shop> getAllShops() {
		return shopService.getAllShops();
	}
	
	@PUT
	@Path("{shopId}")
	@NoCache
	public void updateShop(@PathParam("shopId") Long shopId, Shop shop) {
		shopService.saveOrUpdate(shopId, shop);
	}

}
