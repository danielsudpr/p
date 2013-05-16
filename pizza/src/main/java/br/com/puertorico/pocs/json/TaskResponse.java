/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puertorico.pocs.json;

import br.com.puertorico.pocs.entities.Task;
import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonProperty;

public class TaskResponse implements JsonDestroyable, Serializable {

	public static Task convert(TaskResponse taskResponse) {
		Task task = new Task();
		task.setId(taskResponse.getId());
		task.setTitle(taskResponse.getTitle());
		task.setDone(taskResponse.getDone());
		return task;
	}

	private Long id;

	private String title;

	private Boolean done;

	private Boolean destroy;

	{
		destroy = false;
	}

	public TaskResponse() {
	}

	public TaskResponse(Task task) {
		id = task.getId();
		title = task.getTitle();
		done = task.getDone();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	@Override
	@JsonProperty("_destroy")
	public Boolean getDestroy() {
		return destroy;
	}

	@Override
	public void setDestroy(Boolean _destroy) {
		this.destroy = _destroy;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
		hash = 37 * hash + (this.title != null ? this.title.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final TaskResponse other = (TaskResponse) obj;
		if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
			return false;
		}
		if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title)) {
			return false;
		}
		return true;
	}
}
