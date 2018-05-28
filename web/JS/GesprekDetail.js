function Gesprek(gesprekID) {

    var gesprekken = document.getElementsByClassName("gesprek");
    var details = document.getElementsByClassName("details");

    //hide all
    for (i = 0; i < gesprekken.length; i++) {
        var elem = gesprekken[i];
        elem.style.backgroundColor = 'white';
    }

    for (i = 0; i < details.length; i++) {
        var elem = details[i];
        elem.style.display = 'none';
    }
    
    //show selected
    
    document.getElementById("links"+gesprekID).style.backgroundColor = 'aqua';
    document.getElementById("rechts"+gesprekID).style.display = 'block';

}