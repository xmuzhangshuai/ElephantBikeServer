package com.xxn.service;

import java.util.List;
import java.util.Map;

import com.xxn.dao.WalletDao;
import com.xxn.entity.Wallet;
import com.xxn.idao.IWalletDao;
import com.xxn.iservice.IWalletService;

public class WalletService implements IWalletService{

	@Override
	public int createWallet(Wallet wallet) {
		IWalletDao iWalletDao = new WalletDao();
		return iWalletDao.createWallet(wallet);
	}

	@Override
	public int rechargeWallet(Wallet wallet) {
		IWalletDao iWalletDao = new WalletDao();
		return iWalletDao.rechargeWallet(wallet);
	}

	@Override
	public int addWalletList(Wallet wallet) {
		IWalletDao iWalletDao = new WalletDao();
		return iWalletDao.addWalletList(wallet);
	}

	@Override
	public List<Map<String, Object>> getBalancelist(Wallet wallet, int count) {
		IWalletDao iWalletDao = new WalletDao();
		return iWalletDao.getBalancelist(wallet, count);
	}

	@Override
	public float getBalance(Wallet wallet) {
		IWalletDao iWalletDao = new WalletDao();
		return iWalletDao.getBalance(wallet);
	}

	@Override
	public int batchRecharge(float val,String[] userids) {
		IWalletDao iWalletDao = new WalletDao();
		return iWalletDao.batchRecharge(val,userids);
	}

}
