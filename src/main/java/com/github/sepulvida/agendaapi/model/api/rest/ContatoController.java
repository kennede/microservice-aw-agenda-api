package com.github.sepulvida.agendaapi.model.api.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.sepulvida.agendaapi.model.entity.Contato;
import com.github.sepulvida.agendaapi.model.repository.ContatoRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/contatos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ContatoController {

	private final ContatoRepository contatoRepository;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Contato salvar (@RequestBody Contato contato) {
		return contatoRepository.save(contato);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		contatoRepository.deleteById(id);
	}
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public Page<Contato> getList(@RequestParam(value =  "page", defaultValue = "0") Integer pagina,
								@RequestParam( value =  "size" , defaultValue = "10") Integer tamanhoPagina ){
		
		
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");
		PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina, sort);
		
		return contatoRepository.findAll(pageRequest);
	}
	
	@PatchMapping("{id}/favorito")
	public void favorite(@PathVariable Long id) {
		Optional<Contato> contato =   contatoRepository.findById(id);
		
		contato.ifPresent( c ->  {
			boolean favorito = c.getFavorito() == Boolean.TRUE;
			c.setFavorito(favorito);
			contatoRepository.save(c);
		});
	}
	
	@PutMapping("{id}/favorito")
	@ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
	public void update(@PathVariable Long id, @RequestBody Contato contato) {
		Optional<Contato> contatoDb =   contatoRepository.findById(id);
		contatoDb.ifPresent( c ->  {
			contatoRepository.save(contato);
		});
	}
	
	@PutMapping("{id}/foto")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public byte [] addPhoto(@PathVariable Long id, @RequestParam("foto") Part arquivo ) {
		
		Optional<Contato> contato = contatoRepository.findById(id);
		
		return contato.map(c -> {
			try {
				InputStream is = arquivo.getInputStream();
				byte [] bytes = new byte[(int)arquivo.getSize()];
				IOUtils.readFully(is, bytes);
				c.setFoto(bytes);
				contatoRepository.save(c);
				is.close();
				return bytes;
			}catch (IOException e) {
				return null;
			}
		}).orElse(null);
		
	}
}
