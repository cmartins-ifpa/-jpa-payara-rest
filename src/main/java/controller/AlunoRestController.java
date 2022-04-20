package controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.AlunoDAO;
import dto.AlunoDTO;
import entidades.Aluno;

@RequestScoped
@Path("alunos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlunoRestController {
	@Inject
	private AlunoDAO dao;

	@GET
    public Response getAll() {
        return Response.ok(dao.findAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getAluno(@PathParam("id") Long id) {
        Aluno aluno = dao.find(id);
        return Response.ok(aluno).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Aluno a) {
        Aluno updatea = dao.find(id);

        updatea.setNome(a.getNome());
        updatea.setDtnasc(a.getDtnasc());
        dao.edit(updatea);

        return Response.ok().build();
    }

    @POST
    public Response create(AlunoDTO a) {
    	Aluno alu = new Aluno();
    	LocalDate ld = LocalDate.parse(a.getDtnasc());

    	Date dtNascTransf = Date.from(ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    	alu.setNome(a.getNome());
    	alu.setDtnasc(dtNascTransf);    	
    	
        dao.create(alu);
        System.out.println("INCLUI - " + a.toString());
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        Aluno a = dao.find(id);
        
        dao.remove(a);

        return Response.ok().build();
    }
}
