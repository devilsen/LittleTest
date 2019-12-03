package com.test.devilsen.test.socket.model;

import androidx.annotation.NonNull;

/**
 * desc :
 * date : 2019-11-29 16:16
 *
 * @author : dongSen
 */
public class PayResult {

    /**
     * username:用户名
     * payResult:支付结果
     * amount:金额
     * tradeNo:订单号
     * integralType:积分类型（ 1：神奇积分  2：关爱积分  3：能量券  4：现金）
     * appName:商家
     * errNo:错误码（1301：神奇积分不足 20：神奇积分或者能量券不足）
     * errMsg:错误信息
     */

    public String username;
    public boolean payResult;
    public double amount;
    public String tradeNo;
    public int integralType;
    public String appName;
    public int errNo;
    public String errMsg;

    @Override
    public String toString() {
        return "PayResult{" +
                "username='" + username + '\'' +
                ", payResult='" + payResult + '\'' +
                ", amount=" + amount +
                ", tradeNo='" + tradeNo + '\'' +
                ", integralType=" + integralType +
                ", appName='" + appName + '\'' +
                ", errNo=" + errNo +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}
