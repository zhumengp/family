package org.zhump.familybill.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 *@Title JwtUtil
 *@Description: token工具类
 *
 *@author zhump
 *@date 2021/4/10 20:56
 */
public class JwtUtil {
	private static final Log log = LogFactory.getLog(JwtUtil.class);
	/**
	 *时间
	 */
	public static final long EXPIRATIONTIME = 1000*60*60*24*1;
	/**
	 *盐
	 */
	public static final String SECRET = "DPSASecret";
	/**
	 *头部
	 */
	public static final String TOKEN_PREFIX = "Bearer";
	/**
	 * 标示位
	 */
	public static final String HEADER_STRING = "Authorization";

	/**
	 * 设置请求头
	 * @param res
	 * @param id
	 */
	public static void addAuthentication(HttpServletResponse res, String id) {
        res.addHeader(HEADER_STRING,getToken(id));
    }

	/**
	 * 生成请求头信息
	 * @param id
	 * @return
	 */
	public static String getToken(String id){
		 String jwt = Jwts.builder()
	                .setSubject(id)
	                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
	                .signWith(SignatureAlgorithm.HS512, SECRET)
	                .compact();
	  return TOKEN_PREFIX + " " + jwt;
	}
	
	/**
	 * 获取请求头中的信息
	 * @param request
	 * @return
	 */
	public static String getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if(StringUtils.isEmpty(token)){
        	token=request.getParameter("token");
		}
        return getUserId(token);
    }

	/**
	 * 根据token获取用户ID信息
	 * @param token
	 * @return
	 */
	public static String getUserId(String token){
		  if (token != null) {
	            try{
	            	String id = Jwts.parser()
		                    .setSigningKey(SECRET)
		                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
		                    .getBody()
		                    .getSubject();
	            	return id;
	            }catch(Exception ex){
	            	if(!(ex instanceof JwtException)){
	            		log.error(ex);
	            	}
	            	return null;
	            }
	        }
		  return null;
	}
}
