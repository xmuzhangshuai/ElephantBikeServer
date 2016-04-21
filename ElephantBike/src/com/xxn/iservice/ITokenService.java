package com.xxn.iservice;

import com.xxn.entity.Token;

public interface ITokenService {
	
	public int createToken(Token token);
	public int updateToken(Token token);
	public String getToken(Token token);
}
