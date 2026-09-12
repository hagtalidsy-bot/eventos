package ifrn.pi.eventos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifrn.pi.eventos.models.Eventos;
import ifrn.pi.eventos.repositories.EventosRepository;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventosRepository er;

    @GetMapping("/form")
    public String form() {
        return "eventos/formEvento";
    }

    @PostMapping
    public String adicionar(Eventos evento) {

        System.out.println(evento);
        er.save(evento);

        return "redirect:/eventos";
    }

    @GetMapping
    public ModelAndView listar() {

        List<Eventos> eventos = er.findAll();

        ModelAndView mv = new ModelAndView("eventos/lista");
        mv.addObject("eventos", eventos);

        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhar(@PathVariable Long id) {

        ModelAndView md = new ModelAndView();

        Optional<Eventos> opt = er.findById(id);

        if (opt.isEmpty()) {
            md.setViewName("redirect:/eventos");
            return md;
        }

        Eventos evento = opt.get();

        md.setViewName("eventos/detalhes");
        md.addObject("evento", evento);

        return md;
    }
}