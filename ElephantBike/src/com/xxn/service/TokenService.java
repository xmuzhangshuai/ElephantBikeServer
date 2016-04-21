package com.xxn.service;

import com.xxn.dao.TokenDao;
import com.xxn.entity.Token;
import com.xxn.idao.ITokenDao;
import com.xxn.iservice.ITokenService;

public class TokenService implements ITokenService{

	@Override
	public int createToken(Token token) {
		ITokenDao iTokenDao = new TokenDao();
		return iTokenDao.createToken(token);
	}

	@Override
	public int updateToken(Token token) {
		ITokenDao iTokenDao = new TokenDao();
		return iTokenDao.updateToken(token);
	}

	@Override
	public String getToken(Token token) {
		ITokenDao iTokenDao = new TokenDao();
		return iTokenDao.getToken(token);
	}

}
