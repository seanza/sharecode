package com.model;

public class Chuwei {
	private int id;
	private int adr_id;
	private int row;
	private int col;
	private String code;
	private String model;
	private String isUseful;
	private String ismeter;
	private String metercode;
	private String com;


	public Chuwei(){
		/*this.id = id;
		this.adr_id = adr_id;
		this.row = row;
		this.col = col;
		this.code = code;
		this.model = model;
		this.isUseful = isUseful;
		this.ismeter = ismeter;
		this.metercode = metercode;
		this.com = com;*/
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAdr_id() {
		return adr_id;
	}
	public void setAdr_id(int adr_id) {
		this.adr_id = adr_id;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getIsUseful() {
		return isUseful;
	}
	public void setIsUseful(String isUseful) {
		this.isUseful = isUseful;
	}
	public String getIsmeter() {
		return ismeter;
	}
	public void setIsmeter(String ismeter) {
		this.ismeter = ismeter;
	}
	public String getMetercode() {
		return metercode;
	}
	public void setMetercode(String metercode) {
		this.metercode = metercode;
	}

	public String getCom() {
		return com;
	}
	public void setCom(String com) {
		this.com = com;
	}
}
