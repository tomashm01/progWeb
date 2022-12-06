document.addEventListener("DOMContentLoaded",function(){ 
    const formulario= document.querySelector("form");
    const inputs = document.querySelectorAll('form input');
    const noCheck = ["radio","submit","reset","button"];
    const expresiones={
        email:/[a-zA-Z0-9\.\_]+\@[a-zA-Z0-9\.\_]+(\.\w+){1,}/,
        password: /^[a-zA-Z0-9]+$/,
        date:/^\d{4}([\-/.])(0?[1-9]|1[1-2])\1(3[01]|[12][0-9]|0?[1-9])$/,
        diaFecha:/^^\d{4}([\-/.])(0?[1-9]|1[1-2])\1(3[01]|[12][0-9]|0?[1-9])T\d\d\:\d\d$/,
        time:/^\d\d:\d\d$/,
        telefono:/^\d{9}$/,
        nombrecompleto:/^([A-Z][a-z]+\s){2,}[A-Z][a-z]+$/,
        numero:/^\d+$/
    }

    const validarFormulario = (e) => {
        
        let validado = true;
        inputs.forEach((input)=>{
            if(!noCheck.includes(input.type) && !validarCampo(input)) validado = false;
        });
        if(validado){
            formulario.removeEventListener("submit",validarFormulario);
            formulario.dispatchEvent(new Event("submit"));  
        }else{
            e.preventDefault();
        }
    }

    function validarCampo(input){
        if(!expresiones[input.id].test(input.value)){
            input.classList.add('cajaRoja');
            input.classList.remove('cajaBlanca');
            return false;
        }
        input.classList.add('cajaBlanca');
        input.classList.remove('cajaRoja');
        return true;     
    }
    
    function resetFormulario(){
        inputs.forEach((input)=>{
            if(!noCheck.includes(input.type)){
                input.classList.add('cajaBlanca');
                input.classList.remove('cajaRoja');
            }
        });
    }
    document.querySelector('form').addEventListener('submit',validarFormulario);
    document.querySelector('form').addEventListener('reset',resetFormulario);
});
