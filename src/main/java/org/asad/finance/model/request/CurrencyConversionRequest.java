package org.asad.finance.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.asad.finance.model.CurrencyCode;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "Request to Covert a currency")
public class CurrencyConversionRequest {

    @NotNull
    @ApiModelProperty(required = true, value = "Amount which needs to be converted")
    private Double amount;

    @NotNull
    @ApiModelProperty(required = true, value = "Source Currency Code", allowableValues = "aed,afn,all,amd,ang,aoa,ars,aud,awg,azn,bam,bbd,bdt,bgn,bhd,bif,bmd,bnd,bob,brl,bsd,btc,btn,bwp,byn,byr,bzd,cad,cdf,chf,clf,clp,cny,cop,crc,cuc,cup,cve,czk,djf,dkk,dop,dzd,egp,ern,etb,eur,fjd,fkp,gbp,gel,ggp,ghs,gip,gmd,gnf,gtq,gyd,hkd,hnl,hrk,htg,huf,idr,ils,imp,inr,iqd,irr,isk,jep,jmd,jod,jpy,kes,kgs,khr,kmf,kpw,krw,kwd,kyd,kzt,lak,lbp,lkr,lrd,lsl,ltl,lvl,lyd,mad,mdl,mga,mkd,mmk,mnt,mop,mro,mur,mvr,mwk,mxn,myr,mzn,nad,ngn,nio,nok,npr,nzd,omr,pab,pen,pgk,php,pkr,pln,pyg,qar,ron,rsd,rub,rwf,sar,sbd,scr,sdg,sek,sgd,shp,sll,sos,srd,std,svc,syp,szl,thb,tjs,tmt,tnd,top,try,ttd,twd,tzs,uah,ugx,usd,uyu,uzs,vef,vnd,vuv,wst,xaf,xag,xau,xcd,xdr,xof,xpf,yer,zar,zmk,zmw,zwl")
    private CurrencyCode sourceCurrency;

    @NotNull
    @ApiModelProperty(required = true, value = "Source Currency Code", allowableValues = "aed,afn,all,amd,ang,aoa,ars,aud,awg,azn,bam,bbd,bdt,bgn,bhd,bif,bmd,bnd,bob,brl,bsd,btc,btn,bwp,byn,byr,bzd,cad,cdf,chf,clf,clp,cny,cop,crc,cuc,cup,cve,czk,djf,dkk,dop,dzd,egp,ern,etb,eur,fjd,fkp,gbp,gel,ggp,ghs,gip,gmd,gnf,gtq,gyd,hkd,hnl,hrk,htg,huf,idr,ils,imp,inr,iqd,irr,isk,jep,jmd,jod,jpy,kes,kgs,khr,kmf,kpw,krw,kwd,kyd,kzt,lak,lbp,lkr,lrd,lsl,ltl,lvl,lyd,mad,mdl,mga,mkd,mmk,mnt,mop,mro,mur,mvr,mwk,mxn,myr,mzn,nad,ngn,nio,nok,npr,nzd,omr,pab,pen,pgk,php,pkr,pln,pyg,qar,ron,rsd,rub,rwf,sar,sbd,scr,sdg,sek,sgd,shp,sll,sos,srd,std,svc,syp,szl,thb,tjs,tmt,tnd,top,try,ttd,twd,tzs,uah,ugx,usd,uyu,uzs,vef,vnd,vuv,wst,xaf,xag,xau,xcd,xdr,xof,xpf,yer,zar,zmk,zmw,zwl")
    private CurrencyCode targetCurrency;
}
