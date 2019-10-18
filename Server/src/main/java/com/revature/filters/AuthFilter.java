package com.revature.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

import com.revature.dtos.Principal;
import com.revature.models.Role;
import com.revature.security.JwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@WebFilter("/*")
public class AuthFilter extends OncePerRequestFilter {
	
	private Logger log = LogManager.getLogger(AuthFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws ServletException, IOException {

    
        String header = req.getHeader(JwtConfig.HEADER);

        if (header == null || !header.startsWith(JwtConfig.PREFIX)) {
            attachCorsResponseHeaders(resp);
            chain.doFilter(req, resp);
            return;
        }

        String token = header.replaceAll(JwtConfig.PREFIX, "");

        Claims claims = Jwts.parser()
                .setSigningKey(JwtConfig.SECRET.getBytes())
                .parseClaimsJws(token)
                .getBody();

        int id = Integer.parseInt(claims.getId());
        String username = claims.getSubject();
        String role = claims.get("role", String.class).trim();
        Principal principal = new Principal(id, username, role);
        req.setAttribute("principal", principal);
        
        System.out.println("principal object added to request");
        attachCorsResponseHeaders(resp);
        System.out.println("cors headers attached to response");
        chain.doFilter(req, resp);
    }
    
    /**
	 * Attaches the required appropriate response headers to satisfy 
	 * the CORS pre-flight request requirements. 
	 * 
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	public void attachCorsResponseHeaders(HttpServletResponse resp) throws IOException, ServletException {
		log.info("Attaching CORS headers to HTTP response");
		resp.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE");
		resp.setHeader("Access-Control-Allow-Headers", "Content-type, Authorization");
        resp.setHeader("Access-Control-Expose-Headers", "Authorization");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
	}
}