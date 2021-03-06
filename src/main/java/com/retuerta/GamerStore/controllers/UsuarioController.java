package com.retuerta.GamerStore.controllers;

import java.util.ArrayList;
import java.util.List;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retuerta.GamerStore.entities.Usuario;
import com.retuerta.GamerStore.repositories.UsuarioRepository;
import com.retuerta.GamerStore.services.TokenService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TokenService tokenService;
	
	@GetMapping("/usuarios")
	public List<Usuario> getAllUsuarios() {
		List<Usuario> usuarioList = new ArrayList<Usuario>();
		usuarioList = usuarioRepository.findAll();
		for (Usuario usr : usuarioList) {
			usr.setPassword("");
		}
		return usuarioList;
	}
	
	@PostMapping("/guardausuario")
	public ResponseEntity<String> createUsuario(@RequestBody Usuario usuario) {
		
		List<Usuario> usuarioList = new ArrayList<Usuario>();
		usuarioList = usuarioRepository.findAll();
		for (Usuario usr : usuarioList) {
			if (usr.getEmail().equals(usuario.getEmail())) {
			    return new ResponseEntity<String>(HttpStatus.FOUND);
			}
		}
		
		String hashed_password = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt()) ;
		usuario.setPassword(hashed_password);
		usuarioRepository.save(usuario);
		
	    return new ResponseEntity<>(usuario.getEmail(), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<String> checkUsuario(@RequestBody Usuario usuario) {
		String storedHash = "";
		usuario.setId(-1L);
		boolean passwordVerified = false;
		List<Usuario> usuarioList = new ArrayList<Usuario>();
		usuarioList = usuarioRepository.findAll();
		for (Usuario usr : usuarioList) {
			if (usr.getEmail().equals(usuario.getEmail())) {
				storedHash = usr.getPassword();
				usuario.setId(Long.parseLong(usr.getId().toString()));
			}
		}
		if (usuario.getId() < 0) { return new ResponseEntity<String>(HttpStatus.NOT_FOUND); }

		if(null != storedHash && storedHash.startsWith("$2a$")) {
			passwordVerified = BCrypt.checkpw(usuario.getPassword(), storedHash);
		}
		
		if (!passwordVerified) { return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED); }
		
		String token = tokenService.getToken(usuario);
		
		return new ResponseEntity<String>(token, HttpStatus.OK);
	}
	
}