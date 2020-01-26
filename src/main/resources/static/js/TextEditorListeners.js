var get_trigger_url = "http://localhost:8081/rest/trigger/"
var get_note_url = "http://localhost:8081/rest/getnote/"
var changed = false;
var form = document.getElementById("textForm");
var textEditor = document.getElementById("textEditor");
var divEditor = document.getElementsByClassName("editor")[0];
var firstIteration = true;
var domain;
var Id = divEditor.id;
var triggerCount = 0;
var oldTriggerCount = 0;
var note;

//REST Requests
var request = new XMLHttpRequest();
var noteRequest = new XMLHttpRequest();

//Event Listener Text Area
textEditor.oninput = function(event){
 event.preventDefault();
    console.log("changed...");
    changed = true;
};

//request Impl.
request.open('GET', get_trigger_url+Id);

request.onload = function() {
    console.log("request: ",request.responseText);
    triggerCount = request.responseText;

    if(firstIteration){
        oldTriggerCount = triggerCount;
        firstIteration = false;
    }
};

request.onerror = function() {
    console.log("Error Getting trigger...");
};

//noteRequest Impl.
noteRequest.open('GET', get_note_url+Id);

noteRequest.onload = function(){
    note = noteRequest.responseText;
    textEditor.innerText = note;
    oldTriggerCount = triggerCount;
}

noteRequest.onerror = () => {
    console.log("Error getting note");
}


//Main Impl.
window.onload = function(){

    request.send();

    setInterval(function(){
        request.send();

        if(oldTriggerCount != triggerCount){
               noteRequest.open('GET', get_note_url+Id);
               noteRequest.send();
        }

        if(changed){
            console.log("Form Submit..");
            var res = form.submit();
            changed = false;
        }
    }, 2000);
}





