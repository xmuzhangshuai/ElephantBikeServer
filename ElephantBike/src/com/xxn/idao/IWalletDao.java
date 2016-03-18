package com.xxn.idao;

import java.util.List;
import java.util.Map;

import com.xxn.entity.Wallet;

public interface IWalletDao {
	/**
	 * 创建用户钱包
	 * @param wallet
	 * @return
	 */
	public int createWallet(Wallet wallet);
	/**
	 * 充值费用
	 * @param wallet
	 * @return
	 */
	public int rechargeWallet(Wallet wallet);
	/**
	 * 批量增加
	 * @return
	 */
	public int batchRecharge(float val);
	/**
	 * 获取用户余额
	 * @param wallet
	 * @return
	 */
	public float getBalance(Wallet wallet);
	
	
	//钱包明细数据表
	/**
	 * 增加明细列表
	 * @param wallet
	 * @return
	 */
	public int addWalletList(Wallet wallet);
	
	public List<Map<String, Object>> getBalancelist(Wallet wallet,int count);
	
}
