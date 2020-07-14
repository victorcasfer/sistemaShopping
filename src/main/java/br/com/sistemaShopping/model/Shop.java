package br.com.sistemaShopping.model;

import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="shop")
public class Shop implements GenericEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;

	@Column(name="name")
	private String name;
	
	@Column(name="cnpj")
	private String CNPJ;
	
	@Column(name="shop_floor")
	private String floor;
	
	@Column(name = "shop_number")
	private String number;
	
	@Column(name="actived")
	private boolean actived;
	
	@JsonbTransient
	@ManyToMany(mappedBy = "shops")
	private List<Segment> segments;

	public Shop() {
	}

	public Shop(Long id, String name, String cNPJ, String floor, String number, boolean actived,
			List<Segment> segments) {
		this.id = id;
		this.name = name;
		CNPJ = cNPJ;
		this.floor = floor;
		this.number = number;
		this.actived = actived;
		this.segments = segments;
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

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public boolean isActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

	public List<Segment> getSegment() {
		return segments;
	}

	public void setSegment(List<Segment> segment) {
		this.segments = segment;
	}
	
}
