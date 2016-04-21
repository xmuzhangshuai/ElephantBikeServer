package com.xxn.idao;

import com.xxn.entity.Token;

public interface ITokenDao {
	public int createToken(Token token);
	public int updateToken(Token token);
	public String getToken(Token token);
}
