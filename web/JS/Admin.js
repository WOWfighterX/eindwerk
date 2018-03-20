function Menu(string){
    if(string === "+pers"){
        
        document.getElementById("PersToev").style.display = 'block';
        document.getElementById("EvalVer").style.display = 'none';
        document.getElementById("Rechten").style.display = 'none';
        document.getElementById("SchoolToev").style.display = 'none';
        document.getElementById("SchoolVerw").style.display = 'none';
        document.getElementById("Account").style.display = 'none';
        document.getElementById("FuncToev").style.display = 'none';
        
        document.getElementById("+func").style.backgroundColor  = 'white';
        document.getElementById("+pers").style.backgroundColor  = 'aqua';
        document.getElementById("+eval").style.backgroundColor  = 'white';
        document.getElementById("+recht").style.backgroundColor  = 'white';
        document.getElementById("+school").style.backgroundColor  = 'white';
        document.getElementById("-school").style.backgroundColor  = 'white';
        document.getElementById("-account").style.backgroundColor  = 'white';
        
    }
    
    if(string === "+eval"){
        
        document.getElementById("PersToev").style.display = 'none';
        document.getElementById("EvalVer").style.display = 'block';
        document.getElementById("Rechten").style.display = 'none';
        document.getElementById("SchoolToev").style.display = 'none';
        document.getElementById("SchoolVerw").style.display = 'none';
        document.getElementById("Account").style.display = 'none';
        document.getElementById("FuncToev").style.display = 'none';
        
        document.getElementById("+func").style.backgroundColor  = 'white';
        document.getElementById("+pers").style.backgroundColor  = 'white';
        document.getElementById("+eval").style.backgroundColor  = 'aqua';
        document.getElementById("+recht").style.backgroundColor  = 'white';
        document.getElementById("+school").style.backgroundColor  = 'white';
        document.getElementById("-school").style.backgroundColor  = 'white';
        document.getElementById("-account").style.backgroundColor  = 'white';
        
    }
    
    if(string === "+recht"){
        
        document.getElementById("PersToev").style.display = 'none';
        document.getElementById("EvalVer").style.display = 'none';
        document.getElementById("Rechten").style.display = 'block';
        document.getElementById("SchoolToev").style.display = 'none';
        document.getElementById("SchoolVerw").style.display = 'none';
        document.getElementById("Account").style.display = 'none';
        document.getElementById("FuncToev").style.display = 'none';
        
        document.getElementById("+func").style.backgroundColor  = 'white';
        document.getElementById("+pers").style.backgroundColor  = 'white';
        document.getElementById("+eval").style.backgroundColor  = 'white';
        document.getElementById("+recht").style.backgroundColor  = 'aqua';
        document.getElementById("+school").style.backgroundColor  = 'white';
        document.getElementById("-school").style.backgroundColor  = 'white';
        document.getElementById("-account").style.backgroundColor  = 'white';
        
    }
    
    if(string === "+school"){
        
        document.getElementById("PersToev").style.display = 'none';
        document.getElementById("EvalVer").style.display = 'none';
        document.getElementById("Rechten").style.display = 'none';
        document.getElementById("SchoolToev").style.display = 'block';
        document.getElementById("SchoolVerw").style.display = 'none';
        document.getElementById("Account").style.display = 'none';
        document.getElementById("FuncToev").style.display = 'none';
        
        document.getElementById("+func").style.backgroundColor  = 'white';
        document.getElementById("+pers").style.backgroundColor  = 'white';
        document.getElementById("+eval").style.backgroundColor  = 'white';
        document.getElementById("+recht").style.backgroundColor  = 'white';
        document.getElementById("+school").style.backgroundColor  = 'aqua';
        document.getElementById("-school").style.backgroundColor  = 'white';
        document.getElementById("-account").style.backgroundColor  = 'white';
        
    }
    
    if(string === "-school"){
        
        document.getElementById("PersToev").style.display = 'none';
        document.getElementById("EvalVer").style.display = 'none';
        document.getElementById("Rechten").style.display = 'none';
        document.getElementById("SchoolToev").style.display = 'none';
        document.getElementById("SchoolVerw").style.display = 'block';
        document.getElementById("Account").style.display = 'none';
        document.getElementById("FuncToev").style.display = 'none';
        
        document.getElementById("+func").style.backgroundColor  = 'white';
        document.getElementById("+pers").style.backgroundColor  = 'white';
        document.getElementById("+eval").style.backgroundColor  = 'white';
        document.getElementById("+recht").style.backgroundColor  = 'white';
        document.getElementById("+school").style.backgroundColor  = 'white';
        document.getElementById("-school").style.backgroundColor  = 'aqua';
        document.getElementById("-account").style.backgroundColor  = 'white';
        
    }
    
    if(string === "-account"){
        
        document.getElementById("PersToev").style.display = 'none';
        document.getElementById("EvalVer").style.display = 'none';
        document.getElementById("Rechten").style.display = 'none';
        document.getElementById("SchoolToev").style.display = 'none';
        document.getElementById("SchoolVerw").style.display = 'none';
        document.getElementById("Account").style.display = 'block';
        document.getElementById("FuncToev").style.display = 'none';
        
        document.getElementById("+func").style.backgroundColor  = 'white';
        document.getElementById("+pers").style.backgroundColor  = 'white';
        document.getElementById("+eval").style.backgroundColor  = 'white';
        document.getElementById("+recht").style.backgroundColor  = 'white';
        document.getElementById("+school").style.backgroundColor  = 'white';
        document.getElementById("-school").style.backgroundColor  = 'white';
        document.getElementById("-account").style.backgroundColor  = 'aqua';
        
    }
    
    if(string === "+func"){
        
        document.getElementById("PersToev").style.display = 'none';
        document.getElementById("EvalVer").style.display = 'none';
        document.getElementById("Rechten").style.display = 'none';
        document.getElementById("SchoolToev").style.display = 'none';
        document.getElementById("SchoolVerw").style.display = 'none';
        document.getElementById("Account").style.display = 'none';
        document.getElementById("FuncToev").style.display = 'block';
        
        document.getElementById("+func").style.backgroundColor  = 'aqua';
        document.getElementById("+pers").style.backgroundColor  = 'white';
        document.getElementById("+eval").style.backgroundColor  = 'white';
        document.getElementById("+recht").style.backgroundColor  = 'white';
        document.getElementById("+school").style.backgroundColor  = 'white';
        document.getElementById("-school").style.backgroundColor  = 'white';
        document.getElementById("-account").style.backgroundColor  = 'white';
        
    }
}