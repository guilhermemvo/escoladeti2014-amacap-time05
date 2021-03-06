package br.unicesumar.time5.controller;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import br.unicesumar.time5.entity.AssociadoPJ;
import br.unicesumar.time5.service.AssociadoPJService;
import br.unicesumar.time5.service.LivroService;
import br.unicesumar.time5.service.RelatorioService;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;


@Controller
@RequestMapping("/associadopj")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class AssociadoPJController extends RelatorioService implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(AssociadoPJController.class);

    @Autowired
    private AssociadoPJService service;
    
    @Autowired
    private DataSource dataSource;

    @RequestMapping(value = {"/listar"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<AssociadoPJ> listar() {
        return service.getAssociados(0);
    }

    @RequestMapping(value = {"/listar/pag/{pagina}"}, method = RequestMethod.GET)
    @ResponseBody
    public DataPage<AssociadoPJ> listar(@PathVariable Integer pagina) {
        return service.getAssociados(pagina);
    }

    @RequestMapping(value = "/remover", method = RequestMethod.DELETE)
    @ResponseBody
    public String remover(@RequestBody AssociadoPJ associado) {
    	
        service.remover(associado);
        return "OK";
    	
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @ResponseBody
    public String salvar(@RequestBody AssociadoPJ associado) {
        service.salvar(associado);
        return "OK";
    }

    @RequestMapping(value = "/localiza", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public AssociadoPJ localiza(@RequestParam Long id) {
        logger.debug("Id a localizar: {}", id);
        return service.recuperarPeloId(id);
    }

    @RequestMapping(value = {"/todas"}, method = RequestMethod.GET)
    @ResponseBody
    public List<AssociadoPJ> todas() {
        return service.getTodosAssociados();
    }

    @RequestMapping(value = "/carregar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AssociadoPJ carregar(@PathVariable Long id) {
        return service.recuperarPeloId(id);
    }
    
    @RequestMapping(value = "/relatorio/listaRelatorio", method = RequestMethod.GET)
    public void listaRelatorio(HttpServletResponse response) throws IOException, JRException, SQLException {
        listaRelatorio(response, dataSource.getConnection(), "listaAssociadoPJ.jasper", AssociadoPJ.class, "listaAssociadoPJ.pdf");
    }
    
    @RequestMapping(value = "/relatorio/listaPagamento/{id}", method = RequestMethod.GET)
    public void listaParticipante(HttpServletResponse response, @PathVariable Long id) throws IOException, JRException, SQLException {
        listaID(response, dataSource.getConnection(), id, "listaPagamentoAssociadosPJ.jasper", AssociadoPJ.class, "listaPagamentoAssociadosPJ.pdf", "idAssociado");
    }
}
