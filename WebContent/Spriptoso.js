function isNumberKey(evt)
{
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
    {
    	 alert("Insira apenas números.");	
    	 return false;
    }
        		
    	 return true;
}

function isLettersOnly(evt)
{
    evt = (evt) ? evt : event;
    var charCode = (evt.charCode) ? evt.charCode : ((evt.keyCode) ? evt.keyCode : ((evt.which) ? evt.which : 0));
    if (charCode > 31 && (charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122)) 
    {
        alert("Insira apenas letras.");
        return false;
    }
        return true;
}