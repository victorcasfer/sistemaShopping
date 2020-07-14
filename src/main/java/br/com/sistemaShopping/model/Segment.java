package br.com.sistemaShopping.model;

import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "segment")
public class Segment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@JsonbTransient
	@ManyToMany
	@JoinTable(name = "shop_segment", joinColumns = 
	@JoinColumn(name = "id_segment"), inverseJoinColumns = 
	@JoinColumn(name = "id_shop", nullable = false))
	private List<Shop> shops;

	public Segment() {
	}

	public Segment(Long id, String name, List<Shop> shops) {
		this.id = id;
		this.name = name;
		this.shops = shops;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Shop> getShops() {
		return shops;
	}

	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}

}
