package com.wch.test.thistest;

import java.io.Serializable;
import java.math.BigDecimal;


public class KindCard  implements Serializable {
	
    public static final String TABLE_NAME = "kindcard";
    
    /**
     * 使用会员价
     */
    public static final int MODE_1 = 1;
    
    /**
     * 使用打折方案
     */
    public static final int MODE_2 = 2;
    
    /**
     * 设置折扣率
     */
    public static final int MODE_3 = 3;
    
    /**
     * 使用批发价
     */
    public static final int MODE_8 = 8;
    
    private Short isapply;

    private Short isautocommit;

    private String name;
    private String kindCardName;

    private String code;

    private Integer updegree;

    private String upkindcardid;

    private Integer mode;

    private BigDecimal ratio;

    private Short ismoneyratio;

    private Short isratiopass;

    private Short ismemberprice;

    private Short isforceratio;

    private Short istotalshop;

    private Short isprefeedegree;

    private Short isratiofeedegree;

    private BigDecimal exchangedegree;

    private BigDecimal ratioexchangedegree;

    private BigDecimal pledge;

    private Short isforcepwd;

    private String attachmentid;

    private String style;

    private String memo;

    private Integer membernumber;

    private String entityname;
    
    //会员卡类型的数量
    private Integer number;

    private static final long serialVersionUID = 1L;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Short getIsapply() {
        return isapply;
    }

    public void setIsapply(Short isapply) {
        this.isapply = isapply;
    }

    public Short getIsautocommit() {
        return isautocommit;
    }

    public void setIsautocommit(Short isautocommit) {
        this.isautocommit = isautocommit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getUpdegree() {
        return updegree;
    }

    public void setUpdegree(Integer updegree) {
        this.updegree = updegree;
    }

    public String getUpkindcardid() {
        return upkindcardid;
    }

    public void setUpkindcardid(String upkindcardid) {
        this.upkindcardid = upkindcardid == null ? null : upkindcardid.trim();
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public Short getIsmoneyratio() {
        return ismoneyratio;
    }

    public void setIsmoneyratio(Short ismoneyratio) {
        this.ismoneyratio = ismoneyratio;
    }

    public Short getIsratiopass() {
        return isratiopass;
    }

    public void setIsratiopass(Short isratiopass) {
        this.isratiopass = isratiopass;
    }

    public Short getIsmemberprice() {
        return ismemberprice;
    }

    public void setIsmemberprice(Short ismemberprice) {
        this.ismemberprice = ismemberprice;
    }

    public Short getIsforceratio() {
        return isforceratio;
    }

    public void setIsforceratio(Short isforceratio) {
        this.isforceratio = isforceratio;
    }

    public Short getIstotalshop() {
        return istotalshop;
    }

    public void setIstotalshop(Short istotalshop) {
        this.istotalshop = istotalshop;
    }

    public Short getIsprefeedegree() {
        return isprefeedegree;
    }

    public void setIsprefeedegree(Short isprefeedegree) {
        this.isprefeedegree = isprefeedegree;
    }

    public Short getIsratiofeedegree() {
        return isratiofeedegree;
    }

    public void setIsratiofeedegree(Short isratiofeedegree) {
        this.isratiofeedegree = isratiofeedegree;
    }

    public BigDecimal getExchangedegree() {
        return exchangedegree;
    }

    public void setExchangedegree(BigDecimal exchangedegree) {
        this.exchangedegree = exchangedegree;
    }

    public BigDecimal getRatioexchangedegree() {
        return ratioexchangedegree;
    }

    public void setRatioexchangedegree(BigDecimal ratioexchangedegree) {
        this.ratioexchangedegree = ratioexchangedegree;
    }

    public BigDecimal getPledge() {
        return pledge;
    }

    public void setPledge(BigDecimal pledge) {
        this.pledge = pledge;
    }

    public Short getIsforcepwd() {
        return isforcepwd;
    }

    public void setIsforcepwd(Short isforcepwd) {
        this.isforcepwd = isforcepwd;
    }

    public String getAttachmentid() {
        return attachmentid;
    }

    public void setAttachmentid(String attachmentid) {
        this.attachmentid = attachmentid == null ? null : attachmentid.trim();
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style == null ? null : style.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getEntityname() {
        return entityname;
    }

    public void setEntityname(String entityname) {
        this.entityname = entityname == null ? null : entityname.trim();
    }

    public Integer getMembernumber() {
        return membernumber;
    }

    public void setMembernumber(Integer membernumber) {
        this.membernumber = membernumber;
    }

	public String getKindCardName() {
		return kindCardName;
	}

	public void setKindCardName(String kindCardName) {
		this.kindCardName = kindCardName;
	}

   
}