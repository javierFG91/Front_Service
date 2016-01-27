function SearchModel() {
        var self = this;
        self.query = ko.observable("");
        self.resultados = ko.observableArray();
        self.buscar = function() {
                self.resultados.push(new ResultadoModel({link: "http://www.google.cl", resumen: "Google Chile"}));
                self.resultados.push(new ResultadoModel({link: "http://www.yahoo.cl", resumen: "Yahoo!"}));
                self.resultados.push(new ResultadoModel({link: "http://www.facebook.com", resumen: "Facebook"}));
                self.resultados.push(new ResultadoModel({link: "http://www.jaidefinichon.com", resumen: "Terrible HD"}));
        };
};
function ResultadoModel(params) {
        var self = this;
        self.link = ko.observable(params.link);
        self.resumen = ko.observable(params.resumen);
};
window.model = new SearchModel();
ko.applyBindings(window.model);
