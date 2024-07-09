var network = null;
var input = document.getElementById("sip");
input.addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
        searchMethodByName();
    }
});
var resultsDiv = document.getElementById('searchResult');
var dataToBeSearchedIn = [];

function focusAndZoomNode(param) {
    let id = param.children[0].textContent;
    network.fit();
    network.focus(id);
    network.moveTo({
        position: network.getViewPosition(),
        scale: 2,
        animation: { duration: 1500 }
    });
}
function fromHTML(html) {
    const template = document.createElement('template');
    template.innerHTML = html;
    return template.content.children[0];
}
function readTextFile(file, callback) {
    var rawFile = new XMLHttpRequest();
    rawFile.overrideMimeType('application/json');
    rawFile.open("GET",file,true);
    rawFile.onreadystatechange= function(){
        if (rawFile.readyState===4 && rawFile.status == '200') {
            callback(rawFile.responseText);
        };
    };
    rawFile.send(null);
};
function searchMethodByName() {
    resultsDiv.innerHTML = "";
    if (!input.value) {
        return ;
    }
    for (let node of dataToBeSearchedIn) {
        if (node.methodName.includes(input.value)) {
            let addDangerBadge = !!node.color;
            resultsDiv.append(fromHTML("<p class=\"methodEntry\"><span class=\"badge " + (addDangerBadge ? "badge-danger" : "badge-secondary") + "\">" + node.id + "</span> " + node.methodName + "</p>"));
        }
    }
    $(".methodEntry").click(function() {
        focusAndZoomNode(this);
    });
};

readTextFile('elements.json', function(text) {
    var parsedData=JSON.parse(text);
    var data = {
        nodes: new vis.DataSet(parsedData.nodes),
        edges: new vis.DataSet(parsedData.edges)
    };
    var options = {
        interaction: { hover: true },
        nodes: { shape: "circle" }
    }
    var container = document.getElementById('graph');
    network = new vis.Network(container,data,options);
    network.addEventListener('selectNode', function(obj) {
        let div = document.getElementById("clickResult");
        div.innerHTML = "";
        let node = data.nodes.get(obj.nodes[0]);
        div.append(fromHTML("<p><b>Id:</b> " + node.id + "</p>"));
        div.append(fromHTML("<p><b>Method Name:</b> " + node.methodName + "</p>"));
        div.append(fromHTML("<p><b>Invoked By:</b> " + node.invokedBy + "</p>"));
        div.append(fromHTML("<p><b>Class Name:</b> " + node.className + "</p>"));
    });
    dataToBeSearchedIn = parsedData.nodes;
});