function Menu(string){
    if(string === "werknemer"){
        var elemwerk = document.getElementsByClassName("werknemer");
        var elemnoti = document.getElementsByClassName("notificatie");
        
        for (i = 0; i < elemwerk.length; i++) {
            var elem = elemwerk[i];
            elem.style.display = 'block';
        }
        for (i = 0; i < elemnoti.length; i++) {
            var elem = elemnoti[i];
            elem.style.display = 'none';
        }
        
        document.getElementById("werknemer").style.backgroundColor  = 'aqua';
        document.getElementById("notificatie").style.backgroundColor  = 'white';
    }
    if(string === "notificatie"){
        var elemwerk = document.getElementsByClassName("werknemer");
        var elemnoti = document.getElementsByClassName("notificatie");
        
        for (i = 0; i < elemwerk.length; i++) {
            var elem = elemwerk[i];
            elem.style.display = 'none';
        }
        for (i = 0; i < elemnoti.length; i++) {
            var elem = elemnoti[i];
            elem.style.display = 'block';
        }
        
        document.getElementById("notificatie").style.backgroundColor  = 'aqua';
        document.getElementById("werknemer").style.backgroundColor  = 'white';
    }
}