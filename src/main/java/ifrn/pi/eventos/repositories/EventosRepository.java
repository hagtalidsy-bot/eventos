package ifrn.pi.eventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.eventos.models.Eventos;

public interface EventosRepository extends JpaRepository<Eventos, Long> {

}
