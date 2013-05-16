function Task(data) {
	this.id = ko.observable(data.id);
	this.title = ko.observable(data.title);
	this.done = ko.observable(data.done);
}

function TaskListViewModel() {
	// Data
	var self = this;
	self.tasks = ko.observableArray([]);
	self.newTaskText = ko.observable();
	self.incompleteTasks = ko.computed(function() {
		return ko.utils.arrayFilter(self.tasks(), function(task) {
			return !task.done() && !task._destroy;
		});
	});

	// Behaviour
	self.addTask = function() {
		self.tasks.push(new Task({title: this.newTaskText()}));
		self.newTaskText("");
	};

	self.removeTask = function(task) {
		self.tasks.destroy(task);
	};

	self.save = function() {

		var jsonData = ko.toJSON(self.tasks);
		console.log(jsonData);

		$.ajax("/jaxrs-knockoutjs/rest/tasks", {
			data: jsonData,
			type: "post", contentType: "application/json",
			success: function(allData) {
				var mappedTasks = $.map(allData, function(item) {
					return new Task(item);
				});
				self.tasks(mappedTasks);
			}
		});
	};

	// Startup operations
	$.getJSON("/jaxrs-knockoutjs/rest/tasks", function(allData) {
		console.log(ko.toJSON(allData));
		var mappedTasks = $.map(allData, function(item) {
			return new Task(item);
		});
		self.tasks(mappedTasks);
	});
}

ko.applyBindings(new TaskListViewModel());