package com.xxn.butils;

import java.util.ArrayList;
import java.util.List;


public class GridDataModel<T> {
	private int total;
	private List<T> rows = new ArrayList<T>();

	public List<T> getRows() {
		return this.rows;
	}

	public void setRows(List<T> paramList) {
		this.rows = paramList;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int paramInt) {
		this.total = paramInt;
	}
}