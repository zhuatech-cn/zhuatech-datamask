/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.datamask.service;import jakarta.validation.Valid;import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service public class DataMaskService{/**
                                       * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                       */
public Result preview(Request r){List<MaskedField> out=new ArrayList<>();int supported=0;for(Field f:r.fields()){String masked=mask(f.type().toUpperCase(),f.value());boolean ok=masked!=null;if(ok)supported++;out.add(new MaskedField(f.name(),f.type(),ok?masked:"[UNSUPPORTED]",ok));}double coverage=r.fields().isEmpty()?100:supported*100.0/r.fields().size();return new Result(Math.round(coverage*10)/10.0,out,r.fields().size()-supported);}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 private String mask(String t,String v){if(v==null)return "";return switch(t){case "NAME"->v.isEmpty()?v:v.substring(0,1)+"**";case "PHONE"->v.replaceAll("(?<=\\d{3})\\d(?=\\d{4})","*");case "EMAIL"->{int i=v.indexOf('@');yield i<1?null:v.substring(0,1)+"***"+v.substring(i);}case "ID_CARD"->keep(v,4,4);case "BANK_CARD"->keep(v,4,4);case "TEXT"->v.length()<3?"***":v.substring(0,2)+"***";default->null;};}/**
                                                                                                                                                                                                                                                                                                                                                                                                                       * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                                                                                                                       */
private String keep(String v,int a,int b){return v.length()<=a+b?"*".repeat(v.length()):v.substring(0,a)+"*".repeat(v.length()-a-b)+v.substring(v.length()-b);}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record Request(@NotNull List<@Valid Field> fields){}/**
                                                             * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                             */
public record Field(@NotBlank String name,@NotBlank String type,@NotNull String value){}/**
                                                                                                                                                     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                     */
public record MaskedField(String name,String type,String maskedValue,boolean supported){}/**
                                                                                                                                                                                                                                              * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                              */
public record Result(double coverage,List<MaskedField> fields,int unsupportedCount){} }
