/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puertorico.pocs.rest;

import br.com.puertorico.pocs.entities.Task;
import br.com.puertorico.pocs.json.TaskResponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@LocalBean
@Path("/tasks")
public class TaskServices implements Serializable {

	@PersistenceContext
	private EntityManager em;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TaskResponse> getAll() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(Task.class));
		List<Task> result = em.createQuery(cq).getResultList();
		List<TaskResponse> tasks = new ArrayList<TaskResponse>();
		for (Task task : result) {
			tasks.add(new TaskResponse(task));
		}
		return tasks;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<TaskResponse> flush(List<TaskResponse> tasks) {

		for (TaskResponse taskResponse : tasks) {
			Task task = TaskResponse.convert(taskResponse);
			if (taskResponse.getDestroy() && taskResponse.getId() != null) {
				task = em.find(Task.class, task.getId());
				em.remove(task);
			} else {
				em.merge(task);
			}
		}

		return getAll();
	}
}
