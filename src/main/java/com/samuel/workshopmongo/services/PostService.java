package com.samuel.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samuel.workshopmongo.domain.Post;
import com.samuel.workshopmongo.repository.PostRepository;
import com.samuel.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public Post findById(String id) {
		Optional<Post> user = repo.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));

	}

	public List<Post> findByTitle(String txt) {
		return repo.searchTitle(txt);
	}
	// tem que adicionar um dia amais, por que tem que ser até o final do proximo dia, porcausa do milisegundos;
	public List<Post> fullSeach(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repo.fullSearch(text, minDate, maxDate);
	}

}