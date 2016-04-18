package com.xxn.iservice;

import java.util.List;
import java.util.Map;

import com.xxn.entity.Wallet;

public interface IWalletService {
	public int createWallet(Wallet wallet);
	public int rechargeWallet(Wallet wallet);
	public int batchRecharge(float val,String[] userids);
	public float getBalance(Wallet wallet);
	
	//明细表
	public int addWalletList(Wallet wallet);
	public List<Map<String, Object>> getBalancelist(Wallet wallet,int count);
}
