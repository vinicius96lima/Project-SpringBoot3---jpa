package com.example.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.example.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "tb_order")
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// com essas anotações Estou dizendo que ela uma tabela do bando de dados
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	
	@ManyToOne // Anotação para chave estrangeira muitos para um, muitos pedidos para um cliente
	@JoinColumn (name = "client_id") //O nome da chave estrangeira.
	private User client;
	
	@OneToMany (mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	
	@OneToOne (mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;
	
	private Integer orderStatus;
	
	
	
	
	public Order() {
		
	}
	
	public Order (Long id, Instant moment, OrderStatus orderStatus, User client) {
		super();
		this.client = client;
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
	}
	
	public void setOrderStatus(OrderStatus orderStatus) {
		if(orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}
	
	
	
	public Set<OrderItem> getItems(){
		return items;
	}
	
	public OrderStatus getOrderStatus() {
		return OrderStatus.valuesOf(orderStatus);
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId () {
		return id;
	}
	
	public void setMoment(Instant moment) {
		this.moment = moment;
	}
	
	public Instant getMoment() {
		return moment;
	}
	
	public void setClient (User client) {
		this.client = client;
	}
	
	public User getClient() {
		return client;
	}
	
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public Payment getPayment() {
		return payment;
	}
	
	public Double getTotal() {
		double sum = 0.0;
		for(OrderItem x : items) {
			sum += x.getSubTotal();			
		}
		return sum;
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}

	
	
	

}
