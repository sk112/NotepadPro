
var changed = false;
var form = document.getElementById("textForm");
var textEditor = document.getElementById("textEditor");

console.log("Form ", form);
console.log("text editor ", textEditor);

 textEditor.oninput = function(event){
 event.preventDefault();
    console.log("changed...");
    changed = true;
};

window.onload = function(){

    setInterval(function(){

        if(changed){
            console.log("Form Submit..");
            form.submit();
            changed = false;
        }
    }, 2000);
}





