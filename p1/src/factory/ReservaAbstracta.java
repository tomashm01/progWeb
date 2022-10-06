package factory;
import java.time.LocalDateTime;
import entities.enums.DificultadPista;

public abstract class ReservaAbstracta {
	
	protected Integer idUser;
	protected LocalDateTime date;
	protected Integer time;
	protected Integer idPista;
	protected float price;
	protected float discount;
	protected Integer numAdults;
	protected Integer numChilds;
	
	protected Integer id;
	public static final int MAX_RANDOM = 999999999;

	public ReservaAbstracta() {
		
	}

	public ReservaAbstracta(Integer idUser, LocalDateTime date, Integer time, Integer idPista, float price,float discount, Integer id,Integer numAdults,Integer numChilds) {
		this.idUser = idUser;
		this.date = date;
		this.time = time;
		this.idPista = idPista;
		this.price = price;
		this.discount = discount;
		this.id = id;
		this.numAdults = numAdults;
		this.numChilds = numChilds;
	}

	public abstract DificultadPista getType();
	
	public abstract boolean validate();
	
	public Integer getNumPlayers() {
		return numChilds+numAdults;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getIdPista() {
		return idPista;
	}

	public void setIdPista(Integer idPista) {
		this.idPista = idPista;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumAdults() {
		return numAdults;
	}

	public void setNumAdults(Integer numAdults) {
		this.numAdults = numAdults;
	}

	public void setNumChilds(Integer numChilds) {
		this.numChilds = numChilds;
	}
	
}


