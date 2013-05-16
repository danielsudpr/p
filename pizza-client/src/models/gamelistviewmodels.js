Pedido = function(name, priority) {
	self = this;
	this.name;
	this.priority;
};

PedidoViewModel = function(games) {
	self = this;
	self.gamesToPlay = ko.observableArray(games);
	self.gamesCount = ko.computed(function() {
		return self.gamesToPlay().length + " games found.";
	});
};

ko.applyBindings(new PedidoViewModel([
	{name: "Skyrim", priority: 1},
	{name: "Max Payne 3", priority: 2}
]));