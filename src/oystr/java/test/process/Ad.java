package oystr.java.test.process;

import java.lang.reflect.Field;

public class Ad {
	private boolean tipo; /* true is ad false is error */
	private String model;
	private String contract; /*rent or sale*/
	private String make;
	private String year;
	private String workHours;
	private String city;
	private String price;
	private String pictureURL;
	private String erro;	
	public boolean getTipo() {
		return tipo;
	}
	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getWorkHours() {
		return workHours;
	}
	public void setWorkHours(String workHours) {
		this.workHours = workHours;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPictureURL() {
		return pictureURL;
	}
	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}	
	public String getErro() {
		return erro;
	}
	public void setErro(String erro) {
		this.erro = erro;
	}
	public String toJSON() {
		String sAux="",sComma="";
		Field[] fields = Ad.class.getDeclaredFields();
		for (Field field : fields) {
			try {
				sAux += sComma + "\"" + field.getName() + "\":\"" + field.get(this) + "\"";
				sComma = ",";
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return "{" + sAux + "}";
	}
	public String export(String sDelimiter) {
		String sAux="",sPipe="";
		Field[] fields = Ad.class.getDeclaredFields();		
		for (Field field : fields) {
			try {
				if((field.getName().compareTo("tipo")!=0) &&
				   (field.getName().compareTo("erro")!=0)) {
					sAux += sPipe + field.get(this);
					sPipe = sDelimiter;
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return sAux;
	}
	public static String getCabecalho(String sDelimiter) {
		String sAux="",sPipe="";
		Field[] fields = Ad.class.getDeclaredFields();
		for (Field field : fields) {
			try {
				if((field.getName().compareTo("tipo")!=0) &&
				   (field.getName().compareTo("erro")!=0)) {
					sAux += sPipe + field.getName();
					sPipe = sDelimiter;
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		return sAux;
	}
}
